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
import javax.validation.constraints.Size;

/**
 *
 * @author geovanny
 */
@Embeddable
public class PersonagrupoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GRUID")
    private String gruid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERID")
    private int perid;

    public PersonagrupoPK() {
    }

    public PersonagrupoPK(String gruid, int perid) {
        this.gruid = gruid;
        this.perid = perid;
    }

    public String getGruid() {
        return gruid;
    }

    public void setGruid(String gruid) {
        this.gruid = gruid;
    }

    public int getPerid() {
        return perid;
    }

    public void setPerid(int perid) {
        this.perid = perid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruid != null ? gruid.hashCode() : 0);
        hash += (int) perid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonagrupoPK)) {
            return false;
        }
        PersonagrupoPK other = (PersonagrupoPK) object;
        if ((this.gruid == null && other.gruid != null) || (this.gruid != null && !this.gruid.equals(other.gruid))) {
            return false;
        }
        if (this.perid != other.perid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.PersonagrupoPK[ gruid=" + gruid + ", perid=" + perid + " ]";
    }
    
}
