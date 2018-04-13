/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.ciencias.is.capisoft.modelo.Usuario;
import mx.ciencias.is.capisoft.modelo.dao.UsuarioDAO;

/**
 *
 * @author roberto
 */
@ManagedBean
@ViewScoped
public class IniciarSesion {
    
    private String correo;
    private String password;
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        UsuarioDAO us = new UsuarioDAO();
        Usuario user = us.obtener(correo);
        FacesContext context = FacesContext.getCurrentInstance();
        
        if (user == null) {
            context.addMessage(null, new FacesMessage("Usuario incorrecto, intenta de nuevo"));
            correo = null;
            password = null;
            return "";
        } else {
            context.getExternalContext().getSessionMap().put("user", user);
            correo = null;
            password = null;
            return "index.xhtml";
            
        }
    }
    
    public String salir() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
    
}
