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

/**
 *
 * @author arqsoft2017i
 */
public class UserManager {

    
    
    public List<Usuario> usuarios(){
        UserDAO usuarioDAO = new UserDAO();
        return usuarioDAO.findAll();
    }
            
    public String createUser(Integer idUsuario, String nombreUsuario,
            String apellidoUsuario, String aliasUsuario, String sexoUsuario,
            String telefonoUsuario, boolean activo, Date fechaNacimientoUsuario,
            String pass) {
        
        
        Usuario usuario = 
                new Usuario(idUsuario, nombreUsuario, apellidoUsuario,
                aliasUsuario, sexoUsuario, telefonoUsuario, activo, new Date(),
                fechaNacimientoUsuario);
        Autenticacion autenticacion =
                new Autenticacion(idUsuario, aliasUsuario,pass);
        //usuario.setAutenticacion(autenticacion);

        UserDAO usuarioDAO = new UserDAO();
        Usuario usuarioG = usuarioDAO.persist(usuario);
        
        AuthenticationDAO autenticacionDAO = new AuthenticationDAO();
        Autenticacion autenticacionG = autenticacionDAO.persist(autenticacion);
        
        if (usuarioG != null /*&& autenticacionG != null*/) {
            return "El isuario ha sido creado, su id es " + usuario.getAliasUsuario()+ ".";
        } else {
            if(usuarioG == null){
                return "El usuario no pudo ser creado.";
            }else{
                return "La autenticación no pudo ser creada.";
            }                            
        }
    }
}
