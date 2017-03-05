/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.AppController;
import BusinessLogic.Controller.UserManager;
import BusinessLogic.Controller.UserView;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author arqsoft2017i
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    
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
    /*
     * En este método sólo verificamos si el usuario se conectó de forma exitosa.
     * El atributo usrId está en -1 sólo si no se han conectado.
     */
    public boolean isOnline(){
        return (usrId > UserManager.noUsrId);
    }
    
    public String login(){
        Integer someId = AppController.create().login(correo, pass);       
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
            usrView = AppController.create().loadUser(usrId);
            usrView.setUsrMail(correo);            
        }
        
        return value;
    }
    
    public UserView getUser(){
        return usrView;
    }
    
    private String pass;
    private String correo;
    private String message;       
    private Integer usrId;
    private UserView usrView;
}
