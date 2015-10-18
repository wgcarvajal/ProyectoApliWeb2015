/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.managedbean.incidentes;

import com.unicauca.apliweb.beans.CategoriaFacade;
import com.unicauca.apliweb.entities.Categoria;
import com.unicauca.apliweb.entities.Incidente;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author miguel
 */
@ManagedBean
@ViewScoped
public class RegistroIncidente {

    private Incidente incidente;
    private CategoriaFacade categoriaEJB;
    private List<Categoria> categoriasDisponibles;
    private int idCatSeleccionada;

    public int getIdCatSeleccionada() {
        return idCatSeleccionada;
    }

    public void setIdCatSeleccionada(int idCatSeleccionada) {
        this.idCatSeleccionada = idCatSeleccionada;
    }    
    
    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    public List<Categoria> getCategoriasDisponibles() {
        return categoriasDisponibles;
    }

    public void setCategoriasDisponibles(List<Categoria> categoriasDisponibles) {
        this.categoriasDisponibles = categoriasDisponibles;
    }
                        
    public RegistroIncidente() {
        categoriasDisponibles=categoriaEJB.obtnCategorias();
    }
    
}
