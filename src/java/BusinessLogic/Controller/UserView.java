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
public class UserView {
    
    public UserView(){
        this(noUsrId, emptyString, emptyString, emptyString, emptyString);
    }
    
    public UserView(Integer aUsrId, String aUsrMail, String aUsrAlias, String aUsrName, String aUsrLastName){
        usrId = aUsrId;
        usrMail = aUsrMail;
        usrAlias = aUsrAlias;
        usrName = aUsrName;
        usrLastName = aUsrLastName;
    }
        
    public Integer getUsrId(){
        return usrId;
    }
    
    public void setUsrId(Integer aUsrId){
        if(usrId == noUsrId){
            usrId = aUsrId;
        }
    }
    
    public String getUsrMail(){
        return usrMail;
    }
    
    public void setUsrMail(String aUsrMail){
        if(usrMail == emptyString){
            usrMail = aUsrMail;
        }
    }
    
    public String getUsrAlias(){
        return usrAlias;
    }
    
    public void setUsrAlias(String aUsrAlias){
        if(usrAlias == emptyString){
            usrAlias = aUsrAlias;
        }
    }
    
    public String getUsrName(){
        return usrName;
    }
    
    public void setUsrName(String aUsrAlias){
        if(usrName == emptyString){
            usrName = aUsrAlias;
        }
    }
    
    public String getUsrLastName(){
        return usrLastName;
    }
    
    public void setUsrLastName(String aUsrAlias){
        if(usrLastName == emptyString){
            usrLastName = aUsrAlias;
        }
    }
    
    private Integer usrId;
    private String usrMail;    
    private String usrAlias;
    private String usrName;
    private String usrLastName;
    
    public static final Integer noUsrId = -1;
    public static final String emptyString = "";
}
