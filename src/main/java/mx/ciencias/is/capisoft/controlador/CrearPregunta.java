/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
//import javax.faces.context.FacesContext;
import mx.ciencias.is.capisoft.modelo.Pregunta;
import mx.ciencias.is.capisoft.modelo.Usuario;
import mx.ciencias.is.capisoft.modelo.dao.PreguntaDAO;
//import mx.ciencias.is.capisoft.modelo.Usuario;
//import mx.ciencias.is.capisoft.modelo.dao.UsuarioDAO;

/**
 *
 * @author victor
 */
//etiqueta que le dice a jsf que esta clase es un controllador
@ManagedBean
//Etiqueta para que viva este bean hasta que se cambie de pagina
@ViewScoped
public class CrearPregunta {

  private String titulo;
  private String pregunta;
  private Usuario usuario;

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getPregunta() {
    return pregunta;
  }

  public void setPregunta(String pregunta) {
    this.pregunta = pregunta;
  }

  public String creaPregunta() {
    Pregunta p = new Pregunta();
    p.setIdPregunta(0);
    p.setTitulo(titulo);
    p.setPregunta(pregunta);
    p.setUsuario(usuario);
    p.setComentarios(null);
    p.setFechaPublicacion(new Date());
    PreguntaDAO pd = new PreguntaDAO();
    pd.crear(p);
    int id = p.getIdPregunta();

    return "/pregunta.xhtml?id=" + id + "&faces-redirect=true";
  }

  @PostConstruct
  public void init() {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    usuario = (Usuario) externalContext.getSessionMap().get("user");
    if (usuario == null) {
      try {
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
      } catch (IOException ex) {
        Logger.getLogger(CrearPregunta.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
