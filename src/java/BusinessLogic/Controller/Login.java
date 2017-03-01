package BusinessLogic.Controller ; 

import DataAccess.DAO.AccountDAO ; 
import DataAccess.Entity.Account ; 
import java.io.Serializable ; 

/**
 *
 * @author arqsoft2017i
 */ 
public class Login implements Serializable { 
    public String login ( long document, String password ) { 
        AccountDAO accountDAO = new AccountDAO ( ) ; 
        Account accountE = accountDAO. searchUser ( document, password ) ; 
        if ( accountE != null ) 
            return "Welcome " + accountE. getName ( ) ; 
        else 
            return "User not found" ; 
    } 
} 
