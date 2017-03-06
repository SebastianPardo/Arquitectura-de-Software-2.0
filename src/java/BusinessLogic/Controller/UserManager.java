/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.AuthenticationDAO;
import DataAccess.DAO.FriendsDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Autenticacion;
import DataAccess.Entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author arqsoft2017i
 */
public class UserManager {

    public UserManager(){
    
    }
    
    
    public Integer login(String mail, String pass){
        Integer usrId = noUsrId;
        AuthenticationDAO autenticacionDAO = new AuthenticationDAO();
        Autenticacion autenticacionG = autenticacionDAO.searchByUsrData(mail,pass);
        
        if(autenticacionG != null){
            usrId = autenticacionG.getIdUsuario();        
        }
        
        return usrId;
    }

    public String createUser(String nombreUsuario,
            String apellidoUsuario, String aliasUsuario, String sexoUsuario,
            String telefonoUsuario, boolean activo, Date fechaNacimientoUsuario,
            String pass, String correo) {

        Usuario usuario
                = new Usuario(null, nombreUsuario, apellidoUsuario,
                        aliasUsuario, sexoUsuario, telefonoUsuario, activo, new Date(),
                        fechaNacimientoUsuario);

        //usuario.setAutenticacion(autenticacion);
        try {
            UserDAO usuarioDAO = new UserDAO();
            Usuario usuarioG = usuarioDAO.persist(usuario);

            Autenticacion autenticacion
                    = new Autenticacion(usuarioG.getIdUsuario(), correo, pass);

            AuthenticationDAO autenticacionDAO = new AuthenticationDAO();
            Autenticacion autenticacionG = autenticacionDAO.persist(autenticacion);

            if (usuarioG != null && autenticacionG != null) {
                return "El usuario ha sido creado, su usuario es " + autenticacionG.getCorreo() + ".";
            } else {
                if (usuarioG == null) {
                    return "El usuario no pudo ser creado.";
                } else {
                    return "La autenticación no pudo ser creada.";
                }
            }
        } catch (IllegalStateException pe) {
            return "El correo ya existe";
        }

    }
    
    public UserView loadUser(Integer usrId){
        UserView value = new UserView();
        
        UserDAO usrDAO = new UserDAO();
        Usuario usr = usrDAO.searchById(usrId);
        
        if(usr != null){        
            value = new UserView(usr);            
        }
        
        return value;
    }
    
    public java.util.ArrayList<UserView> loadAllUsers(){        
        java.util.ArrayList<UserView> value = new java.util.ArrayList<>();
        java.util.ArrayList<Usuario> usrs = this.getUsuarios();
        
        for(Usuario aUsr: usrs){
            value.add(new UserView(aUsr));
        }
        
        return value;
    }
    
    public ArrayList<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = new ArrayList<>((new UserDAO().findAll()));
            usuarios.sort((Usuario user1, Usuario user2) -> 
                    user1.getApellidoUsuario().compareTo(user2.getApellidoUsuario()));
        }
        return usuarios;
    }
    
     public ArrayList<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public void setUsuariosFiltrados(ArrayList<Usuario> usuariosFiltrados) {
        this.usuariosFiltrados = usuariosFiltrados;
    }
    
    
    public ArrayList<UserView> getSuggestedFrom(Integer usrId){
        List<Usuario> sFriends = (new FriendsDAO()).getSuggestedFriendsFrom(usrId);
        ArrayList<UserView> suggested = new ArrayList<>();
        
        for(Usuario aUser : sFriends){
            UserView value = new UserView(aUser);
            suggested.add(value);
        }
        
        return suggested;
    }
    
    public void saveUser(Usuario user){
        (new UserDAO()).edit(user);
    }
    
    private ArrayList<Usuario> usuarios;
    private ArrayList<Usuario> usuariosFiltrados;
    public static Integer noUsrId = -1;
}
