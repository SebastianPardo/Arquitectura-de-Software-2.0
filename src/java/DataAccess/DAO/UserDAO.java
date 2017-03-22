/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.PersistenceException;

/**
 *
 * @author arqsoft2017i
 */
public class UserDAO {

    public UserDAO() {

    }

    public Usuario persist(Usuario aUser) throws PersistenceException {
        EFactory.getCache().evict(Usuario.class);
        EntityManager em = EFactory.createEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        try {
            em.persist(aUser);
            et.commit();
        } catch (PersistenceException e) {            
            //e.printStackTrace();
            et.rollback();
            throw e;
        } catch (Exception e) {            
            e.printStackTrace();
            et.rollback();
        } finally {
            em.clear();
            em.close();
        }
        return aUser;
    } 
    
    public boolean remove(Usuario aUser){
        boolean value = true;
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {
            em.remove(aUser);
            et.commit();
        } catch (Exception e) {
            value = false;
            e.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }
        return value;
    } 

    public Usuario searchById(Integer usrId) {
        EFactory.getCache().evict(Usuario.class);
        EntityManager em = EFactory.createEntityManager();
        Usuario value = null;

        try {
            value = em.find(Usuario.class, usrId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return value;
    }

    public Usuario searchByName(String name) {
        EFactory.getCache().evict(Usuario.class);
        EntityManager em = EFactory.createEntityManager();
        Usuario value = null;
        Query q = em.createNamedQuery("Usuario.findByNombreUsuario");
        q.setParameter(1, name);

        try {
            value = (Usuario) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return value;
    }

    public Usuario searchByLastName(String lastName) {
        EFactory.getCache().evict(Usuario.class);
        EntityManager em = EFactory.createEntityManager();
        Usuario value = null;
        Query q = em.createNamedQuery("Usuario.findByNombreUsuario");
        q.setParameter(1, lastName);

        try {
            value = (Usuario) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return value;
    }

    public List<Usuario> findAll() {
        EFactory.getCache().evict(Usuario.class);
        EntityManager em = EFactory.createEntityManager();
        java.util.List<Usuario> usuarios = null;
        Query q = em.createNamedQuery("Usuario.findAll");
        try {
            usuarios = (java.util.List<Usuario>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return usuarios;
    }
    
     public List<Usuario> findAllExcept(Integer usrId) {
        EFactory.getCache().evict(Usuario.class);
        EntityManager em = EFactory.createEntityManager();
        java.util.List<Usuario> usuarios = null;
        Query q = em.createNamedQuery("Usuario.findAllExcept").setParameter("idUsuario", usrId);
        try {
            usuarios = (java.util.List<Usuario>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return usuarios;
    }


    public void edit(Usuario aUsr) {
        EFactory.getCache().evict(Usuario.class);
        Usuario usuarioNew;
        EntityManager em = EFactory.createEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            usuarioNew = em.merge(em.find(Usuario.class, aUsr.getIdUsuario()));
            usuarioNew.setAliasUsuario(aUsr.getAliasUsuario());
            usuarioNew.setActivo(aUsr.getActivo());
            usuarioNew.setApellidoUsuario(aUsr.getApellidoUsuario());
            usuarioNew.setFechaNacimientoUsuario(aUsr.getFechaNacimientoUsuario());
            usuarioNew.setFechaRegistroUsuario(aUsr.getFechaRegistroUsuario());
            usuarioNew.setNombreUsuario(aUsr.getNombreUsuario());
            usuarioNew.setSexoUsuario(aUsr.getSexoUsuario());
            usuarioNew.setTelefonoUsuario(aUsr.getTelefonoUsuario());
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } finally {
            em.close();
        }
    }

    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory(DataAccess.Entity.DataBaseController.DB_NAME);
}
