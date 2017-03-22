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

    public FriendsManager() {

    }

    public java.util.ArrayList<UserView> getFriendsFrom(Integer usrId, Integer state) {
        java.util.List<Amigos> friendsList = (new FriendsDAO()).getFriendsFrom(usrId, state);
        java.util.ArrayList<UserView> usrFriends = new java.util.ArrayList<>();

        for (Amigos someFriend : friendsList) {
            Usuario aFriend = someFriend.getAmigo();
            usrFriends.add(new UserView(aFriend));
        }
        return usrFriends;
    }

    public java.util.ArrayList<UserView> getRequestTo(Integer usrId, Integer state) {
        java.util.List<Amigos> friendsList = (new FriendsDAO()).getRequestsTo(usrId, state);
        java.util.ArrayList<UserView> usrFriends = new java.util.ArrayList<>();

        for (Amigos someFriend : friendsList) {
            Usuario aFriend = someFriend.getUsuario();
            usrFriends.add(new UserView(aFriend));
        }
        return usrFriends;
    }

    public java.util.ArrayList<UserView> getFriendsBloquedFrom(Integer usrId) {
        return getFriendsFrom(usrId, FriendsDAO.Blocked);
    }

    public java.util.ArrayList<UserView> getFriendsPendingSendedFrom(Integer usrId) {
        return getFriendsFrom(usrId, FriendsDAO.Pending);
    }

    public java.util.ArrayList<UserView> getFriendsAceptedFrom(Integer usrId) {
        return getFriendsFrom(usrId, FriendsDAO.Friends);
    }

    public java.util.ArrayList<UserView> getFriendsRequestsFrom(Integer usrId) {
        return getRequestTo(usrId, FriendsDAO.Pending);
    }

    public java.util.ArrayList<UserView> getSuggestedFriendsFrom(Integer usrId) {
        java.util.List<Usuario> friendsList = (new FriendsDAO()).getSuggestedFriendsFrom(usrId);
        java.util.ArrayList<UserView> usrFriends = new java.util.ArrayList<>();
        for (Usuario someFriend : friendsList) {
            usrFriends.add(new UserView(someFriend));
        }
        return usrFriends;
    }

    /**
     * Método usado para enviar una solicitud de amistad
     * <p>
     * Se reciben las ids de los usuarios,
     *
     * @param usrId ID del usuario
     * @param frdId ID del usuario al que se le envía la solicitud
     */
    public boolean sendFriendRequest(Integer usrId, Integer frdId) {
        java.util.List<Amigos> friendsList = (new FriendsDAO()).getFriendsFrom(usrId);

        Amigos friends = new Amigos(new AmigosPK(usrId, frdId), FriendsDAO.Friends);
        Amigos pending = new Amigos(new AmigosPK(usrId, frdId), FriendsDAO.Pending);
        Amigos blocked = new Amigos(new AmigosPK(usrId, frdId), FriendsDAO.Blocked);

        if (!friendsList.contains(friends) && !friendsList.contains(pending) && !friendsList.contains(blocked)) {
            return (new FriendsDAO()).persist(pending);
        }

        return false;
    }

    public boolean answerFriendRequest(Integer usrId, Integer senderId, boolean didAccept) {
        boolean value = false;
        FriendsDAO frDAO = new FriendsDAO();
        Amigos fr = frDAO.getFriendship(senderId, usrId);

        if (didAccept) {
            fr.setEstatusRelacion(FriendsDAO.Friends);
            value = frDAO.persist(fr, new Amigos(new AmigosPK(usrId, senderId), FriendsDAO.Friends));
        } else {
            value = frDAO.remove(fr);
        }

        return value;
    }

    public boolean blockUser(Integer usrId, Integer blockedId) {
        Amigos friends = new Amigos(new AmigosPK(usrId, blockedId), FriendsDAO.Blocked);

        boolean value = (new FriendsDAO()).editFriendship(friends);

        return value;
    }

    public boolean deleteFriend(Integer usrId, Integer frdId) {
        Amigos friends1 = new Amigos(new AmigosPK(usrId, frdId), FriendsDAO.Friends);
        Amigos friends2 = new Amigos(new AmigosPK(frdId, usrId), FriendsDAO.Friends);
        FriendsDAO dao = new FriendsDAO();
        return dao.remove(friends1) && dao.remove(friends2);
    }
}
