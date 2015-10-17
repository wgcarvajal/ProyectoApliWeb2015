/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author miguel
 */
@Embeddable
public class EnviacorreoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERFKREMITENTE")
    private int perfkremitente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERFKDESTINATARIO")
    private int perfkdestinatario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INCID")
    private int incid;

    public EnviacorreoPK() {
    }

    public EnviacorreoPK(int perfkremitente, int perfkdestinatario, int incid) {
        this.perfkremitente = perfkremitente;
        this.perfkdestinatario = perfkdestinatario;
        this.incid = incid;
    }

    public int getPerfkremitente() {
        return perfkremitente;
    }

    public void setPerfkremitente(int perfkremitente) {
        this.perfkremitente = perfkremitente;
    }

    public int getPerfkdestinatario() {
        return perfkdestinatario;
    }

    public void setPerfkdestinatario(int perfkdestinatario) {
        this.perfkdestinatario = perfkdestinatario;
    }

    public int getIncid() {
        return incid;
    }

    public void setIncid(int incid) {
        this.incid = incid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) perfkremitente;
        hash += (int) perfkdestinatario;
        hash += (int) incid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnviacorreoPK)) {
            return false;
        }
        EnviacorreoPK other = (EnviacorreoPK) object;
        if (this.perfkremitente != other.perfkremitente) {
            return false;
        }
        if (this.perfkdestinatario != other.perfkdestinatario) {
            return false;
        }
        if (this.incid != other.incid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.EnviacorreoPK[ perfkremitente=" + perfkremitente + ", perfkdestinatario=" + perfkdestinatario + ", incid=" + incid + " ]";
    }
    
}
