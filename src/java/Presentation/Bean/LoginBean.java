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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author arqsoft2017i
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

    private static final long serialVersionUID = -3476703846699958985L;
    
    public LoginBean(){        
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
    
    public boolean isOnline(){
        return (usrId > UserManager.noUsrId);
    }
    
    public String login(){
        Integer someId = AppController.getInstance().login(correo, pass);       
        if(someId > UserManager.noUsrId){                        
            usrId = someId;
            loadUserData();
            return "bootstrap";
        }else{
            usrId = UserManager.noUsrId;
            message = "Datos erroneos";
            return null;
        }        
    }
    
    public String logout(){
        usrId = UserManager.noUsrId;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login_c.xhtml?faces-redirect=true";
    }

    private boolean loadUserData(){
        boolean value = false;
        
        if(isOnline()){
            usrView = AppController.getInstance().loadUser(usrId);       
        }
        
        return value;
    }
    
    public UserView getUser(){
        return usrView;
    }
    
    public String visit(Integer id){
        usrVisit = AppController.getInstance().loadUser(id);
        return "perfil_user";
    }
    
    public UserView getUserVisit(){
        return usrVisit;
    }
    
    public void save(){
        usrView.saveEdit();
    }
    
    //Se reescriben los datos alterados por los datos de la entidad en la BD
    public void cancelEdit(){
        loadUserData();
    }
    
    public void validatePass(FacesContext context, UIComponent component, Object value) {
        String password = (String) value;
        UIInput confirmComponent = (UIInput) component.getAttributes().get("confirmPass");
        String confirm = (String)confirmComponent.getValue();
        
        if (password == null || password.isEmpty() || password.length()<6) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de contraseña", "La contraseña debe tener entre 6 y 20 caracteres"));
        }
        if(!password.equals(confirm))
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", "Los valores de las contraseñas deben coincidir"));
    }
    
    private String pass;
    private String correo;
    private String message;       
    private Integer usrId;
    private UserView usrView;
    private UserView usrVisit;
}
