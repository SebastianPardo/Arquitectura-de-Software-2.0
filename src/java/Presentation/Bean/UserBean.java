/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.AppController;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author arqsoft2017i
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1328414043287985722L;

    public UserBean() {

    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public void addFriend(int id, Integer idFriend) {
        if (getAppController().addFriend(id, idFriend)) {
            setMensaje("Solicitud enviada");
        } else {
            setMensaje("Ya tienes a este usuario añadido");
        }
    }
    
    public void answerFriendRequest(Integer id, Integer idFriend, boolean answer){
        getAppController().answerFriendRequest(id, idFriend, answer);
    }
            

    public AppController getAppController() {
        return AppController.getInstance();
    }

    public void deleteFriend(Integer id,Integer idFriend) {
        if (getAppController().getFriendsManager().deleteFriend(id, idFriend)) {
            setMensaje("Usuario eliminado");
        } else {
            setMensaje("El usuario no ha sido eliminado");
        }
    }
    
    public void blockFriend(Integer id,Integer idFriend) {
        if (getAppController().getFriendsManager().blockUser(id, idFriend)) {
            setMensaje("Usuario bloqueado");
        } else {
            setMensaje("El usuario no ha sido bloqueado");
        }
    }
    

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Estado",mensaje) );
        this.mensaje = mensaje;
    }

    @ManagedProperty("#{login}")
    private LoginBean login;
    private String mensaje;
}
