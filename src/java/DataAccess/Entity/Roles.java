/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author arqsoft2017i
 */
@Entity
@Table(name = "ROLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
    , @NamedQuery(name = "Roles.findByIdRol", query = "SELECT r FROM Roles r WHERE r.idRol = :idRol")
    , @NamedQuery(name = "Roles.findByDescripcionRol", query = "SELECT r FROM Roles r WHERE r.descripcionRol = :descripcionRol")
    , @NamedQuery(name = "Roles.findByFechaRegistroRol", query = "SELECT r FROM Roles r WHERE r.fechaRegistroRol = :fechaRegistroRol")})
public class Roles implements Serializable {

    @JoinTable(name = "USUARIO_ROL", joinColumns = {
        @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")})
    @ManyToMany
    private Collection<Usuario> usuarioCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROL")
    private Integer idRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "DESCRIPCION_ROL")
    private String descripcionRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO_ROL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistroRol;
    @JoinTable(name = "ROL_PERMISOS", joinColumns = {
        @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PERMISO", referencedColumnName = "ID_PERMISO")})
    @ManyToMany
    private Collection<Permisos> permisosCollection;

    public Roles() {
    }

    public Roles(Integer idRol) {
        this.idRol = idRol;
    }

    public Roles(Integer idRol, String descripcionRol, Date fechaRegistroRol) {
        this.idRol = idRol;
        this.descripcionRol = descripcionRol;
        this.fechaRegistroRol = fechaRegistroRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public Date getFechaRegistroRol() {
        return fechaRegistroRol;
    }

    public void setFechaRegistroRol(Date fechaRegistroRol) {
        this.fechaRegistroRol = fechaRegistroRol;
    }

    @XmlTransient
    public Collection<Permisos> getPermisosCollection() {
        return permisosCollection;
    }

    public void setPermisosCollection(Collection<Permisos> permisosCollection) {
        this.permisosCollection = permisosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Roles[ idRol=" + idRol + " ]";
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }
    
}
