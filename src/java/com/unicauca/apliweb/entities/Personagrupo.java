/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author geovanny
 */
@Entity
@Table(name = "PERSONAGRUPO", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personagrupo.findAll", query = "SELECT p FROM Personagrupo p"),
    @NamedQuery(name = "Personagrupo.findByGruid", query = "SELECT p FROM Personagrupo p WHERE p.personagrupoPK.gruid = :gruid"),
    @NamedQuery(name = "Personagrupo.findByPerid", query = "SELECT p FROM Personagrupo p WHERE p.personagrupoPK.perid = :perid"),
    @NamedQuery(name = "Personagrupo.findByPeruser", query = "SELECT p FROM Personagrupo p WHERE p.peruser = :peruser")})
public class Personagrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonagrupoPK personagrupoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PERUSER")
    private String peruser;
    @JoinColumn(name = "GRUID", referencedColumnName = "GRUID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Grupo grupo;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;

    public Personagrupo() {
    }

    public Personagrupo(PersonagrupoPK personagrupoPK) {
        this.personagrupoPK = personagrupoPK;
    }

    public Personagrupo(PersonagrupoPK personagrupoPK, String peruser) {
        this.personagrupoPK = personagrupoPK;
        this.peruser = peruser;
    }

    public Personagrupo(String gruid, int perid) {
        this.personagrupoPK = new PersonagrupoPK(gruid, perid);
    }
    
    public Personagrupo(String gruid, int perid, String perUser) {
        this.personagrupoPK = new PersonagrupoPK(gruid, perid);
        this.peruser=perUser;
    }

    public PersonagrupoPK getPersonagrupoPK() {
        return personagrupoPK;
    }

    public void setPersonagrupoPK(PersonagrupoPK personagrupoPK) {
        this.personagrupoPK = personagrupoPK;
    }

    public String getPeruser() {
        return peruser;
    }

    public void setPeruser(String peruser) {
        this.peruser = peruser;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personagrupoPK != null ? personagrupoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personagrupo)) {
            return false;
        }
        Personagrupo other = (Personagrupo) object;
        if ((this.personagrupoPK == null && other.personagrupoPK != null) || (this.personagrupoPK != null && !this.personagrupoPK.equals(other.personagrupoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Personagrupo[ personagrupoPK=" + personagrupoPK + " ]";
    }
    
}
