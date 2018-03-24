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

  /**
   * Obtiene una pregunta específica
   *
   * @param idPregunta El id de la pregunta que se quiere obtener
   * @return La pregunta identificada por el id, o null si no existe
   */
  public Pregunta obtener(int idPregunta) {
    Pregunta preguntaObtenida = null;
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      tx.begin();

      String queryString = "from Pregunta p where p.idPregunta=:id";
      Query query = session.createQuery(queryString);
      preguntaObtenida = (Pregunta) query.uniqueResult();

      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
    return preguntaObtenida;
  }

  /**
   * Actualiza una pregunta
   *
   * @param preguntaActualizada Una pregunta previamente obtenida con
   * {@link #obtener(int) obtener} u {@link #obtener() obtener lista}
   */
  public void actualizar(Pregunta preguntaActualizada) {
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      tx.begin();

      session.merge(preguntaActualizada);

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

}
