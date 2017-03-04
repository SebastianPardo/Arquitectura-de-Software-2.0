/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.UserManager;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author arqsoft2017i
 */
@ManagedBean
@ViewScoped
public class LoginBean {

    private String pass;
    private String correo;
    private String message;
    
    public String getPass() {
        return pass;
    }

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
    
    public String login(){
        if(((new UserManager()).login(correo, pass))!=UserManager.noUsrId)
            return "usuarios";
        else
            message = "Datos erroneos";
        return null;
    }

    
}
