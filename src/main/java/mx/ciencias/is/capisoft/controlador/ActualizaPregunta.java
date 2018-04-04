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
import mx.ciencias.is.capisoft.modelo.dao.PreguntaDAO;
//import mx.ciencias.is.capisoft.modelo.Usuario;
//import mx.ciencias.is.capisoft.modelo.dao.UsuarioDAO;
/**
 *
 * @author victor
 */
@ManagedBean
@ViewScoped
public class ActualizaPregunta {
    private int id;
    private Pregunta pregunta;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
    
    
   public void cargarId(){
    pregunta = new PreguntaDAO().obtener(id);
   }
   
   
    public String actualizaPregunta(){
        PreguntaDAO pd = new PreguntaDAO();
        pd.actualizar(pregunta);
        //Usuario nu = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombreUsuario");
        
        return "pregunta.xhtml?id="+id;
    
    }
}