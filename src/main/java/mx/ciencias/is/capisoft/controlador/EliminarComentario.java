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
 * 
 */
@ManagedBean
@ViewScoped
public class EliminarComentario{

  private Comentario comentario;  
  private boolean actualizando;
  private Usuario usuario;

  public Comentario getComentario() {
    return comentario;
  }

  public void setComentario(Comentario comentario) {
    this.comentario = comentario;
  }

  
 
  public boolean isActualizando() {
    return actualizando;
  }

  public void setActualizando(boolean actualizando) {
    this.actualizando = actualizando;
  }

 

  public String eliminar(Comentario comentario){
      this.comentario=comentario;
      ComentarioDAO comDAO=new ComentarioDAO();
      comDAO.elimina(this.comentario);
      return "pregunta?id=" + this.comentario.getPregunta().getIdPregunta() + "&faces-redirect=true";
  }
  
  

  /**
   * comparar si el comentario lo realizo el usuario que actualmente esta
   * conectado, con el fin de darle la opcion de editar el comentario
   *
   * @param comentario
   * @return
   */
  public boolean esUsuario(Comentario comentario) {
    return this.usuario != null && comentario != null && comentario.getUsuario().getCorreo().equals(this.usuario.getCorreo()) || usuario.getRol().equals("Admin");
  }

  @PostConstruct
  public void init() {
    this.usuario = (Usuario) FacesContext.getCurrentInstance()
            .getExternalContext().getSessionMap().get("user");
    this.actualizando = false;
    
  }

}
