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
     * @return Friends as arraylist of UserView.
     */
    @WebMethod(operationName = "getFriends")
    public java.util.ArrayList<UserView> getFriends(int id) {
        return AppController.getInstance().loadFriendsFrom(id);
    }
}
