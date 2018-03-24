/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.modelo.dao;

import java.util.List;
import mx.ciencias.is.capisoft.modelo.HibernateUtil;
import mx.ciencias.is.capisoft.modelo.Pregunta;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Data Access Object para la entidad Pregunta
 *
 * @author acv629
 * @version 1.0
 */
public class PreguntaDAO {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  /**
   * Añade una nueva pregunta
   *
   * @param preguntaNueva La pregunta a añadir a la BD
   */
  public void crear(Pregunta preguntaNueva) {
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      tx.begin();

      session.persist(preguntaNueva);

      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  /**
   * Obtiene todas las preguntas
   *
   * @return Una lista con todas las preguntas en la BD
   */
  public List<Pregunta> obtener() {
    List<Pregunta> preguntasObtenidas = null;
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      tx.begin();

      String queryString = "from Pregunta";
      Query query = session.createQuery(queryString);
      preguntasObtenidas = (List<Pregunta>) query.list();

      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }

    return preguntasObtenidas;
  }

}
