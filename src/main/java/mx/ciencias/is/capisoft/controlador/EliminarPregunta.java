/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mx.ciencias.is.capisoft.modelo.Pregunta;
import mx.ciencias.is.capisoft.modelo.Usuario;
import mx.ciencias.is.capisoft.modelo.dao.PreguntaDAO;

/**
 *
 * @author acv629
 */
@ManagedBean
@RequestScoped
public class EliminarPregunta {

  public boolean puedeEliminar(Pregunta p) {
    Usuario usuario = (Usuario) FacesContext.getCurrentInstance()
            .getExternalContext().getSessionMap().get("user");
    return usuario != null && (usuario.getRol().equals("admin") || p.getUsuario().getCorreo().equals(usuario.getCorreo()));
  }

  public void eliminar(int idPregunta) {
    new PreguntaDAO().eliminar(idPregunta);
  }
}
