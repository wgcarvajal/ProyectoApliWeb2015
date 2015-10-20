/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.managedbean.incidentes;

import com.unicauca.apliweb.entities.Incidente;
import com.unicauca.apliweb.entities.Persona;
import java.io.Serializable;
import java.util.Properties;
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
    private String to;
    private String from;
    private String message;
    private String subject;
    private String smtpServ;
    
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
    public void enviarMensaje(Incidente incidente)
    {
        this.persona=incidente.getPersona();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formEnviarCorreo");
        requestContext.execute("PF('enviarCorreo').show()");
    }
    
    
    
    
    
    
    public void sendMail(){
        
        System.out.println("entro-----------------");
        HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();      
        
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
            System.out.println("A+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }       
    }
    
    
}
