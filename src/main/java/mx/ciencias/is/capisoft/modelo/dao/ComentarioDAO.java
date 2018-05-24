/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.modelo.dao;

import java.util.List;
import mx.ciencias.is.capisoft.modelo.Comentario;
import mx.ciencias.is.capisoft.modelo.HibernateUtil;
import mx.ciencias.is.capisoft.modelo.Pregunta;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Data Access Object para la entidad Comentario
 *
 * @author acv629
 * @version 1.0
 */
public class ComentarioDAO {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  /**
   * Añade un nuevo comentario
   *
   * @param comentarioNuevo El comentario a añadir a la BD
   */
  public void crear(Comentario comentarioNuevo) {
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      session.persist(comentarioNuevo);

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
   * Dado un comentario lo borra de la  BD
   * @param c 
   */
  public void elimina(Comentario c){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
         
           session.delete(c);
           
           tx.commit();
        }
        catch (HibernateException e) {
           if (tx!=null){ 
               tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
           session.close();
        }
    
    }
  
  
  /**
   * Obtiene un comentario
   *
   * @param id El identificador del comentario que se desea obtener
   * @return El comentario identificado por el id, o null si no existe
   */
  public Comentario obtener(int id) {
    Comentario comentarioObtenido = null;
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      String queryString = "from Comentario c where c.idComentario=:id";
      Query query = session.createQuery(queryString);
      query.setParameter("id", id);
      comentarioObtenido = (Comentario) query.uniqueResult();

      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
    return comentarioObtenido;
  }

  /**
   * Obtiene los comentarios de una pregunta
   *
   * @param pregunta Una pregunta obtenida anteriormente con
   * {@link mx.ciencias.is.capisoft.modelo.dao.PreguntaDAO#obtener() obtener} u {@link mx.ciencias.is.capisoft.modelo.dao.PreguntaDAO#obtener(int)
   * obtener lista}
   * @return Una lista con todas los comentarios de la pregunta
   */
  public List<Comentario> obtener(Pregunta pregunta) {
    List<Comentario> comentariosObtenidos = null;
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      String queryString = "from Comentario c where c.pregunta.idPregunta=:idP"
              + " order by c.fecha desc";
      Query query = session.createQuery(queryString);
      query.setParameter("idP", pregunta.getIdPregunta());
      comentariosObtenidos = (List<Comentario>) query.list();

      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
    return comentariosObtenidos;
  }

  public List<Comentario> obtener(Pregunta pregunta, boolean fetchUsuario) {
    List<Comentario> comentariosObtenidos = null;
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      String queryString = "from Comentario c inner join fetch c.pregunta p";
      if (fetchUsuario) {
        queryString += " left join fetch c.usuario u";
      }
      queryString += " where p.idPregunta=:idP order by c.fecha desc";
      Query query = session.createQuery(queryString);
      query.setParameter("idP", pregunta.getIdPregunta());
      comentariosObtenidos = (List<Comentario>) query.list();

      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
    return comentariosObtenidos;
  }

  /**
   * Obtiene una lista de comentarios que responden
   *
   * @param comentario Un comentario obtenido anteriormente
   * @return Los comentarios que responden al comentario dado
   */
  public List<Comentario> obtener(Comentario comentario) {
    List<Comentario> comentariosObtenidos = null;
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      String queryString = "from Comentario c where c.responde.idComentario=:idC";
      Query query = session.createQuery(queryString);
      query.setParameter("idC", comentario.getIdComentario());
      comentariosObtenidos = (List<Comentario>) query.list();

    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
    return comentariosObtenidos;
  }

  /**
   * Actualiza un comentario
   *
   * @param comentarioActualizado El comentario a actualizar previamente
   * obtenido
   */
  public void actualizar(Comentario comentarioActualizado) {
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      session.merge(comentarioActualizado);

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
