/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.beans;

import com.unicauca.apliweb.entities.Grupo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author geovanny
 */
@Stateless
public class GrupoFacade extends AbstractFacade<Grupo> {
    @PersistenceContext(unitName = "ProyectoApliWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }

    public List<Grupo> obtnGrupos() {
        try
        {
            TypedQuery query=em.createNamedQuery("Grupo.findAll",Grupo.class);            
            return query.getResultList();
        }
        catch(Exception ex)
        {            
            return null;
        }
    }
    
}
