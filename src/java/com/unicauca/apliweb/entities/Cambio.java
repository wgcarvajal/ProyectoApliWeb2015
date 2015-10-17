/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguel
 */
@Entity
@Table(name = "CAMBIO", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cambio.findAll", query = "SELECT c FROM Cambio c"),
    @NamedQuery(name = "Cambio.findByCamid", query = "SELECT c FROM Cambio c WHERE c.camid = :camid"),
    @NamedQuery(name = "Cambio.findByCamdescripcion", query = "SELECT c FROM Cambio c WHERE c.camdescripcion = :camdescripcion"),
    @NamedQuery(name = "Cambio.findByCamfecha", query = "SELECT c FROM Cambio c WHERE c.camfecha = :camfecha")})
public class Cambio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CAMID")
    private Integer camid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "CAMDESCRIPCION")
    private String camdescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAMFECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date camfecha;
    @JoinColumn(name = "INCID", referencedColumnName = "INCID")
    @ManyToOne(optional = false)
    private Incidente incid;

    public Cambio() {
    }

    public Cambio(Integer camid) {
        this.camid = camid;
    }

    public Cambio(Integer camid, String camdescripcion, Date camfecha) {
        this.camid = camid;
        this.camdescripcion = camdescripcion;
        this.camfecha = camfecha;
    }

    public Integer getCamid() {
        return camid;
    }

    public void setCamid(Integer camid) {
        this.camid = camid;
    }

    public String getCamdescripcion() {
        return camdescripcion;
    }

    public void setCamdescripcion(String camdescripcion) {
        this.camdescripcion = camdescripcion;
    }

    public Date getCamfecha() {
        return camfecha;
    }

    public void setCamfecha(Date camfecha) {
        this.camfecha = camfecha;
    }

    public Incidente getIncid() {
        return incid;
    }

    public void setIncid(Incidente incid) {
        this.incid = incid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (camid != null ? camid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cambio)) {
            return false;
        }
        Cambio other = (Cambio) object;
        if ((this.camid == null && other.camid != null) || (this.camid != null && !this.camid.equals(other.camid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Cambio[ camid=" + camid + " ]";
    }
    
}
