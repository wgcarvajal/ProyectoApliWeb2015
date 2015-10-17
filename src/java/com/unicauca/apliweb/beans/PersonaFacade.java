/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.beans;

import com.unicauca.apliweb.entities.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author geovanny
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {
    @PersistenceContext(unitName = "ProyectoApliWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    public boolean existsPerson(String perUser) {
        try
        {            
            TypedQuery<Persona> query =em.createNamedQuery("Persona.findByPeruser", Persona.class);
            query.setParameter("peruser", perUser);            
            query.getSingleResult();
            return true;
        }
        catch(NoResultException ex)
        {
            return false;
        }
        //return em.find(Persona.class, perid)!=null;
    }

    public void registrar(Persona persona) {
        em.persist(persona);
    }

    public List<Persona> buscarPorAdministradores() 
    {   
        List <Persona>resultList=new ArrayList();
        Query query = getEntityManager().createNamedQuery("Persona.findAll",Persona.class);       
        List<Persona> resultquery = query.getResultList();
        for(Persona p:resultquery)
        {
            getEntityManager().refresh(p);
            if(!(p.getPersonagrupoList().isEmpty()) && p.getPersonagrupoList().get(0).getGrupo().getGruid().equals("admin"))
            {
                resultList.add(p);
            }
        }
        return resultList;
    }

    public List<Persona> buscarPorEmpleados() {
       List <Persona>resultList=new ArrayList();
        Query query = getEntityManager().createNamedQuery("Persona.findAll",Persona.class);       
        List<Persona> resultquery = query.getResultList();
        for(Persona p:resultquery)
        {
            getEntityManager().refresh(p);
            if(!(p.getPersonagrupoList().isEmpty()) && (p.getPersonagrupoList().get(0)).getGrupo().getGruid().equals("empl"))
            {
                resultList.add(p);
            }
        }
        return resultList;
    }

    public List<Persona> buscarPorUsuarios() 
    {        
        List <Persona>resultList=new ArrayList();
        Query query = getEntityManager().createNamedQuery("Persona.findAll",Persona.class);       
        List<Persona> resultquery = query.getResultList();
        System.out.println("Tamaño:"+resultquery.size());
        for(Persona p:resultquery)
        {
            getEntityManager().refresh(p);
            if(!(p.getPersonagrupoList().isEmpty()) && (p.getPersonagrupoList().get(0)).getPersonagrupoPK().getGruid().equals("user"))
            {
                resultList.add(p);
            }
        }
        return resultList;
    }

    public List<Persona> buscarPorNombreAdministrador(String nombrePersona)
    {
        List <Persona>resultList=new ArrayList();
        Query query = getEntityManager().createNamedQuery("Persona.findByName");
        query.setParameter("nombre","%"+nombrePersona.toLowerCase()+"%");
        List<Persona> resultquery = query.getResultList();
        System.out.println("tamaño:"+resultquery.size());
        for(Persona p:resultquery)
        {
            getEntityManager().refresh(p);
            if(!(p.getPersonagrupoList().isEmpty()) && (p.getPersonagrupoList().get(0)).getGrupo().getGruid().equals("admin"))
            {
                resultList.add(p);
            }
        }
        return resultList;        
    }

    public List<Persona> busacarPorNombreEmpleado(String nombrePersona) 
    {
        List <Persona>resultList=new ArrayList();
        Query query = getEntityManager().createNamedQuery("Persona.findByName");
        query.setParameter("nombre","%"+nombrePersona.toLowerCase()+"%");
        List<Persona> resultquery = query.getResultList();
        System.out.println("tamaño:"+resultquery.size());
        for(Persona p:resultquery)
        {
            getEntityManager().refresh(p);
            if(!(p.getPersonagrupoList().isEmpty()) && (p.getPersonagrupoList().get(0)).getGrupo().getGruid().equals("empl"))
            {
                resultList.add(p);
            }
        }
        return resultList; 
    }

    public List<Persona> busacarPorNombreUsuario(String nombrePersona) 
    {
        List <Persona>resultList=new ArrayList();
        Query query = getEntityManager().createNamedQuery("Persona.findByName");
        query.setParameter("nombre","%"+nombrePersona.toLowerCase()+"%");
        List<Persona> resultquery = query.getResultList();
        System.out.println("tamaño:"+resultquery.size());
        for(Persona p:resultquery)
        {
            getEntityManager().refresh(p);
            if(!(p.getPersonagrupoList().isEmpty()) && (p.getPersonagrupoList().get(0)).getPersonagrupoPK().getGruid().equals("user"))
            {
                resultList.add(p);
            }
        }
        return resultList; 
    }
    public List<Persona> buscarPendientes() {
       TypedQuery<Persona> query=em.createNamedQuery("Persona.findByPendientes",Persona.class);
       return query.getResultList();
    }        
}
