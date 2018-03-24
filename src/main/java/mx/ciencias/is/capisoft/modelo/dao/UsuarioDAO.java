/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.modelo.dao;

import mx.ciencias.is.capisoft.modelo.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 *
 * @author acv629
 */
public class UsuarioDAO {
    private final SessionFactory session = HibernateUtil.getSessionFactory();
}
