package Presentation.Bean;

import BusinessLogic.Controller.HandleAccount;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
/**
 *
 * @author arqsoft2017i
 */
@ManagedBean
@ViewScoped
public class CreateAccountBean {
    
    private String name;
    private Long document;
    private String passwordAccount;
    private String message;

    public CreateAccountBean() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void createAccount(){
        HandleAccount createAccount = new HandleAccount();
        message = createAccount.createAccount(name, passwordAccount, document);
    }
    
}
