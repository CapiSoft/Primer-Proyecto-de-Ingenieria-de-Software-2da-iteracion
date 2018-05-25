/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.modelo.dao;

import java.util.List;
import mx.ciencias.is.capisoft.modelo.HibernateUtil;
import mx.ciencias.is.capisoft.modelo.Pregunta;
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
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

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
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      String queryString = "from Pregunta p order by p.fechaPublicacion desc";
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
   * Obtiene todas las preguntas
   *
   * @param fetchUsuario Bandera que indica si se debe cargar el usuario de cada
   * pregunta
   * @param fetchComentarios Bandera que indica si se deben cargar los
   * comentarios de cada pregunta
   *
   * @return Una lista con todas las preguntas en la BD
   */
  public List<Pregunta> obtener(boolean fetchUsuario, boolean fetchComentarios) {
    List<Pregunta> preguntasObtenidas = null;
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      String queryString = "from Pregunta p";
      if (fetchUsuario) {
        queryString += " join fetch p.usuario u";
      }
      if (fetchComentarios) {
        queryString += " join fetch p.comentarios c";
      }
      queryString += " order by p.fechaPublicacion desc";

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
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      String queryString = "from Pregunta p where p.idPregunta=:id";
      Query query = session.createQuery(queryString);
      query.setParameter("id", idPregunta);
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
   * Obtiene una pregunta específica
   *
   * @param idPregunta El id de la pregunta que se quiere obtener
   * @param fetchUsuario Bandera que indica si se debe cargar el usuario de la
   * pregunta
   * @param fetchComentarios Bandera que indica si se deben cargar los
   * comentarios de la pregunta
   *
   * @return La pregunta identificada por el id, o null si no existe
   */
  public Pregunta obtener(int idPregunta, boolean fetchUsuario, boolean fetchComentarios) {
    Pregunta preguntaObtenida = null;
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      String queryString = "from Pregunta p";
      if (fetchUsuario) {
        queryString += " inner join fetch p.usuario u";
      }
      if (fetchComentarios) {
        queryString += " left join fetch p.comentarios c";
      }
      queryString += " where p.idPregunta=:id";

      Query query = session.createQuery(queryString);

      query.setParameter("id", idPregunta);
      preguntaObtenida = (Pregunta) query.list().get(0);

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
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

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

  /**
   * Busca entre las preguntas
   *
   * @param contenido Una cadena a buscar dentro de las preguntas
   * @return Una lista con todas las preguntas que contienen la cadena buscada
   */
  public List<Pregunta> buscar(String contenido) {
    List<Pregunta> preguntasObtenidas = null;
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      String queryString = "from Pregunta p join fetch p.usuario u"
              + " where (p.titulo like ?)"
              + " or (p.pregunta like ?) order by p.fechaPublicacion desc";
      Query query = session.createQuery(queryString);
      query.setString(0, "%" + contenido + "%");
      query.setString(1, "%" + contenido + "%");
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
   * Elimina una pregunta de la BD
   *
   * @param idPregunta El id de la pregunta que se desea borrar
   */
  public void eliminar(int idPregunta) {
    Pregunta p = this.obtener(idPregunta, true, true);
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      session.delete(p);

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
