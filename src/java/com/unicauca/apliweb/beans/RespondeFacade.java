/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.beans;

import com.unicauca.apliweb.entities.Incidente;
import com.unicauca.apliweb.entities.Persona;
import com.unicauca.apliweb.entities.Preguntas;
import com.unicauca.apliweb.entities.Responde;
import com.unicauca.apliweb.entities.RespondePK;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miguel
 */
@Stateless
public class RespondeFacade extends AbstractFacade<Responde> {
    @PersistenceContext(unitName = "ProyectoApliWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RespondeFacade() {
        super(Responde.class);
    }

    public void guardarRespuesta(int incId, int perId, int pregId, String respuesta) {
        Responde resp=new Responde();
        RespondePK pk=new RespondePK();
        pk.setIncid(incId);
        pk.setPerid(perId);
        pk.setPreid(pregId);
        resp.setRespondePK(pk);        
        resp.setRespuesta(respuesta);
        em.persist(resp);        
    }
    
}
