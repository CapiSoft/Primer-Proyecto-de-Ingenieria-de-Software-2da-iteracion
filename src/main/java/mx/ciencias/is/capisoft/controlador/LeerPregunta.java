/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import mx.ciencias.is.capisoft.modelo.Comentario;
import mx.ciencias.is.capisoft.modelo.Pregunta;
import mx.ciencias.is.capisoft.modelo.Usuario;
import mx.ciencias.is.capisoft.modelo.dao.ComentarioDAO;
import mx.ciencias.is.capisoft.modelo.dao.PreguntaDAO;

/**
 *
 * @author acv629
 */
@ManagedBean
@ViewScoped
public class LeerPregunta implements Serializable {

  private Pregunta preguntaPedida = new Pregunta();
  private List<Comentario> comentariosPregunta;
  private String busqueda;
  private Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

  public boolean isAdmin() {
    return usuario != null && usuario.getRol().trim().equals("admin");
  }

  public String getBusqueda() {
    return busqueda;
  }

  public void setBusqueda(String busqueda) {
    this.busqueda = busqueda;
  }

  public List<Comentario> getComentariosPregunta() {
    return comentariosPregunta;
  }

  public void setComentariosPregunta(List<Comentario> comentariosPregunta) {
    this.comentariosPregunta = comentariosPregunta;
  }

  public Pregunta getPreguntaPedida() {
    return preguntaPedida;
  }

  public void setPreguntaPedida(Pregunta preguntaPedida) {
    this.preguntaPedida = preguntaPedida;
  }

  public List<Pregunta> listarPreguntas() {
    PreguntaDAO daoP = new PreguntaDAO();
    List<Pregunta> preguntas = daoP.obtener(true, false);
    return preguntas;
  }

  public String mostrarPregunta() {
    int id = preguntaPedida.getIdPregunta();
    if (id == 0) {
      return "/index.xhtml?faces-redirect=true";
    }
    preguntaPedida = new PreguntaDAO().obtener(id, true, false);
    comentariosPregunta = new ComentarioDAO().obtener(preguntaPedida, true);
    return null;
  }

  public String enviaBusqueda() {
    if (busqueda != null) {
      if (!"".equals(busqueda.trim())) {
        return "/busqueda.xhtml?query=" + busqueda + "&faces-redirect=true";
      }
    }
    return null;
  }

  public boolean puedeActualizar(Pregunta p) {
    return usuario != null && (usuario.getRol().trim().equals("admin") || usuario.getCorreo().equals(p.getUsuario().getCorreo()));
  }

}
