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
public class RespondePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERID")
    private int perid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PREID")
    private int preid;

    public RespondePK() {
    }

    public RespondePK(int perid, int preid) {
        this.perid = perid;
        this.preid = preid;
    }

    public int getPerid() {
        return perid;
    }

    public void setPerid(int perid) {
        this.perid = perid;
    }

    public int getPreid() {
        return preid;
    }

    public void setPreid(int preid) {
        this.preid = preid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) perid;
        hash += (int) preid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespondePK)) {
            return false;
        }
        RespondePK other = (RespondePK) object;
        if (this.perid != other.perid) {
            return false;
        }
        if (this.preid != other.preid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.RespondePK[ perid=" + perid + ", preid=" + preid + " ]";
    }
    
}
