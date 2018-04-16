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
//import org.primefaces.component.editor.Editor;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import mx.ciencias.is.capisoft.modelo.dao.PreguntaDAO;
import mx.ciencias.is.capisoft.modelo.dao.UsuarioDAO;
/**
 *
 * @author berna
 */
@ManagedBean
@ViewScoped
public class CrearComentario implements Serializable{
    
    String texto;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    Pregunta pregunta;
    //Usuario usPregunta;//Usuario que esta haciendo la pregunta/comentario 

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
    
    /*Usuario actual  que respondera a la pregunta/comentario*/
    Usuario us= (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    
   
     
    
    
    
    /**
     * Solo realiza comentarios a  preguntas
     * @param preguntaa
     * @return 
     */
    public String comentar(Pregunta preguntaa){
        //PreguntaDAO aDAO=new PreguntaDAO();
        
        this.setPregunta(preguntaa);
        Date fecha=new Date();    
      //usuario us= context.getExternalContext().getSessionMap().get("user");
        if(! getTexto().equals("")){
            Comentario com=new Comentario(0, null, pregunta, us, texto, fecha, null);
            ComentarioDAO comDAO=new ComentarioDAO();
            comDAO.crear(com);   
            setTexto("");
            
        }
        return "pregunta?id="+preguntaa.getIdPregunta()+"&faces-redirect=true";
    }
    
  
    

    
   
    
}
