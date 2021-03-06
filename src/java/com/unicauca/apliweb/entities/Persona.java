/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguel
 */
@Entity
@Table(name = "PERSONA", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByPerid", query = "SELECT p FROM Persona p WHERE p.perid = :perid"),
    @NamedQuery(name = "Persona.findByPernombre", query = "SELECT p FROM Persona p WHERE p.pernombre = :pernombre"),
    @NamedQuery(name = "Persona.findByPerapellido", query = "SELECT p FROM Persona p WHERE p.perapellido = :perapellido"),
    @NamedQuery(name = "Persona.findByPeruser", query = "SELECT p FROM Persona p WHERE p.peruser = :peruser"),
    @NamedQuery(name = "Persona.findByPerpassword", query = "SELECT p FROM Persona p WHERE p.perpassword = :perpassword"),
    @NamedQuery(name = "Persona.findByPeremail", query = "SELECT p FROM Persona p WHERE p.peremail = :peremail"),
    @NamedQuery(name = "Persona.findByName", query = "SELECT p FROM Persona p WHERE LOWER(CONCAT(CONCAT(p.pernombre,' '),p.perapellido)) LIKE :nombre "),
    @NamedQuery(name = "Persona.findByGruid", query = "SELECT p FROM Persona p INNER JOIN Personagrupo pg"),
    @NamedQuery(name = "Persona.findByPendientes", query = "SELECT p FROM Persona p WHERE p.personagrupoList IS EMPTY") })
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PERID")
    private Integer perid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PERNOMBRE")
    private String pernombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PERAPELLIDO")
    private String perapellido;
    @Size(max = 50)
    @Column(name = "PERUSER")
    private String peruser;
    @Size(max = 256)
    @Column(name = "PERPASSWORD")
    private String perpassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PEREMAIL")
    private String peremail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<Atiende> atiendeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<Incidente> incidenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<Personagrupo> personagrupoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remitente")
    private List<Enviacorreo> remitentesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinatario")
    private List<Enviacorreo> destinatariosList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<Responde> respondeList;

    public Persona() {
    }

    public Persona(Integer perid) {
        this.perid = perid;
    }

    public Persona(Integer perid, String pernombre, String perapellido, String peremail) {
        this.perid = perid;
        this.pernombre = pernombre;
        this.perapellido = perapellido;
        this.peremail = peremail;
    }

    public Integer getPerid() {
        return perid;
    }

    public void setPerid(Integer perid) {
        this.perid = perid;
    }

    public String getPernombre() {
        return pernombre;
    }

    public void setPernombre(String pernombre) {
        this.pernombre = pernombre;
    }

    public String getPerapellido() {
        return perapellido;
    }

    public void setPerapellido(String perapellido) {
        this.perapellido = perapellido;
    }

    public String getPeruser() {
        return peruser;
    }

    public void setPeruser(String peruser) {
        this.peruser = peruser;
    }

    public String getPerpassword() {
        return perpassword;
    }

    public void setPerpassword(String perpassword) {
        this.perpassword = perpassword;
    }

    public String getPeremail() {
        return peremail;
    }

    public void setPeremail(String peremail) {
        this.peremail = peremail;
    }

    @XmlTransient
    public List<Atiende> getAtiendeList() {
        return atiendeList;
    }

    public void setAtiendeList(List<Atiende> atiendeList) {
        this.atiendeList = atiendeList;
    }

    @XmlTransient
    public List<Incidente> getIncidenteList() {
        return incidenteList;
    }

    public void setIncidenteList(List<Incidente> incidenteList) {
        this.incidenteList = incidenteList;
    }

    @XmlTransient
    public List<Personagrupo> getPersonagrupoList() {
        return personagrupoList;
    }

    public void setPersonagrupoList(List<Personagrupo> personagrupoList) {
        this.personagrupoList = personagrupoList;
    }

    @XmlTransient
    public List<Enviacorreo> getRemitentesList() {
        return remitentesList;
    }

    public void setRemitentesList(List<Enviacorreo> enviacorreoList) {
        this.remitentesList = enviacorreoList;
    }

    @XmlTransient
    public List<Enviacorreo> getDestinatariosList() {
        return destinatariosList;
    }

    public void setDestinatariosList(List<Enviacorreo> enviacorreoList) {
        this.destinatariosList = enviacorreoList;
    }

    @XmlTransient
    public List<Responde> getRespondeList() {
        return respondeList;
    }

    public void setRespondeList(List<Responde> respondeList) {
        this.respondeList = respondeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perid != null ? perid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.perid == null && other.perid != null) || (this.perid != null && !this.perid.equals(other.perid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Persona[ perid=" + perid + " ]";
    }
    
}
