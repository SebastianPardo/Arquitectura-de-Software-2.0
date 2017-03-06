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
    
    public void addFriend(int id){
        if ((new FriendsManager()).sendFriendRequest(login.getUser().getUsrId(), id)){
        }else{
        }
    }
    
    public AppController getAppController(){
        return AppController.getInstance();
    }
    
    
    public void deleteFriend(String id){
    }
    
    @ManagedProperty("#{login}")
    private LoginBean login;
}
