/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ciencias.is.capisoft.modelo.dao;

import mx.ciencias.is.capisoft.modelo.HibernateUtil;
import mx.ciencias.is.capisoft.modelo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Data Access Object para la entidad Usuario
 *
 * @author acv629
 * @version 1.0
 */
public class UsuarioDAO {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  /**
   * Añade un nuevo usuario
   *
   * @param usuarioNuevo El usuario a añadir a la BD
   */
  public void crear(Usuario usuarioNuevo) {
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      session.persist(usuarioNuevo);

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
   * Obtiene un usuario
   *
   * @param correoUsuario El correo que identifica al usuario que se desea
   * obtener de la BD
   * @return El usuario identificado por el correo, o null si no existe
   */
  public Usuario obtener(String correoUsuario) {
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    Usuario usuarioObtenido = null;
    try {
      tx = session.beginTransaction();

      String queryString = "from Usuario u where u.correo=:c";
      Query query = session.createQuery(queryString);
      query.setParameter("c", correoUsuario);

      usuarioObtenido = (Usuario) query.uniqueResult();

      tx.commit();

    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
    return usuarioObtenido;
  }

  /**
   * Actualiza un usuario
   *
   * @param usuarioActualizado Un usuario previamente obtenido con
   * {@link #obtener(java.lang.String) obtener}
   */
  public void actualizar(Usuario usuarioActualizado) {
    Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      session.merge(usuarioActualizado);

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

    public Usuario encuentra(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
