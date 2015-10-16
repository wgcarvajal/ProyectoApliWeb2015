/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.beans;

import com.unicauca.apliweb.entities.Personagrupo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author geovanny
 */
@Stateless
public class PersonagrupoFacade extends AbstractFacade<Personagrupo> {
    @PersistenceContext(unitName = "ProyectoApliWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonagrupoFacade() {
        super(Personagrupo.class);
    }
    
    
    public List<Personagrupo> buscarPorNombreUsuario(String nombreusuario)
    {
        System.out.println(nombreusuario);
        Query query = getEntityManager().createNamedQuery("Personagrupo.findByPeruser");
        query.setParameter("peruser", nombreusuario);
        List<Personagrupo> resultList = query.getResultList();
        return resultList;
    }
    
    public void registrar(Personagrupo entry)
    {
        em.persist(entry);
    }
    
}
