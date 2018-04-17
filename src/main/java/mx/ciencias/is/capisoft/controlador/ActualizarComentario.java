/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

import mx.ciencias.is.capisoft.modelo.Comentario;
import mx.ciencias.is.capisoft.modelo.Usuario;
import mx.ciencias.is.capisoft.modelo.dao.ComentarioDAO;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author berna
 * @author acv629
 */
@ManagedBean
@ViewScoped
public class ActualizarComentario {

  private Comentario comentario;
  private String texto;
  private boolean comentar;
  private boolean actualizando;
  private Usuario usuario;

  public Comentario getComentario() {
    return comentario;
  }

  public void setComentario(Comentario comentario) {
    this.comentario = comentario;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public boolean isComentar() {
    return comentar;
  }

  public void setComentar(boolean comentar) {
    this.comentar = comentar;
  }

  public boolean isActualizando() {
    return actualizando;
  }

  public void setActualizando(boolean actualizando) {
    this.actualizando = actualizando;
  }

  public String actualizar() {

    if (!"".equals(this.texto)) {

      this.comentario.setComentario(texto);
      ComentarioDAO comDAO = new ComentarioDAO();
      comDAO.actualizar(this.comentario);
      return "pregunta?id=" + this.comentario.getPregunta().getIdPregunta() + "&faces-redirect=true";
    }
    return null;
  }

  public void fooActualizar() {
    //System.out.println(texto);
    cambiaVistaActualizar(false, null);
    texto = "";
  }

  public void cambiaVistaActualizar(boolean actualizar, Comentario comentario) {
    this.comentario = comentario;
    this.actualizando = actualizar;
    this.comentar = !actualizar;
    if (comentario != null) {
      this.texto = comentario.getComentario();
    }
  }

  /**
   * comparar si el comentario lo realizo el usuario que actualmente esta
   * conectado, con el fin de darle la opcion de editar el comentario
   *
   * @param comentario
   * @return
   */
  public boolean esUsuario(Comentario comentario) {
    return this.usuario != null && comentario != null && comentario.getUsuario().getCorreo().equals(this.usuario.getCorreo());
  }

  @PostConstruct
  public void init() {
    this.usuario = (Usuario) FacesContext.getCurrentInstance()
            .getExternalContext().getSessionMap().get("user");
    this.actualizando = false;
    this.texto = "";
  }

}
