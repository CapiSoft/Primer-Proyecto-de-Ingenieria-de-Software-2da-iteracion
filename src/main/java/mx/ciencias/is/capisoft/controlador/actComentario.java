/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

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
/**
 *
 * @author berna
 */


@ManagedBean
@SessionScoped
public class actComentario {
    
    String texto;
    Comentario com;
    boolean comentar;
    boolean editar;

    
    public actComentario(){
        comentar=true;
        editar=false;
    }

    public Comentario getCom() {
        return com;
    }

    public void setCom(Comentario com) {
        this.com = com;
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

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
          //context.getExternalContext().getSessionMap().get("user");

    
    public String actualizar(){
        
        if(!getTexto().equals("")){
            
            Date fecha= new Date();
            Comentario comen=new Comentario(com.getIdComentario(),null,com.getPregunta() ,com.getUsuario(), texto, com.getFecha(), null);
            ComentarioDAO comDAO=new ComentarioDAO();
            comDAO.actualizar(comen); 
            cambiaVistaActualizar(false, null);
            setTexto("");
        }
        
        return "pregunta?id="+com.getPregunta().getIdPregunta()+"&faces-redirect=true";
    }
    
    public void fooActualizar(){
        //System.out.println(texto);
        cambiaVistaActualizar(false, null);
        texto="";
    }
    
    public String cambiaVistaActualizar(boolean mood,Comentario comentarioo){
        setCom(comentarioo);
        setComentar(!mood);
        setEditar(mood);
        setTexto(comentarioo.getComentario());
        return "pregunta?id="+comentarioo.getPregunta().getIdPregunta()+"&faces-redirect=true";

    }
    /**
     *  comparar si el comentario lo realizo el usuario que actualmente esta conectado, con el fin de darle la opcion de editar el comentario
     * @param comentario
     * @return 
     */
    public boolean esUsuario(Comentario comentario){
        
        return true;
    }
    
}
