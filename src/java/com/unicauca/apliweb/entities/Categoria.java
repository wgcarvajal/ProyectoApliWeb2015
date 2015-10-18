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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "CATEGORIA", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByCatid", query = "SELECT c FROM Categoria c WHERE c.catid = :catid"),
    @NamedQuery(name = "Categoria.findByCatnombre", query = "SELECT c FROM Categoria c WHERE c.catnombre = :catnombre"),
    @NamedQuery(name = "Categoria.findByCatdescripcion", query = "SELECT c FROM Categoria c WHERE c.catdescripcion = :catdescripcion")})
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CATID")
    private Integer catid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "CATNOMBRE")
    private String catnombre;
    @Size(max = 2048)
    @Column(name = "CATDESCRIPCION")
    private String catdescripcion;
    @JoinTable(name = "DEFINE", joinColumns = {
        @JoinColumn(name = "CATID", referencedColumnName = "CATID")}, inverseJoinColumns = {
        @JoinColumn(name = "PREID", referencedColumnName = "PREID")})
    @ManyToMany
    private List<Preguntas> preguntasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private List<Incidente> incidenteList;

    public Categoria() {
    }

    public Categoria(Integer catid) {
        this.catid = catid;
    }

    public Categoria(Integer catid, String catnombre) {
        this.catid = catid;
        this.catnombre = catnombre;
    }

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getCatnombre() {
        return catnombre;
    }

    public void setCatnombre(String catnombre) {
        this.catnombre = catnombre;
    }

    public String getCatdescripcion() {
        return catdescripcion;
    }

    public void setCatdescripcion(String catdescripcion) {
        this.catdescripcion = catdescripcion;
    }

    @XmlTransient
    public List<Preguntas> getPreguntasList() {
        return preguntasList;
    }

    public void setPreguntasList(List<Preguntas> preguntasList) {
        this.preguntasList = preguntasList;
    }

    @XmlTransient
    public List<Incidente> getIncidenteList() {
        return incidenteList;
    }

    public void setIncidenteList(List<Incidente> incidenteList) {
        this.incidenteList = incidenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catid != null ? catid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.catid == null && other.catid != null) || (this.catid != null && !this.catid.equals(other.catid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Categoria[ catid=" + catid + " ]";
    }
    
}
