/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

/**
 *
 * @author arqsoft2017i
 */
public class AppController{
    
    private AppController (){
        frdManager = new FriendsManager();
        usrManager = new UserManager();
        pflManager = new ProfileManager();
    }
    
    public static AppController create(){
        return new AppController();
    }
    
    public Integer login(String mail, String pass){
        return usrManager.login(mail, pass);
    }
    
    public UserView loadUser(Integer usrId){
        return usrManager.loadUser(usrId);
    }
    
    private UserManager usrManager;
    private FriendsManager frdManager;
    private ProfileManager pflManager;
}