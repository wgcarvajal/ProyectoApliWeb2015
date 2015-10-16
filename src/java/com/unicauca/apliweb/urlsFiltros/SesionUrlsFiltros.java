/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.urlsFiltros;

import java.io.IOException;
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

    FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException 
    {
        this.filterConfig=filterConfig;        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        /*HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        HttpSession session=req.getSession(true);        
        String requestUrl=req.getRequestURL().toString(); 
        
        String []urlPermitidasSuperAdministrador= new String []
        {"VistaAdministrador","gestionUsuarios","gestionAdministradores","gestionImplementos",
         "gestionEscenarios","registrarUsuario","registrarAdministrador","usuariosPorConfirmar",
         "registrarImplemento","registrarEscenario","gestionReservas","reservasImplementos",
         "realizarReservaImplemento", "reservaEscenario/Listar", "reservaEscenario/Create","mostrarReservasImplementos","Estadisticas"};
        
        String []urlPermitidasAdministradorEscenarios= new String []
        {"VistaAdministrador","gestionUsuarios","gestionEscenarios","registrarUsuario","usuariosPorConfirmar"
        ,"gestionReservas", "reservaEscenario/Listar", "reservaEscenario/Create","Estadisticas"};
        
        String []urlPermitidasAdministradorImplementos= new String []
        {"VistaAdministrador","gestionUsuarios","gestionImplementos","registrarUsuario","usuariosPorConfirmar",
         "registrarImplemento","gestionReservas","reservasImplementos","realizarReservaImplemento","mostrarReservasImplementos","Estadisticas"};
        
        String []urlPermitidasUsuarioSinSesion= new String[]
        {"index", "reservaEscenario/VerTodas"};
        
        String []urlPermitidasUsuarioComun= new String[]
        {"index", "indexUser", "reservaEscenario/Solicitar"};
        
        if(requestUrl.equals("http://localhost:8084/GestorRecursosDeportivos/"))
        {
            res.sendRedirect(req.getContextPath()+"/faces/index.xhtml");
        }
        else
        {
            if(session.getAttribute("rol")!=null && (Integer)session.getAttribute("rol")==1)
            {
                boolean bandera=false;
                for(String url:urlPermitidasSuperAdministrador)
                {
                    if(requestUrl.contains(url))
                    {
                        bandera=true;
                        break;
                    }
                }
                if(bandera==true)
                {
                    
                    chain.doFilter(request, response);                    
                }
                else
                {
                    res.sendRedirect(req.getContextPath()+"/faces/VistaAdministrador.xhtml");
                }
                            
            }
            else
            {   
                if(session.getAttribute("rol")!=null && (Integer)session.getAttribute("rol")==2)
                {
                    boolean bandera=false;
                    for(String url:urlPermitidasAdministradorEscenarios)
                    {
                        if(requestUrl.contains(url))
                        {
                            bandera=true;
                            break;
                        }
                    }
                    if(bandera==true)
                    {

                        chain.doFilter(request, response);                    
                    }
                    else
                    {
                        res.sendRedirect(req.getContextPath()+"/faces/VistaAdministrador.xhtml");
                    }

                }
                else
                {
                    if(session.getAttribute("rol")!=null && (Integer)session.getAttribute("rol")==3)
                    {
                        boolean bandera=false;
                        for(String url:urlPermitidasAdministradorImplementos)
                        {
                            if(requestUrl.contains(url))
                            {
                                bandera=true;
                                break;
                            }
                        }
                        if(bandera==true)
                        {

                            chain.doFilter(request, response);                    
                        }
                        else
                        {
                            res.sendRedirect(req.getContextPath()+"/faces/VistaAdministrador.xhtml");
                        }

                    }
                    else
                    {
                        if(session.getAttribute("rol")!=null && (Integer)session.getAttribute("rol")==4)
                        {
                            boolean bandera=false;
                            for(String url:urlPermitidasUsuarioComun)
                            {
                                if(requestUrl.contains(url))
                                {
                                    bandera=true;
                                    break;
                                }
                            }
                            if(bandera==true)
                            {

                                chain.doFilter(request, response);                    
                            }
                            else
                            {
                                res.sendRedirect(req.getContextPath()+"/faces/index.xhtml");
                            }
                        }
                        else
                        {
                            boolean bandera=false;
                            for(String url:urlPermitidasUsuarioSinSesion)
                            {
                                if(requestUrl.contains(url))
                                {
                                    bandera=true;
                                    break;
                                }
                            }
                            if(bandera==true)
                            {

                                chain.doFilter(request, response);                    
                            }
                            else
                            {
                                res.sendRedirect(req.getContextPath()+"/faces/index.xhtml");
                            }

                        }
                         
                    }
                }  
                   
                    
            }
            
            
        } */
        
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        String requestUrl=req.getRequestURL().toString(); 
        
            if(req.getUserPrincipal()==null)
            {
                if(requestUrl.equals("http://localhost:8080/ProyectoApliWeb/") || requestUrl.equals("http://localhost:8080/ProyectoApliWeb/faces/Login.xhtml") )
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
                if(requestUrl.equals("http://localhost:8080/ProyectoApliWeb/") || requestUrl.equals("http://localhost:8080/ProyectoApliWeb/faces/Login.xhtml") )
                {
                    res.sendRedirect(req.getContextPath()+"/faces/error.html");
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
