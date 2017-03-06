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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
            return "perfil";
        }else{
            usrId = UserManager.noUsrId;
            message = "Datos erroneos";
            return null;
        }        
    }
    
    public void logout(){
        usrId = UserManager.noUsrId;
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
    
    private String pass;
    private String correo;
    private String message;       
    private Integer usrId;
    private UserView usrView;
    private UserView usrVisit;
}
