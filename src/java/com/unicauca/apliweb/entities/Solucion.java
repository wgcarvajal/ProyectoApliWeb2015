/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author geovanny
 */
@Entity
@Table(name = "SOLUCION", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solucion.findAll", query = "SELECT s FROM Solucion s"),
    @NamedQuery(name = "Solucion.findBySolid", query = "SELECT s FROM Solucion s WHERE s.solid = :solid"),
    @NamedQuery(name = "Solucion.findBySolnombre", query = "SELECT s FROM Solucion s WHERE s.solnombre = :solnombre"),
    @NamedQuery(name = "Solucion.findBySoldescripcion", query = "SELECT s FROM Solucion s WHERE s.soldescripcion = :soldescripcion")})
public class Solucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SOLID")
    private Integer solid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SOLNOMBRE")
    private String solnombre;
    @Size(max = 1024)
    @Column(name = "SOLDESCRIPCION")
    private String soldescripcion;
    @ManyToMany(mappedBy = "solucionList")
    private List<Incidente> incidenteList;

    public Solucion() {
    }

    public Solucion(Integer solid) {
        this.solid = solid;
    }

    public Solucion(Integer solid, String solnombre) {
        this.solid = solid;
        this.solnombre = solnombre;
    }

    public Integer getSolid() {
        return solid;
    }

    public void setSolid(Integer solid) {
        this.solid = solid;
    }

    public String getSolnombre() {
        return solnombre;
    }

    public void setSolnombre(String solnombre) {
        this.solnombre = solnombre;
    }

    public String getSoldescripcion() {
        return soldescripcion;
    }

    public void setSoldescripcion(String soldescripcion) {
        this.soldescripcion = soldescripcion;
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
        hash += (solid != null ? solid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solucion)) {
            return false;
        }
        Solucion other = (Solucion) object;
        if ((this.solid == null && other.solid != null) || (this.solid != null && !this.solid.equals(other.solid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Solucion[ solid=" + solid + " ]";
    }
    
}
