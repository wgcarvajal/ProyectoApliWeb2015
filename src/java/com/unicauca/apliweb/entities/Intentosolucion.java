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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author miguel
 */
@Entity
@Table(name = "INTENTOSOLUCION", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Intentosolucion.findAll", query = "SELECT i FROM Intentosolucion i"),
    @NamedQuery(name = "Intentosolucion.findByIntid", query = "SELECT i FROM Intentosolucion i WHERE i.intid = :intid"),
    @NamedQuery(name = "Intentosolucion.findByIntnombre", query = "SELECT i FROM Intentosolucion i WHERE i.intnombre = :intnombre"),
    @NamedQuery(name = "Intentosolucion.findByIntdescripcion", query = "SELECT i FROM Intentosolucion i WHERE i.intdescripcion = :intdescripcion"),
    @NamedQuery(name = "Intentosolucion.findByIntfecha", query = "SELECT i FROM Intentosolucion i WHERE i.intfecha = :intfecha")})
public class Intentosolucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "INTID")
    private Integer intid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "INTNOMBRE")
    private String intnombre;
    @Size(max = 2048)
    @Column(name = "INTDESCRIPCION")
    private String intdescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTFECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intfecha;
    @ManyToMany(mappedBy = "intentosolucionList")
    private List<Incidente> incidenteList;

    public Intentosolucion() {
    }

    public Intentosolucion(Integer intid) {
        this.intid = intid;
    }

    public Intentosolucion(Integer intid, String intnombre, Date intfecha) {
        this.intid = intid;
        this.intnombre = intnombre;
        this.intfecha = intfecha;
    }

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public String getIntnombre() {
        return intnombre;
    }

    public void setIntnombre(String intnombre) {
        this.intnombre = intnombre;
    }

    public String getIntdescripcion() {
        return intdescripcion;
    }

    public void setIntdescripcion(String intdescripcion) {
        this.intdescripcion = intdescripcion;
    }

    public Date getIntfecha() {
        return intfecha;
    }

    public void setIntfecha(Date intfecha) {
        this.intfecha = intfecha;
    }

    @XmlTransient
    public List<Incidente> getIncidenteList() {
        return incidenteList;
    }

    public void setIncidenteList(List<Incidente> incidenteList) {
        this.incidenteList = incidenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intid != null ? intid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intentosolucion)) {
            return false;
        }
        Intentosolucion other = (Intentosolucion) object;
        if ((this.intid == null && other.intid != null) || (this.intid != null && !this.intid.equals(other.intid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Intentosolucion[ intid=" + intid + " ]";
    }
    
}
