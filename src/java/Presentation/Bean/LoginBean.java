/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.AppController;
import BusinessLogic.Controller.UserManager;
import BusinessLogic.Controller.UserView;
import java.io.Serializable;
import java.util.HashSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author arqsoft2017i
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -3476703846699958985L;

    public LoginBean() {
        usrId = UserManager.noUsrId;
    }

    public String getPass() {
        return pass;
    }

    /* Es en este método que debemos hacer la encripción y es la encripción lo que debe alamacenarse */
    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOnline() {
        return (usrId > UserManager.noUsrId);
    }

    public String login() {
        Integer someId = AppController.getInstance().login(correo, pass);
        if (someId > UserManager.noUsrId) {
            //Mantener el codigo de usuario guardado para una sesion
            if (stayLogged) {//Session permanente
                addCookie("unprofileusr", someId.toString(), 2592000);
            } else//Session temporal
            {
                addCookie("unprofileusr_temp", someId.toString(), -1);
            }
            usrId = someId;
            loadUserData();
            return "bootstrap?faces-redirect=true";
        } else {
            usrId = UserManager.noUsrId;
            FacesMessage mess = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Datos erroneos");
            FacesContext.getCurrentInstance().addMessage(null, mess);
            return null;
        }
    }

    private void addCookie(String name, String id, int time) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Cookie userCookie = new Cookie(name, id);
        userCookie.setMaxAge(time);//30 días
        userCookie.setPath("/UNProfile");//Path para ser usada en todas las páginas de la app
        ((HttpServletResponse) facesContext.getExternalContext()
                .getResponse()).addCookie(userCookie);
    }

    private void removeCookie(String name) {
        addCookie(name, null, 0);
    }

    public String logout() {
        removeCookie("unprofileusr_temp");
        removeCookie("unprofileusr");
        usrId = UserManager.noUsrId;
        usrView = new UserView();
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login_c?faces-redirect=true";
    }

    private void loadUserData() {
        if (isOnline()) {
            usrView = AppController.getInstance().loadUser(usrId);
        }
    }

    public String onLoad() {
        Cookie stayLoggedUsr = (Cookie) FacesContext.getCurrentInstance().
                getExternalContext().getRequestCookieMap().get("unprofileusr");
        Cookie loggedUsr = (Cookie) FacesContext.getCurrentInstance().
                getExternalContext().getRequestCookieMap().get("unprofileusr_temp");
        if (stayLoggedUsr != null || loggedUsr != null) {
            if (stayLoggedUsr != null) {
                usrId = Integer.parseInt(stayLoggedUsr.getValue());
                //Renovar el tiempo de la cookie
                addCookie("unprofileusr", usrId.toString(), 2592000);
            } else if (loggedUsr != null) {
                usrId = Integer.parseInt(loggedUsr.getValue());
            }
            loadUserData();
            //Redirigir a la página del perfil del usuario
            return "bootstrap?faces-redirect=true";
        }
        return "login_c";
    }

    public String profileOnLoad() {
        if (usrView.getUsrId() > UserManager.noUsrId) {
            return null;
        } else {
            Cookie stayLoggedUsr = (Cookie) FacesContext.getCurrentInstance().
                    getExternalContext().getRequestCookieMap().get("unprofileusr");
            Cookie loggedUsr = (Cookie) FacesContext.getCurrentInstance().
                    getExternalContext().getRequestCookieMap().get("unprofileusr_temp");
            if (stayLoggedUsr != null || loggedUsr != null) {
                if (stayLoggedUsr != null) {
                    usrId = Integer.parseInt(stayLoggedUsr.getValue());
                    //Renovar el tiempo de la cookie
                    addCookie("unprofileusr", usrId.toString(), 2592000);
                } else if (loggedUsr != null) {
                    usrId = Integer.parseInt(loggedUsr.getValue());
                }
                loadUserData();
                return null;
            }
        }
        return "login_c";
    }
    public String anotherProfileOnLoad(){
        if(usrVisit==null){
            return "bootstrap?faces-redirect=true";
        }else
            return profileOnLoad();
    }
    

    public UserView getUser() {
        return usrView;
    }

    public String visit(UserView usr) {
        //usrVisit = AppController.getInstance().loadUser(id);
        usrVisit = usr;
        return "profile?faces-redirect=true";
    }

    public UserView getUserVisit() {
        return usrVisit;
    }

    public void save() {
        usrView.saveEdit();
    }

    //Se reescriben los datos alterados por los datos de la entidad en la BD
    public void cancelEdit() {
        loadUserData();
    }

    public boolean isStayLogged() {
        return stayLogged;
    }

    public void setStayLogged(boolean stayLogged) {
        this.stayLogged = stayLogged;
    }

    public void validatePass(FacesContext context, UIComponent component, Object value) {
        String password = (String) value;
        if (!pass.equals(password)) {
            UIInput confirmComponent = (UIInput) component.getAttributes().get("confirmPass");
            String confirm = (String) confirmComponent.getValue();

            if (password == null || password.isEmpty() || password.length() < 6) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de contraseña", "La contraseña debe tener entre 6 y 20 caracteres"));
            }
            if (!password.equals(confirm)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", "Los valores de las contraseñas deben coincidir"));
            }
        }
    }

    private String pass;
    private String correo;
    private String message;
    private boolean stayLogged;
    private Integer usrId;
    private UserView usrView;
    private UserView usrVisit;
}
