/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.urlsFiltros;

import com.unicauca.apliweb.beans.PersonagrupoFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebFilter("*.xhtml")
public class SesionUrlsFiltros implements Filter 
{
    @EJB
    private  PersonagrupoFacade    personaGrupoEJB;
    
    private String urlPrincipal="http://localhost:8080/ProyectoApliWeb2015/";

    FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException 
    {
        this.filterConfig=filterConfig;        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {        
        
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        String requestUrl=req.getRequestURL().toString(); 
        
            if(req.getUserPrincipal()==null)
            {
                if(requestUrl.equals(urlPrincipal) || requestUrl.equals(urlPrincipal+"faces/Login.xhtml") )
                {
                    chain.doFilter(request, response);
                }
                else
                {
                    res.sendRedirect(req.getContextPath());
                }
                
            }
            else
            {
                if(requestUrl.equals(urlPrincipal) || requestUrl.equals(urlPrincipal+"faces/Login.xhtml") )
                {
                    String tipo=personaGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getPersonagrupoPK().getGruid();
                    if(tipo.equals("user"))
                    {
                        res.sendRedirect(req.getContextPath()+"/faces/usuario/incidentes.xhtml");
                    }
                    else
                    {
                        if(tipo.equals("admin"))
                        {
                            res.sendRedirect(req.getContextPath()+"/faces/administrador/usuarios.xhtml");
                        }
                        else
                        {
                            
                        }
                    }
                }
                else
                {
                   chain.doFilter(request, response);
                }
                
            }
    }
    

    @Override
    public void destroy()
    {
        this.filterConfig=null;
    }

  
    
}
