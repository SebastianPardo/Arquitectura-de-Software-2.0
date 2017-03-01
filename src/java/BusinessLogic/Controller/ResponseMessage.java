package BusinessLogic.Controller ; 

/**
 *
 * @author arqsoft2017i
 */ 
 
public class ResponseMessage { 
    
    private boolean success ; 
    private String err_message ; 
    private Long data ; 

    public boolean isSuccess ( ) { 
        return success ; 
    } 

    public void setSuccess ( boolean success ) { 
        this . success = success ; 
    } 

    public String getErr_message ( ) { 
        return err_message ; 
    } 

    public void setErr_message ( String err_message ) { 
        this . err_message = err_message ; 
    } 

    public Long getData ( ) { 
        return data ; 
    } 

    public void setData ( Long data ) { 
        this . data = data ; 
    } 
    
}
