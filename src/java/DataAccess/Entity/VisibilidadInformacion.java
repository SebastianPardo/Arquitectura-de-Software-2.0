/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arqsoft2017i
 */
@Entity
@Table(name = "VISIBILIDAD_INFORMACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VisibilidadInformacion.findAll", query = "SELECT v FROM VisibilidadInformacion v")
    , @NamedQuery(name = "VisibilidadInformacion.findByIdUsuario", query = "SELECT v FROM VisibilidadInformacion v WHERE v.idUsuario = :idUsuario")
    , @NamedQuery(name = "VisibilidadInformacion.findByVisAlias", query = "SELECT v FROM VisibilidadInformacion v WHERE v.visAlias = :visAlias")
    , @NamedQuery(name = "VisibilidadInformacion.findByVisCorreo", query = "SELECT v FROM VisibilidadInformacion v WHERE v.visCorreo = :visCorreo")
    , @NamedQuery(name = "VisibilidadInformacion.findByVisSexo", query = "SELECT v FROM VisibilidadInformacion v WHERE v.visSexo = :visSexo")
    , @NamedQuery(name = "VisibilidadInformacion.findByVisTelefono", query = "SELECT v FROM VisibilidadInformacion v WHERE v.visTelefono = :visTelefono")
    , @NamedQuery(name = "VisibilidadInformacion.findByVisFechaNac", query = "SELECT v FROM VisibilidadInformacion v WHERE v.visFechaNac = :visFechaNac")})
public class VisibilidadInformacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Column(name = "VIS_ALIAS")
    private Integer visAlias;
    @Column(name = "VIS_CORREO")
    private Integer visCorreo;
    @Column(name = "VIS_SEXO")
    private Integer visSexo;
    @Column(name = "VIS_TELEFONO")
    private Integer visTelefono;
    @Column(name = "VIS_FECHA_NAC")
    private Integer visFechaNac;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public VisibilidadInformacion() {
    }

    public VisibilidadInformacion(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getVisAlias() {
        return visAlias;
    }

    public void setVisAlias(Integer visAlias) {
        this.visAlias = visAlias;
    }

    public Integer getVisCorreo() {
        return visCorreo;
    }

    public void setVisCorreo(Integer visCorreo) {
        this.visCorreo = visCorreo;
    }

    public Integer getVisSexo() {
        return visSexo;
    }

    public void setVisSexo(Integer visSexo) {
        this.visSexo = visSexo;
    }

    public Integer getVisTelefono() {
        return visTelefono;
    }

    public void setVisTelefono(Integer visTelefono) {
        this.visTelefono = visTelefono;
    }

    public Integer getVisFechaNac() {
        return visFechaNac;
    }

    public void setVisFechaNac(Integer visFechaNac) {
        this.visFechaNac = visFechaNac;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VisibilidadInformacion)) {
            return false;
        }
        VisibilidadInformacion other = (VisibilidadInformacion) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.VisibilidadInformacion[ idUsuario=" + idUsuario + " ]";
    }
    
}
