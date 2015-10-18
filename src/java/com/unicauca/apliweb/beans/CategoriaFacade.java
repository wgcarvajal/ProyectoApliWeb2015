/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.beans;

import com.unicauca.apliweb.entities.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author miguel
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {
    @PersistenceContext(unitName = "ProyectoApliWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }

    public List<Categoria> obtnCategorias() {
        TypedQuery<Categoria> query=em.createNamedQuery("Categoria.findAll", Categoria.class);
        return query.getResultList();
    }

    public Categoria obtnCategoria(int idCatSeleccionada) {
        return em.find(Categoria.class, idCatSeleccionada);
    }
    
}
