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
 * @author geovanny
 */
@Entity
@Table(name = "PREGUNTAS", catalog = "apliWeb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preguntas.findAll", query = "SELECT p FROM Preguntas p"),
    @NamedQuery(name = "Preguntas.findByPreid", query = "SELECT p FROM Preguntas p WHERE p.preid = :preid"),
    @NamedQuery(name = "Preguntas.findByPreinterrogante", query = "SELECT p FROM Preguntas p WHERE p.preinterrogante = :preinterrogante")})
public class Preguntas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PREID")
    private Integer preid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "PREINTERROGANTE")
    private String preinterrogante;
    @ManyToMany(mappedBy = "preguntasList")
    private List<Categoria> categoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntas")
    private List<Respode> respodeList;

    public Preguntas() {
    }

    public Preguntas(Integer preid) {
        this.preid = preid;
    }

    public Preguntas(Integer preid, String preinterrogante) {
        this.preid = preid;
        this.preinterrogante = preinterrogante;
    }

    public Integer getPreid() {
        return preid;
    }

    public void setPreid(Integer preid) {
        this.preid = preid;
    }

    public String getPreinterrogante() {
        return preinterrogante;
    }

    public void setPreinterrogante(String preinterrogante) {
        this.preinterrogante = preinterrogante;
    }

    @XmlTransient
    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    @XmlTransient
    public List<Respode> getRespodeList() {
        return respodeList;
    }

    public void setRespodeList(List<Respode> respodeList) {
        this.respodeList = respodeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preid != null ? preid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preguntas)) {
            return false;
        }
        Preguntas other = (Preguntas) object;
        if ((this.preid == null && other.preid != null) || (this.preid != null && !this.preid.equals(other.preid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.apliweb.entities.Preguntas[ preid=" + preid + " ]";
    }
    
}
