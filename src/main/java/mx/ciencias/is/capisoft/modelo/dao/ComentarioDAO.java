/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.modelo.dao;

import mx.ciencias.is.capisoft.modelo.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 * Data Access Object para la entidad Comentario
 *
 * @author acv629
 * @version 1.0
 */
public class ComentarioDAO {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

}
