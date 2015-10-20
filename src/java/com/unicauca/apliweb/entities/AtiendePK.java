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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author miguel
 */
@Embeddable
public class AtiendePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "INCID")
    private int incid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERID")
    private int perid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public AtiendePK() {
    }

    public AtiendePK(int incid, int perid, Date fecha) {
        this.incid = incid;
        this.perid = perid;
        this.fecha = fecha;
    }

    public int getIncid() {
        return incid;
    }

    public void setIncid(int incid) {
        this.incid = incid;
    }

    public int getPerid() {
        return perid;
    }

    public void setPerid(int perid) {
        this.perid = perid;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) incid;
        hash += (int) perid;
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtiendePK)) {
            return false;
        }
        AtiendePK other = (AtiendePK) object;
        if (this.incid != other.incid) {
            return false;
        }
        if (this.perid != other.perid) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.AtiendePK[ incid=" + incid + ", perid=" + perid + ", fecha=" + fecha + " ]";
    }
    
}
