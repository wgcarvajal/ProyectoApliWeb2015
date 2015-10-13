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
@Table(name = "RESPODE", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respode.findAll", query = "SELECT r FROM Respode r"),
    @NamedQuery(name = "Respode.findByPerid", query = "SELECT r FROM Respode r WHERE r.respodePK.perid = :perid"),
    @NamedQuery(name = "Respode.findByPreid", query = "SELECT r FROM Respode r WHERE r.respodePK.preid = :preid"),
    @NamedQuery(name = "Respode.findByResprespuesta", query = "SELECT r FROM Respode r WHERE r.resprespuesta = :resprespuesta")})
public class Respode implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespodePK respodePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "RESPRESPUESTA")
    private String resprespuesta;
    @JoinColumn(name = "PREID", referencedColumnName = "PREID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Preguntas preguntas;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;

    public Respode() {
    }

    public Respode(RespodePK respodePK) {
        this.respodePK = respodePK;
    }

    public Respode(RespodePK respodePK, String resprespuesta) {
        this.respodePK = respodePK;
        this.resprespuesta = resprespuesta;
    }

    public Respode(int perid, int preid) {
        this.respodePK = new RespodePK(perid, preid);
    }

    public RespodePK getRespodePK() {
        return respodePK;
    }

    public void setRespodePK(RespodePK respodePK) {
        this.respodePK = respodePK;
    }

    public String getResprespuesta() {
        return resprespuesta;
    }

    public void setResprespuesta(String resprespuesta) {
        this.resprespuesta = resprespuesta;
    }

    public Preguntas getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Preguntas preguntas) {
        this.preguntas = preguntas;
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
        hash += (respodePK != null ? respodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respode)) {
            return false;
        }
        Respode other = (Respode) object;
        if ((this.respodePK == null && other.respodePK != null) || (this.respodePK != null && !this.respodePK.equals(other.respodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Respode[ respodePK=" + respodePK + " ]";
    }
    
}
