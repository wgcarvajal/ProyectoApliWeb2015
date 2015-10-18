/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguel
 */
@Entity
@Table(name = "INCIDENTE", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidente.findAll", query = "SELECT i FROM Incidente i"),
    @NamedQuery(name = "Incidente.findByIncid", query = "SELECT i FROM Incidente i WHERE i.incid = :incid"),
    @NamedQuery(name = "Incidente.findByIncnivel", query = "SELECT i FROM Incidente i WHERE i.incnivel = :incnivel"),
    @NamedQuery(name = "Incidente.findByIncfecharegistro", query = "SELECT i FROM Incidente i WHERE i.incfecharegistro = :incfecharegistro"),
    @NamedQuery(name = "Incidente.findByIncdescripcion", query = "SELECT i FROM Incidente i WHERE i.incdescripcion = :incdescripcion"),
    @NamedQuery(name = "Incidente.findByIncexperiencia", query = "SELECT i FROM Incidente i WHERE i.incexperiencia = :incexperiencia"),
    @NamedQuery(name = "Incidente.findByIncsolucionado", query = "SELECT i FROM Incidente i WHERE i.incsolucionado = :incsolucionado"),
    @NamedQuery(name = "Incidente.findByPerId", query = "SELECT i FROM Incidente i WHERE i.perid.perid = :perid"),
    @NamedQuery(name = "Incidente.findByPerIdPendientes", query = "SELECT i FROM Incidente i WHERE i.perid.perid = :perid AND i.incsolucionado = FALSE"),
    @NamedQuery(name = "Incidente.findByPerIdSolucionados", query = "SELECT i FROM Incidente i WHERE i.perid.perid = :perid AND i.incsolucionado = TRUE")})
public class Incidente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "INCID")
    private Integer incid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INCNIVEL")
    private int incnivel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INCFECHAREGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date incfecharegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "INCDESCRIPCION")
    private String incdescripcion;
    @Size(max = 2048)
    @Column(name = "INCEXPERIENCIA")
    private String incexperiencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INCSOLUCIONADO")
    private boolean incsolucionado;
    @JoinTable(name = "TIENE", joinColumns = {
        @JoinColumn(name = "INCID", referencedColumnName = "INCID")}, inverseJoinColumns = {
        @JoinColumn(name = "INTID", referencedColumnName = "INTID")})
    @ManyToMany
    private List<Intentosolucion> intentosolucionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incidente")
    private List<Atiende> atiendeList;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID")
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "CATID", referencedColumnName = "CATID")
    @ManyToOne(optional = false)
    private Categoria categoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incid")
    private List<Cambio> cambioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incidente")
    private List<Enviacorreo> enviacorreoList;

    public Incidente() {
    }

    public Incidente(Integer incid) {
        this.incid = incid;
    }

    public Incidente(Integer incid, int incnivel, Date incfecharegistro, String incdescripcion, boolean incsolucionado) {
        this.incid = incid;
        this.incnivel = incnivel;
        this.incfecharegistro = incfecharegistro;
        this.incdescripcion = incdescripcion;
        this.incsolucionado = incsolucionado;
    }

    public Integer getIncid() {
        return incid;
    }

    public void setIncid(Integer incid) {
        this.incid = incid;
    }

    public int getIncnivel() {
        return incnivel;
    }

    public void setIncnivel(int incnivel) {
        this.incnivel = incnivel;
    }

    public Date getIncfecharegistro() {
        return incfecharegistro;
    }

    public void setIncfecharegistro(Date incfecharegistro) {
        this.incfecharegistro = incfecharegistro;
    }

    public String getIncdescripcion() {
        return incdescripcion;
    }

    public void setIncdescripcion(String incdescripcion) {
        this.incdescripcion = incdescripcion;
    }

    public String getIncexperiencia() {
        return incexperiencia;
    }

    public void setIncexperiencia(String incexperiencia) {
        this.incexperiencia = incexperiencia;
    }

    public boolean getIncsolucionado() {
        return incsolucionado;
    }

    public void setIncsolucionado(boolean incsolucionado) {
        this.incsolucionado = incsolucionado;
    }

    @XmlTransient
    public List<Intentosolucion> getIntentosolucionList() {
        return intentosolucionList;
    }

    public void setIntentosolucionList(List<Intentosolucion> intentosolucionList) {
        this.intentosolucionList = intentosolucionList;
    }

    @XmlTransient
    public List<Atiende> getAtiendeList() {
        return atiendeList;
    }

    public void setAtiendeList(List<Atiende> atiendeList) {
        this.atiendeList = atiendeList;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona per) {
        this.persona = per;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria cat) {
        this.categoria = cat;
    }

    @XmlTransient
    public List<Cambio> getCambioList() {
        return cambioList;
    }

    public void setCambioList(List<Cambio> cambioList) {
        this.cambioList = cambioList;
    }

    @XmlTransient
    public List<Enviacorreo> getEnviacorreoList() {
        return enviacorreoList;
    }

    public void setEnviacorreoList(List<Enviacorreo> enviacorreoList) {
        this.enviacorreoList = enviacorreoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (incid != null ? incid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incidente)) {
            return false;
        }
        Incidente other = (Incidente) object;
        if ((this.incid == null && other.incid != null) || (this.incid != null && !this.incid.equals(other.incid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Incidente[ incid=" + incid + " ]";
    }
    
}
