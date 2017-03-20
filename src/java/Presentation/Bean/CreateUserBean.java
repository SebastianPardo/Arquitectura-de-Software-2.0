/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

/**
 *
 * @author arqsoft2017i
 */
import BusinessLogic.Controller.AppController;
import BusinessLogic.Controller.UserManager;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.RollbackException;

@ManagedBean
@ViewScoped
public class CreateUserBean implements Serializable {

    private static final long serialVersionUID = -8255062366708463640L;

    private String nombreUsuario;
    private String apellidoUsuario;
    private String aliasUsuario;
    private String sexoUsuario;
    private String telefonoUsuario;
    private Date fechaNacimientoUsuario;
    private String pass;
    private String correo;
    private String message;
    private boolean hide = true;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public String getSexoUsuario() {
        return sexoUsuario;
    }

    public void setSexoUsuario(String sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public Date getFechaNacimientoUsuario() {
        return fechaNacimientoUsuario;
    }

    public void setFechaNacimientoUsuario(Date fechaNacimientoUsuario) {
        this.fechaNacimientoUsuario = fechaNacimientoUsuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public void validatePass(FacesContext context, UIComponent component, Object value) {
        String password = (String) value;
        UIInput confirmComponent = (UIInput) component.getAttributes().get("confirmPass");
        String confirm = (String) confirmComponent.getValue();

        if (password == null || password.isEmpty() || password.length() < 6) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de contraseña", "La contraseña debe tener entre 6 y 20 caracteres"));
        }
        if (!password.equals(confirm)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", "Los valores de las contraseñas deben coincidir"));
        }
    }

    public void createUser() {
        try {
            message = AppController.getInstance().getUserManager().createUser(nombreUsuario,
                    apellidoUsuario, aliasUsuario, sexoUsuario, telefonoUsuario,
                    true, fechaNacimientoUsuario, pass, correo);
        } catch (RollbackException ex) {
            FacesMessage mess= new FacesMessage(FacesMessage.SEVERITY_WARN, "El usuario ya existe", "El correo ingresado ya se ha usado");
            FacesContext.getCurrentInstance().addMessage(null, mess);
        }
        FacesMessage mess =  new FacesMessage(FacesMessage.SEVERITY_WARN, "", message);
        FacesContext.getCurrentInstance().addMessage(null,mess);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

}
