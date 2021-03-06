/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguel
 */
@Entity
@Table(name = "ATIENDE", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atiende.findAll", query = "SELECT a FROM Atiende a"),
    @NamedQuery(name = "Atiende.findByIncid", query = "SELECT a FROM Atiende a WHERE a.atiendePK.incid = :incid"),
    @NamedQuery(name = "Atiende.findByPerid", query = "SELECT a FROM Atiende a WHERE a.atiendePK.perid = :perid"),
    @NamedQuery(name = "Atiende.findByFecha", query = "SELECT a FROM Atiende a WHERE a.atiendePK.fecha = :fecha")})
public class Atiende implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AtiendePK atiendePK;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "INCID", referencedColumnName = "INCID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Incidente incidente;

    public Atiende() {
    }

    public Atiende(AtiendePK atiendePK) {
        this.atiendePK = atiendePK;
    }

    public Atiende(int incid, int perid, Date fecha) {
        this.atiendePK = new AtiendePK(incid, perid, fecha);
    }

    public AtiendePK getAtiendePK() {
        return atiendePK;
    }

    public void setAtiendePK(AtiendePK atiendePK) {
        this.atiendePK = atiendePK;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atiendePK != null ? atiendePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atiende)) {
            return false;
        }
        Atiende other = (Atiende) object;
        if ((this.atiendePK == null && other.atiendePK != null) || (this.atiendePK != null && !this.atiendePK.equals(other.atiendePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Atiende[ atiendePK=" + atiendePK + " ]";
    }
    
}
