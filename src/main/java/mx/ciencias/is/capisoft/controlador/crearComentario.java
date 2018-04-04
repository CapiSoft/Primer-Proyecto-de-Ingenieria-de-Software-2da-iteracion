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
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import mx.ciencias.is.capisoft.modelo.dao.PreguntaDAO;
import mx.ciencias.is.capisoft.modelo.dao.UsuarioDAO;
/**
 *
 * @author berna
 */
@ManagedBean
@SessionScoped
public class crearComentario{
    
    String texto;
    Pregunta pregunta;
    //Usuario usPregunta;//Usuario que esta haciendo la pregunta/comentario 

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
    
    /*Usuario actual  que respondera a la pregunta/comentario*/
    Usuario us=new UsuarioDAO().obtener("a@gmail.com");
    
    public void buttonAction(ActionEvent actionEvent) {
     //  comentar();
        
    }
  
     
    /*Revisar si esta respondiendo a otro comentario, si no, comentario=null*/
    
    
    /**
     * Solo realiza comentarios a  preguntas
     */
    public void comentar(Pregunta preguntaa){
        //PreguntaDAO aDAO=new PreguntaDAO();
        
        this.setPregunta(preguntaa);
        Date fecha=new Date();    
      
        
        Comentario com=new Comentario(0, null, pregunta, us, texto, fecha, null);
        ComentarioDAO comDAO=new ComentarioDAO();
        comDAO.crear(com);   
        
      
    }
    
    public void openDialog(){
        RequestContext rc=RequestContext.getCurrentInstance();
        rc.execute("comEmpty.show()");
    }
   
    
    public void foo(Pregunta preguntaa){
        this.setPregunta(preguntaa);
        Date fecha=new Date();
        ComentarioDAO comDAO=new ComentarioDAO();
        for(int i=2;i<5;i++){
            Comentario com=new Comentario(i, null, pregunta, us, "Comentario num."+i, fecha, null);            
            comDAO.crear(com);
            System.out.println("#"+i+" Agregado");
        }
    }
    
    public String getTexto(){
        return texto;
    }
    
    public void setTexto(String nwTe){
        texto=nwTe;
    }
    
    /*static public void main(String[]Args){
       
        for(int i=0; i<=10;i++){
             Date b =new Date();
            Usuario a=new Usuario(""+i+"asdsdasdasda@gmail.com", ""+i+"nombre_adasasda", ""+i+"", "dsadasdasdasd", b, "Usuario", null, null);
            UsuarioDAO aDAO=new UsuarioDAO();
            aDAO.crear(a);
            System.out.println("Listo "+1+" Usuario creado");
        }
    
    }*/
    
}
