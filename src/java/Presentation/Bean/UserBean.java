/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

/**
 *
 * @author arqsoft2017i
 */
import BusinessLogic.Controller.UserManager;
import DataAccess.Entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UserBean {
    private String nombreUsuario;
    private String apellidoUsuario;
    private String aliasUsuario;
    private String sexoUsuario;
    private String telefonoUsuario;
    private Date fechaNacimientoUsuario;
    private String pass;
    private String correo;
    private String message;
    private List<Usuario> usuarios;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public String getSexoUsuario() {
        return sexoUsuario;
    }

    public void setSexoUsuario(String sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public Date getFechaNacimientoUsuario() {
        return fechaNacimientoUsuario;
    }

    public void setFechaNacimientoUsuario(Date fechaNacimientoUsuario) {
        this.fechaNacimientoUsuario = fechaNacimientoUsuario;
    }

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
        
    public void createUser(){
        UserManager createUser = new UserManager();
        message = createUser.createUser(nombreUsuario, 
                apellidoUsuario, aliasUsuario, sexoUsuario, telefonoUsuario, 
                true, fechaNacimientoUsuario, pass, correo);
    }

    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = (new UserManager()).usuarios();
        }
        return usuarios;
    }
    
    public void addAmigo(String id){
        
    }

}
