/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.FriendsDAO;
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
            usrFriends.add(new UserView(aFriend));
        }
        
        return usrFriends;
    }

    public java.util.ArrayList<UserView> getSuggestedFriendsFrom(Integer usrId){
        java.util.List<Usuario> friendsList = (new FriendsDAO()).getSugestedFriendsFrom(usrId);
        java.util.ArrayList<UserView> usrFriends = new java.util.ArrayList<>();
        
        for (Usuario someFriend : friendsList) {            
            usrFriends.add(new UserView(someFriend));
        }        
        return usrFriends;
    }    
    
    public boolean sendFriendRequest(Integer usrId, Integer frdId){
        java.util.List<Amigos> friendsList = (new FriendsDAO()).getFriendsFrom(usrId);
        
        Amigos friends = new Amigos(new AmigosPK(usrId,frdId), FriendsDAO.Friends);
        Amigos pending = new Amigos(new AmigosPK(usrId,frdId), FriendsDAO.Pending);
        Amigos blocked = new Amigos(new AmigosPK(usrId,frdId), FriendsDAO.Blocked);
        
        if(!friendsList.contains(friends) && !friendsList.contains(pending) && !friendsList.contains(blocked)){
            return (new FriendsDAO()).persist(pending);
        }
        
        return false;
    }
    
    public boolean answerFriendRequest(Integer usrId, Integer senderId, boolean didAccept){
        boolean value;
        
        Amigos friends = new Amigos(new AmigosPK(senderId, usrId), FriendsDAO.Friends); 
        
        if(didAccept){
            value = (new FriendsDAO()).persist(friends, new Amigos(new AmigosPK(usrId, senderId), FriendsDAO.Friends));
        }else{
            value = (new FriendsDAO()).remove(friends);
        }
        
        return value;
    }
    
    public boolean blockUser(Integer usrId, Integer blockedId){
        Amigos friends = new Amigos(new AmigosPK(usrId, blockedId), FriendsDAO.Blocked);
        
        boolean value = (new FriendsDAO()).editFriendship(friends);
        
        return value;   
    }
    
    public boolean deleteFriend(Integer usrId, Integer frdId){        
        Amigos friends1 = new Amigos(new AmigosPK(usrId, frdId), FriendsDAO.Friends);
        Amigos friends2 = new Amigos(new AmigosPK(frdId, usrId), FriendsDAO.Friends);
        FriendsDAO dao = new FriendsDAO();
        return dao.remove(friends1) && dao.remove(friends2);   
    }
}
