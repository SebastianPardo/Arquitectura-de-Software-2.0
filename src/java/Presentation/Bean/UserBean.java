/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.UserManager;
import DataAccess.Entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author arqsoft2017i
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    @ManagedProperty("#{login}")
    private LoginBean login;

    private ArrayList<Usuario> usuarios;
    
    public UserBean(){
        
    }
    
    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }
    
    public ArrayList<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = new ArrayList<>((new UserManager()).usuarios());
            usuarios.sort((Usuario user1, Usuario user2) -> 
                    user1.getApellidoUsuario().compareTo(user2.getApellidoUsuario()));
        }
        return usuarios;
    }
    
    
    public void addAmigo(String id){
        
    }
}
