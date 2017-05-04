/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Service;

import BusinessLogic.Controller.AppController;
import BusinessLogic.Controller.UserView;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author DRAIKO
 */
@WebService(serviceName = "Friends")
public class Friends {

    /**
     * Retorna una lista de los amigos del usuario
     * @param id of the user.
     * @return int of ids as arraylist.
     */
    @WebMethod(operationName = "getFriends")
//    public java.util.ArrayList<Integer> getFriends(int id) {
//        java.util.ArrayList<Integer> ids = new ArrayList<>();
//        java.util.ArrayList<UserView> amigos = AppController.getInstance().loadFriendsFrom(id);
//        amigos.forEach((usr) -> {
//            ids.add(usr.getUsrId());
//        });
//        return ids;
//    }
      public java.util.ArrayList<UserView> getFriends(int id) {
          java.util.ArrayList<UserView> amigos = AppController.getInstance().loadFriendsFrom(id);
          return amigos;
}
}