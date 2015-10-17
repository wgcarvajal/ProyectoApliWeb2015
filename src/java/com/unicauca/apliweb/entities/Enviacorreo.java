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
@Table(name = "ENVIACORREO", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enviacorreo.findAll", query = "SELECT e FROM Enviacorreo e"),
    @NamedQuery(name = "Enviacorreo.findByPerfkremitente", query = "SELECT e FROM Enviacorreo e WHERE e.enviacorreoPK.perfkremitente = :perfkremitente"),
    @NamedQuery(name = "Enviacorreo.findByPerfkdestinatario", query = "SELECT e FROM Enviacorreo e WHERE e.enviacorreoPK.perfkdestinatario = :perfkdestinatario"),
    @NamedQuery(name = "Enviacorreo.findByIncid", query = "SELECT e FROM Enviacorreo e WHERE e.enviacorreoPK.incid = :incid"),
    @NamedQuery(name = "Enviacorreo.findByAsunto", query = "SELECT e FROM Enviacorreo e WHERE e.asunto = :asunto"),
    @NamedQuery(name = "Enviacorreo.findByMensaje", query = "SELECT e FROM Enviacorreo e WHERE e.mensaje = :mensaje"),
    @NamedQuery(name = "Enviacorreo.findByAdjunto", query = "SELECT e FROM Enviacorreo e WHERE e.adjunto = :adjunto")})
public class Enviacorreo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnviacorreoPK enviacorreoPK;
    @Size(max = 128)
    @Column(name = "ASUNTO")
    private String asunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4096)
    @Column(name = "MENSAJE")
    private String mensaje;
    @Size(max = 512)
    @Column(name = "ADJUNTO")
    private String adjunto;
    @JoinColumn(name = "INCID", referencedColumnName = "INCID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Incidente incidente;
    @JoinColumn(name = "PERFKREMITENTE", referencedColumnName = "PERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "PERFKDESTINATARIO", referencedColumnName = "PERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona1;

    public Enviacorreo() {
    }

    public Enviacorreo(EnviacorreoPK enviacorreoPK) {
        this.enviacorreoPK = enviacorreoPK;
    }

    public Enviacorreo(EnviacorreoPK enviacorreoPK, String mensaje) {
        this.enviacorreoPK = enviacorreoPK;
        this.mensaje = mensaje;
    }

    public Enviacorreo(int perfkremitente, int perfkdestinatario, int incid) {
        this.enviacorreoPK = new EnviacorreoPK(perfkremitente, perfkdestinatario, incid);
    }

    public EnviacorreoPK getEnviacorreoPK() {
        return enviacorreoPK;
    }

    public void setEnviacorreoPK(EnviacorreoPK enviacorreoPK) {
        this.enviacorreoPK = enviacorreoPK;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersona1() {
        return persona1;
    }

    public void setPersona1(Persona persona1) {
        this.persona1 = persona1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enviacorreoPK != null ? enviacorreoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enviacorreo)) {
            return false;
        }
        Enviacorreo other = (Enviacorreo) object;
        if ((this.enviacorreoPK == null && other.enviacorreoPK != null) || (this.enviacorreoPK != null && !this.enviacorreoPK.equals(other.enviacorreoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Enviacorreo[ enviacorreoPK=" + enviacorreoPK + " ]";
    }
    
}
