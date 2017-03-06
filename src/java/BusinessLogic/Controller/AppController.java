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
    
    public static AppController getInstance(){        
        if(instance == null){
            instance = new AppController();
        }        
        return instance;
    }
    
    public Integer login(String mail, String pass){
        return usrManager.login(mail, pass);
    }
    
    public UserView loadUser(Integer usrId){        
        return usrManager.loadUser(usrId);
    }
    
    public java.util.ArrayList<UserView> loadAllUsers(){
        return usrManager.loadAllUsers();
    }
    
    
    public java.util.ArrayList<UserView> loadFriendsFrom(Integer usrId){
        return frdManager.getFriendsFrom(usrId);
    }
    
    public java.util.ArrayList<UserView> loadSugestedFriendsFrom(Integer usrId){
        return frdManager.getSuggestedFriendsFrom(usrId);
    }
    
    public UserManager getUserManager(){
        return usrManager;
    }
    
    public FriendsManager getFriendsManager(){
        return frdManager;
    }
    
    
    private static AppController appController;
    public boolean addFriend(Integer usrId, Integer frdId) {
        return frdManager.sendFriendRequest(usrId, frdId);
    }
    
    public boolean deleteFriend(Integer usrId, Integer frdId) {
        return frdManager.deleteFriend(usrId, frdId);
    }
    
    public boolean blockFriend(Integer usrId, Integer frdId) {
        return frdManager.blockUser(usrId, frdId);
    }
        
    private UserManager usrManager;
    private FriendsManager frdManager;
    private ProfileManager pflManager;
    
    private static AppController instance;
}
