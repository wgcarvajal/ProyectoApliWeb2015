/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.managedbean.incidentes;

import com.unicauca.apliweb.beans.EnviacorreoFacade;
import com.unicauca.apliweb.beans.PersonaFacade;
import com.unicauca.apliweb.entities.Enviacorreo;
import com.unicauca.apliweb.entities.EnviacorreoPK;
import com.unicauca.apliweb.entities.Incidente;
import com.unicauca.apliweb.entities.Persona;
import java.io.Serializable;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class EnviarCorreoController implements Serializable
{

    private String correo;
    private Persona persona;
    private Incidente incidente;
    private String to;
    private String from;
    private String message;
    private String mensaje;
    private String subject;
    private String smtpServ;
    @EJB
    private PersonaFacade personaEJB;
    @EJB
    private EnviacorreoFacade enviaCorreoEJB;
    
    public EnviarCorreoController()
    {
        this.persona=new Persona();
    }   
    

    public Persona getPersona() 
    {
        return persona;
    }

    public void setPersona(Persona persona) 
    {
        this.persona = persona;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    
    
    
    
    
    public void enviarMensaje(Incidente incidente)
    {
        this.persona=incidente.getPersona();
        this.incidente=incidente;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formEnviarCorreo");
        requestContext.execute("PF('enviarCorreo').show()");
    }
    
    
    
    
    
    
    public void sendMail(){
        
        System.out.println("entro-----------------");
        HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
        
        Persona perEnvia=personaEJB.buscarNombreUsuario(request.getUserPrincipal().getName());
        
        to = persona.getPeremail();
        from = "electivaapliweb@gmail.com";    
        smtpServ = "smtp.gmail.com";
        String pass = "electiva.apliweb";  
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpServ);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);

        Session session = Session.getDefaultInstance(props);//autentificar el correo

        
        this.message="<br/>Tiket incidente:"+this.incidente.getIncid()+
                "<br/>Miembro Equipo de  Soporte:"+perEnvia.getPernombre()+" "+perEnvia.getPerapellido()+"<br/><br/>"
                +this.mensaje+"<br/><br/><br/>Por favor no responder este mensaje.........";
        
        try {
            MimeMessage message = new MimeMessage(session);//se inicia una session
            message.setFrom(new InternetAddress(this.from));
            message.addRecipient(Message.RecipientType.TO,
                           new InternetAddress(to));
            message.setSubject(this.subject);
            message.setText(this.message, "ISO-8859-1", "html");

            Transport transport = session.getTransport("smtp");
            transport.connect(smtpServ, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            Enviacorreo enviaCorreo=new Enviacorreo();
            EnviacorreoPK enviaCorreoPK=new EnviacorreoPK();
            enviaCorreoPK.setIncid(this.incidente.getIncid());
            enviaCorreoPK.setPerfkdestinatario(this.persona.getPerid());
            enviaCorreoPK.setPerfkremitente(perEnvia.getPerid());
            enviaCorreo.setAsunto(this.subject);
            enviaCorreo.setDestinatario(this.persona);
            enviaCorreo.setRemitente(perEnvia);
            enviaCorreo.setMensaje(this.mensaje);
            enviaCorreo.setIncidente(this.incidente);
            enviaCorreo.setAdjunto("ninguno");
            enviaCorreo.setEnviacorreoPK(enviaCorreoPK);
            this.enviaCorreoEJB.create(enviaCorreo);
            System.out.println("A+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }       
    }
    
    
}
