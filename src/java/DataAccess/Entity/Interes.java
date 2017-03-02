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
@Table(name = "INTERES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interes.findAll", query = "SELECT i FROM Interes i")
    , @NamedQuery(name = "Interes.findByIdInteres", query = "SELECT i FROM Interes i WHERE i.idInteres = :idInteres")
    , @NamedQuery(name = "Interes.findByDescripcionInteres", query = "SELECT i FROM Interes i WHERE i.descripcionInteres = :descripcionInteres")
    , @NamedQuery(name = "Interes.findByFechaRegInteres", query = "SELECT i FROM Interes i WHERE i.fechaRegInteres = :fechaRegInteres")})
public class Interes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INTERES")
    private Integer idInteres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "DESCRIPCION_INTERES")
    private String descripcionInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REG_INTERES")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegInteres;
    @ManyToMany(mappedBy = "interesCollection")
    private Collection<Usuario> usuarioCollection;
    @ManyToMany(mappedBy = "interesCollection")
    private Collection<Categoria> categoriaCollection;

    public Interes() {
    }

    public Interes(Integer idInteres) {
        this.idInteres = idInteres;
    }

    public Interes(Integer idInteres, String descripcionInteres, Date fechaRegInteres) {
        this.idInteres = idInteres;
        this.descripcionInteres = descripcionInteres;
        this.fechaRegInteres = fechaRegInteres;
    }

    public Integer getIdInteres() {
        return idInteres;
    }

    public void setIdInteres(Integer idInteres) {
        this.idInteres = idInteres;
    }

    public String getDescripcionInteres() {
        return descripcionInteres;
    }

    public void setDescripcionInteres(String descripcionInteres) {
        this.descripcionInteres = descripcionInteres;
    }

    public Date getFechaRegInteres() {
        return fechaRegInteres;
    }

    public void setFechaRegInteres(Date fechaRegInteres) {
        this.fechaRegInteres = fechaRegInteres;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Categoria> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<Categoria> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInteres != null ? idInteres.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interes)) {
            return false;
        }
        Interes other = (Interes) object;
        if ((this.idInteres == null && other.idInteres != null) || (this.idInteres != null && !this.idInteres.equals(other.idInteres))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Interes[ idInteres=" + idInteres + " ]";
    }
    
}
