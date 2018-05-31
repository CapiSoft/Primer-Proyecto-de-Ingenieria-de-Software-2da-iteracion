/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

import java.util.Date;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import mx.ciencias.is.capisoft.modelo.Usuario;
import mx.ciencias.is.capisoft.modelo.dao.UsuarioDAO;

/**
 *
 * @author roberto
 */
@ManagedBean
@ViewScoped
public class Registrar {

  private String password1;
  private String correo;
  private String nombre;

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPassword1() {
    return password1;
  }

  public void setPassword1(String password1) {
    this.password1 = password1;
  }

  public String registrar() {
    Date date = new Date();
    Usuario uNuevo = new Usuario(correo, nombre, password1, null, date, "usuario", null, null);
    new UsuarioDAO().crear(uNuevo);
    new Mail("capisoft.is@gmail.com", "capisoft1234").sendMessage(correo + "@ciencias.unam.mx");
    return "/index.xhtml?faces-redirect=true";
  }

  /**
   * Clase interna para manejar el envío de correos
   *
   * @author Jael
   * @author acv629
   */
  public class Mail {

    String user;
    String pass;
    Properties properties;
    javax.mail.Session sesion;

    public Mail(String user, String pass) {
      properties = System.getProperties();
      properties.setProperty("mail.smtp.host", "smtp.googlemail.com");
      properties.setProperty("mail.defaultEncoding", "UTF-8");
      properties.setProperty("mail.smtp.auth", "true");
      properties.setProperty("mail.smtp.starttls.required", "true");
      properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      properties.setProperty("mail.smtp.socketFactory.fallback", "false");
      properties.setProperty("mail.smtp.port", "465");
      properties.setProperty("mail.smtp.socketFactory.port", "465");
      sesion = javax.mail.Session.getDefaultInstance(properties);
      this.user = user;
      this.pass = pass;
    }

    public void sendMessage(String to) {
      try {
        // Creamos un objeto mensaje tipo MimeMessage por defecto.
        MimeMessage mensaje = new MimeMessage(sesion);

        // Asignamos el “de o from” al header del correo.
        mensaje.setFrom(new InternetAddress(user));

        // Asignamos el “para o to” al header del correo.
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Asignamos el asunto
        mensaje.setSubject("Confirmación de Registro a Mis Trámites Ciencias");

        mensaje.setContent("Confirmacion de Registro: <br>"
                + "Te damos la bienvenida a Mis Trámites Ciencias. <br>"
                + "Esperamos que te la pases bien. <br>", "text/html");
        // Enviamos el correo
        Transport t = sesion.getTransport("smtp");
        t.connect(user, pass);
        t.sendMessage(mensaje, mensaje.getAllRecipients());
        System.out.println("Mensaje enviado");
      } catch (MessagingException e) {
        e.printStackTrace();
      }
    }
  }

}
