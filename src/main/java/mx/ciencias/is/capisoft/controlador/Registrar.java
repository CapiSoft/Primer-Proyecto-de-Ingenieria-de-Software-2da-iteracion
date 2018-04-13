/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.ciencias.is.capisoft.modelo.Usuario;
import mx.ciencias.is.capisoft.modelo.dao.UsuarioDAO;

/**
 *
 * @author roberto
 */
@ManagedBean
@ViewScoped
public class Registrar {
     private String password1;  
     private String correo;
     private String nombre;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
      public String getPassword1() {
        return password1;
    }
 
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    
    public String registrar(){
        Date date = new Date ();
        Usuario uNuevo = new Usuario(correo, nombre, password1, null, date, "usuario", null, null);
        new UsuarioDAO().crear(uNuevo);
        return "/index.xhtml?faces-redirect=true";
    }
}

