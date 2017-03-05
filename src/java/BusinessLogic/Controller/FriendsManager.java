/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.AuthenticationDAO;
import DataAccess.DAO.FriendsDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Amigos;
import DataAccess.Entity.Usuario;

/**
 *
 * @author arqsoft2017i
 */
public class FriendsManager {
        
    public FriendsManager(){
    
    }
    
    public java.util.ArrayList<UserView> getFriendsFrom(Integer usrId){
        java.util.List<Amigos> friendsList = (new FriendsDAO()).getFriendsFrom(usrId);
        java.util.ArrayList<UserView> usrFriends = new java.util.ArrayList<>();
        
        for (Amigos someFriends : friendsList) {
            Usuario aFriend = (new UserDAO()).searchById(someFriends.getAmigosPK().getIdAmigo());            
            usrFriends.add(new UserView(aFriend.getIdUsuario(), (new AuthenticationDAO()).searchByUsrId(someFriends.getAmigosPK().getIdAmigo()).getCorreo(), aFriend.getAliasUsuario(), aFriend.getNombreUsuario(), aFriend.getApellidoUsuario()));
        }
        
        return usrFriends;
    }    
}
