/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.FriendsDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Amigos;
import DataAccess.Entity.AmigosPK;
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
        
        for (Amigos someFriend : friendsList) {
            
            Usuario aFriend = someFriend.getAmigo();
            usrFriends.add(new UserView(aFriend.getIdUsuario(), aFriend.getAutenticacion().getCorreo(), aFriend.getAliasUsuario(), aFriend.getNombreUsuario(), aFriend.getApellidoUsuario()));
        }
        
        return usrFriends;
    }    
    
    public java.util.ArrayList<UserView> getSuggestedFriends(Integer usrId){
        java.util.ArrayList<UserView> suggestedFriends = new java.util.ArrayList<UserView>();
        java.util.ArrayList<UserView> usrFriends = new java.util.ArrayList<>();
        
        
        
        
        return suggestedFriends;
    }
    
    public boolean sendFriendRequest(Integer usrId, Integer frdId){
        boolean value = false;
        
        java.util.List<Amigos> friendsList = (new FriendsDAO()).getFriendsFrom(usrId);
        
        Amigos friends = new Amigos(new AmigosPK(usrId,frdId), FriendsDAO.Friends);
        Amigos pending = new Amigos(new AmigosPK(usrId,frdId), FriendsDAO.Pending);
        Amigos blocked = new Amigos(new AmigosPK(usrId,frdId), FriendsDAO.Blocked);
        
        if(!friendsList.contains(friends) && !friendsList.contains(pending) && !friendsList.contains(blocked)){
            value = (new FriendsDAO()).persist(pending);
        }
        
        return value;
    }
    
    public boolean answerFriendRequest(Integer usrId, Integer senderId, boolean didAccept){
        boolean value = false;
        
        if(didAccept){
            Amigos friends = new Amigos(new AmigosPK(usrId, senderId), FriendsDAO.Friends);            
            value = (new FriendsDAO()).persist(friends);
        }else{
            /*
             * Se elimina la relación
             */
        }
        
        return value;
    }
}
