/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.AppController;
import BusinessLogic.Controller.UserView;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author DRAIKO
 */

@ManagedBean
@SessionScoped
public class ViewControl implements Serializable{
    
    private Integer userID;
    
    private UserView usr;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public UserView getUsr() {
        if(userID!=null && usr.getUsrId()==UserView.noUsrId){
            usr=AppController.getInstance().loadUser(userID);
        }
        return usr;
    }

    public void setUsr(UserView usr) {
        this.usr = usr;
    }
    
    public String visit(Integer usrID){
        
        return null;
    }
    
    public String onload(){
        if(getUsr()==null)
            return "bootstrap";
        return null;
    }
    
}
