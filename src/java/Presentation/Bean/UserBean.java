/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.AppController;
import BusinessLogic.Controller.FriendsManager;
import BusinessLogic.Controller.UserManager;
import DataAccess.Entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    public UserBean(){
        
    }
    
    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }
    
    public void addFriend(Integer frdId){
        AppController.getInstance().addFriend(login.getUser().getUsrId(),frdId);
    }
       
    public void deleteFriend(Integer frdId){
        AppController.getInstance().deleteFriend(login.getUser().getUsrId(),frdId);
    }
    
    public void blockFriend(Integer frdId){
        AppController.getInstance().blockFriend(login.getUser().getUsrId(),frdId);
    }
    
    @ManagedProperty("#{login}")
    private LoginBean login;
}
