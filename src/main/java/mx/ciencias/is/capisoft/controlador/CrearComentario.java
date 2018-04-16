/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

import java.io.Serializable;
import mx.ciencias.is.capisoft.modelo.Comentario;
import mx.ciencias.is.capisoft.modelo.Pregunta;
import mx.ciencias.is.capisoft.modelo.Usuario;
import mx.ciencias.is.capisoft.modelo.dao.ComentarioDAO;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author berna
 * @author acv629
 */
@ManagedBean
@ViewScoped
public class CrearComentario implements Serializable {

  private String texto;
  private Usuario usuario;

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  /**
   * Agrega un nuevo comentario a una pregunta
   *
   * @param pregunta La pregunta a la que se le agrega el comentario
   * @return Recarga la página si la creación fue exitosa
   */
  public String crearComentario(Pregunta pregunta) {

    Date fecha = new Date();
    if (!"".equals(texto)) {
      Comentario com = new Comentario(0, null, pregunta, usuario, texto, fecha, null);
      ComentarioDAO comDAO = new ComentarioDAO();
      comDAO.crear(com);
      setTexto("");
      return "pregunta?id=" + pregunta.getIdPregunta() + "&faces-redirect=true";
    }
    return null;
  }

  /**
   * Inicializa el Bean
   */
  @PostConstruct
  public void init() {
    this.usuario = (Usuario) FacesContext.getCurrentInstance()
            .getExternalContext().getSessionMap().get("user");
    this.texto = "";
  }

}
