/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
public class CreaPregunta {

  private String titulo;
  private String pregunta;

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
    p.getUsuario();
    p.getComentarios();
    p.getFechaPublicacion();
    PreguntaDAO pd = new PreguntaDAO();
    pd.crear(p);
    //int id = pd.obtener();
    //Usuario nu = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombreUsuario");

    //return "pregunta.xhtml?=id"+id;
    return "index";
  }
}
