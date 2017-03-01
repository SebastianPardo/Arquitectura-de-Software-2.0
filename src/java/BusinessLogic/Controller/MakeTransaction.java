package BusinessLogic.Controller ; 


import DataAccess.DAO.AccountDAO ; 
import DataAccess.DAO.TransactionDAO 
 ; 
import DataAccess.Entity.Account ; 
import DataAccess.Entity.Transaction ; 
import java.util.Date ; 

/**
 *
 * @author arqsoft2017i
 */ 
public class MakeTransaction { 
    
    public ResponseMessage make ( String password, Long accountNumber, Long accountNumberReceiver, int value ) {        
        
        AccountDAO accountDAO = new AccountDAO ( ) ; 
        Account accountBuyer = accountDAO. searchByAccountNumber ( accountNumber ) ; 
        Account accountSecurity = accountDAO. searchByAccountNumber ( accountNumberReceiver ) ; 
        
        ResponseMessage rm = new ResponseMessage ( ) ; 

        if ( accountBuyer != null ) { 
            if ( accountBuyer. getPassword ( ) . equals ( password ) ) { 
                if ( accountBuyer. getBalance ( ) > value ) { 
                    accountBuyer. setBalance ( accountBuyer. getBalance ( ) - value ) ; 
                    accountSecurity. setBalance ( accountSecurity. getBalance ( ) + value ) ; 

                    Transaction transaction = new Transaction ( ) ; 
                    transaction. setAccountaccountNumber ( accountBuyer ) ; 
                    transaction. setDate ( new Date ( ) ) ; 
                    transaction. setAmount ( value ) ; 

                    TransactionDAO transactionDAO = new TransactionDAO ( ) ; 
                    Transaction newTransaction = transactionDAO. persist ( transaction ) ; 

                    accountDAO. edit ( accountBuyer ) ; 
                    accountDAO. edit ( accountSecurity ) ; 

                    rm. setSuccess ( true ) ; 
                    rm. setErr_message ( "Transacción Satisfactoria" ) ; 
                    rm. setData ( newTransaction. getTransactionNumber ( ) ) ; 
                    
                    return rm ; 

                } else {     
                    rm. setSuccess ( false ) ; 
                    rm. setErr_message ( "Saldo insuficiente, su saldo es $" + accountBuyer. getBalance ( ) + "." ) ; 
                    return rm ; 
                } 
            } else { 
                rm. setSuccess ( false ) ; 
                rm. setErr_message ( "Contraseña Incorrecta" ) ; 
                return rm ; 
            } 
        } else {      
            rm. setSuccess ( false ) ; 
            rm. setErr_message ( "Cuenta Inexistente" ) ; 
            return rm ;  
        } 
    }   
    
} 
