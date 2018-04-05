/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.ciencias.is.capisoft.modelo.Pregunta;
import mx.ciencias.is.capisoft.modelo.dao.PreguntaDAO;

/**
 *
 * @author acv629
 */
@ManagedBean
@ViewScoped
public class BuscarContenido {

  private String contenidoABuscar;

  public String getContenidoABuscar() {
    return contenidoABuscar;
  }

  public void setContenidoABuscar(String contenidoABuscar) {
    this.contenidoABuscar = contenidoABuscar;
  }

  public List<Pregunta> buscar() {
    return new PreguntaDAO().buscar(contenidoABuscar);
  }

}
