/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import mx.ciencias.is.capisoft.modelo.Usuario;
import mx.ciencias.is.capisoft.modelo.dao.UsuarioDAO;

/**
 *
 * @author acv629
 */
@ManagedBean
@ViewScoped
public class EliminarUsuario {

  private Usuario usuario;
  private String correo;

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  @PostConstruct
  public void init() {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    usuario = (Usuario) externalContext.getSessionMap().get("user");
    if (usuario == null || !usuario.getRol().trim().equals("admin")) {
      try {
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
      } catch (IOException ex) {
        Logger.getLogger(CrearPregunta.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public String eliminar() {
    Usuario usuarioAEliminar = new UsuarioDAO().obtener(correo);
    new UsuarioDAO().elimina(usuarioAEliminar);
    return "index.xhtml?faces-redirect=true";
  }

}
