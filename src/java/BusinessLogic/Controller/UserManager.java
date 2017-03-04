/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.AuthenticationDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Autenticacion;
import DataAccess.Entity.Usuario;
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
    
    public List<Usuario> usuarios(){
        UserDAO usuarioDAO = new UserDAO();
        return usuarioDAO.findAll();
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
            value.setUsrId(usr.getIdUsuario());            
            value.setUsrAlias(usr.getAliasUsuario());
            value.setUsrName(usr.getNombreUsuario());
            value.setUsrLastName(usr.getApellidoUsuario());
        }
        
        return value;
    }
    
    public static Integer noUsrId = -1;
}
