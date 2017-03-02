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
@Table(name = "CATEGORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria")
    , @NamedQuery(name = "Categoria.findByDescripcionCategoria", query = "SELECT c FROM Categoria c WHERE c.descripcionCategoria = :descripcionCategoria")
    , @NamedQuery(name = "Categoria.findByFechaRegCategoria", query = "SELECT c FROM Categoria c WHERE c.fechaRegCategoria = :fechaRegCategoria")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "DESCRIPCION_CATEGORIA")
    private String descripcionCategoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REG_CATEGORIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegCategoria;
    @JoinTable(name = "INTERES_CATEGORIA", joinColumns = {
        @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_INTERES", referencedColumnName = "ID_INTERES")})
    @ManyToMany
    private Collection<Interes> interesCollection;

    public Categoria() {
    }

    public Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria(Integer idCategoria, String descripcionCategoria, Date fechaRegCategoria) {
        this.idCategoria = idCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.fechaRegCategoria = fechaRegCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public Date getFechaRegCategoria() {
        return fechaRegCategoria;
    }

    public void setFechaRegCategoria(Date fechaRegCategoria) {
        this.fechaRegCategoria = fechaRegCategoria;
    }

    @XmlTransient
    public Collection<Interes> getInteresCollection() {
        return interesCollection;
    }

    public void setInteresCollection(Collection<Interes> interesCollection) {
        this.interesCollection = interesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Categoria[ idCategoria=" + idCategoria + " ]";
    }
    
}
