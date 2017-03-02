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
@Table(name = "PERMISOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p")
    , @NamedQuery(name = "Permisos.findByIdPermiso", query = "SELECT p FROM Permisos p WHERE p.idPermiso = :idPermiso")
    , @NamedQuery(name = "Permisos.findByDescripcionPermiso", query = "SELECT p FROM Permisos p WHERE p.descripcionPermiso = :descripcionPermiso")
    , @NamedQuery(name = "Permisos.findByFechaRegPermiso", query = "SELECT p FROM Permisos p WHERE p.fechaRegPermiso = :fechaRegPermiso")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERMISO")
    private Integer idPermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "DESCRIPCION_PERMISO")
    private String descripcionPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REG_PERMISO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegPermiso;
    @ManyToMany(mappedBy = "permisosCollection")
    private Collection<Roles> rolesCollection;

    public Permisos() {
    }

    public Permisos(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Permisos(Integer idPermiso, String descripcionPermiso, Date fechaRegPermiso) {
        this.idPermiso = idPermiso;
        this.descripcionPermiso = descripcionPermiso;
        this.fechaRegPermiso = fechaRegPermiso;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }

    public Date getFechaRegPermiso() {
        return fechaRegPermiso;
    }

    public void setFechaRegPermiso(Date fechaRegPermiso) {
        this.fechaRegPermiso = fechaRegPermiso;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Permisos[ idPermiso=" + idPermiso + " ]";
    }
    
}
