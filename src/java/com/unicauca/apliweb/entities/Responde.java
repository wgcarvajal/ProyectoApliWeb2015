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
 * @author miguel
 */
@Entity
@Table(name = "RESPONDE", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responde.findAll", query = "SELECT r FROM Responde r"),
    @NamedQuery(name = "Responde.findByPerid", query = "SELECT r FROM Responde r WHERE r.respondePK.perid = :perid"),
    @NamedQuery(name = "Responde.findByPreid", query = "SELECT r FROM Responde r WHERE r.respondePK.preid = :preid"),
    @NamedQuery(name = "Responde.findByRespuesta", query = "SELECT r FROM Responde r WHERE r.respuesta = :respuesta")})
public class Responde implements Serializable {
        
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected RespondePK respondePK;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "RESPUESTA")
    private String respuesta;
    
    @JoinColumn(name = "INCID", referencedColumnName = "INCID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Incidente incidente;
    
    @JoinColumn(name = "PREID", referencedColumnName = "PREID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Preguntas preguntas;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;

    public Responde() {
    }

    public Responde(RespondePK respondePK) {
        this.respondePK = respondePK;
    }

    public Responde(RespondePK respondePK, String respuesta) {
        this.respondePK = respondePK;
        this.respuesta = respuesta;
    }

    public Responde(int perid, int preid) {
        this.respondePK = new RespondePK(perid, preid);
    }

    public RespondePK getRespondePK() {
        return respondePK;
    }

    public void setRespondePK(RespondePK respondePK) {
        this.respondePK = respondePK;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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
        hash += (respondePK != null ? respondePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responde)) {
            return false;
        }
        Responde other = (Responde) object;
        if ((this.respondePK == null && other.respondePK != null) || (this.respondePK != null && !this.respondePK.equals(other.respondePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Responde[ respondePK=" + respondePK + " ]";
    }   

    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }
    
}
