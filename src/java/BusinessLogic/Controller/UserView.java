/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import java.util.ArrayList;

/**
 *
 * @author arqsoft2017i
 */
public class UserView {
    
    public UserView(){
        this(null);
    }
    
    public UserView(DataAccess.Entity.Usuario usrData){
        this.usrData = usrData;
    }
        
    public Integer getUsrId(){
        Integer value = noUsrId;
        
        if(usrData != null){
            value = usrData.getIdUsuario();
        }
        
        return value;
    }
    
    public void setUsrId(Integer aUsrId){
        if(usrData != null){
            usrData.setIdUsuario(aUsrId);
        }
    }
    
    public String getUsrMail(){
        String value = emptyString;
        
        if(usrData != null){
            value = usrData.getAutenticacion().getCorreo();
        }
        
        return value;
    }
    
    public void setUsrMail(String aUsrMail){
        if(usrData != null){
            usrData.getAutenticacion().setCorreo(aUsrMail);
        }
    }
    
    public String getUsrAlias(){
        String value = emptyString;
        
        if(usrData != null){
            value = usrData.getAliasUsuario();
        }
        
        return value;
    }
    
    public void setUsrAlias(String aUsrAlias){
        if(usrData != null){
            usrData.setAliasUsuario(aUsrAlias);
        }
    }
    
    public String getUsrName(){
        String value = emptyString;
        
        if(usrData != null){
            value = usrData.getNombreUsuario();
        }
        
        return value;
    }
    
    public void setUsrName(String aUsrName){
        if(usrData != null){
            usrData.setNombreUsuario(aUsrName);
        }
    }
    
    public String getUsrLastName(){
        String value = emptyString;
        
        if(usrData != null){
            value = usrData.getApellidoUsuario();
        }
        
        return value;
    }
    
    public void setUsrLastName(String aUsrLastName){
        if(usrData != null){
            usrData.setApellidoUsuario(aUsrLastName);
        }
    }
    
    public java.util.ArrayList<UserView> getUsrFriends(){
        if(usrFriends == null){
            if(usrData != null){
                usrFriends = AppController.create().loadFriendsFrom(usrData.getIdUsuario());
            }else{
                usrFriends = new ArrayList<>();
            }
        }
        
        return usrFriends;
    }
    
    public java.util.ArrayList<UserView> getUsrSuggestedFriends(){
        if(usrFriends == null){
            if(usrData != null){
                usrFriends = AppController.create().loadSugestedFriendsFrom(usrData.getIdUsuario());
            }else{
                usrFriends = new ArrayList<>();
            }
        }
        
        return usrFriends;
    }
    
    public void setUsrFriends(java.util.ArrayList<UserView> someFriends){
        if(usrFriends == null){
            usrFriends = someFriends;
        }
    }    
    
    private DataAccess.Entity.Usuario usrData;
    private java.util.ArrayList<UserView> usrFriends;
    
    public static final Integer noUsrId = -1;
    public static final String emptyString = "";
}
