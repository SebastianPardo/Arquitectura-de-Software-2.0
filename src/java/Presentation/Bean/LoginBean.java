package Presentation.Bean ; 

import BusinessLogic.Controller.Login ; 
import java.io.Serializable ; 
import javax.faces.bean.ManagedBean ; 
import javax.faces.bean.ViewScoped ; 
import javax.inject.Named ; 

/**
 *
 * @author arqsoft2017i
 */ 
 
@Named ( value = "loginBean" ) 
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable { 
    
    private long document ; 
    private String password ; 
    private String message ; 

    public LoginBean ( ) { 
    } 

    public long getDocument ( ) { 
        return document ; 
    } 

    public void setDocument ( long document ) { 
        this . document = document ; 
    } 

    public String getPassword ( ) { 
        return password ; 
    } 

    public void setPassword ( String password ) { 
        this . password = password ; 
    } 

    public String getMessage ( ) { 
        return message ; 
    } 

    public void setMessage ( String message ) { 
        this . message = message ; 
    } 

    public void login ( ) { 
        Login login = new Login ( ) ; 
        message = login. login ( document,password ) ; 
    } 
    
} 
