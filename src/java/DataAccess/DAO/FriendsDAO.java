/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author arqsoft2017i
 */
public class FriendsDAO {

    public FriendsDAO() {

    }

    public boolean persist(Amigos someFriends) {
        boolean value = true;
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Amigos someFriends_ = new Amigos(new AmigosPK(someFriends.getAmigosPK().getIdAmigo(), someFriends.getAmigosPK().getIdUsuario()), someFriends.getEstatusRelacion());
            em.persist(someFriends);
            em.persist(someFriends_);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
            value = false;
        } finally {
            em.close();
        }

        return value;
    }

    public boolean persist(Amigos newFriendsSideA, Amigos newFriendsSideB) {
        boolean value = true;
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            //Ya está manejado por la entidad y ya existe
            em.merge(newFriendsSideA);
            em.persist(newFriendsSideB);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
            value = false;
        } finally {
            em.close();
        }

        return value;
    }

    public boolean remove(Amigos someFriends) {
        boolean value = true;
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        try {
            someFriends = em.getReference(Amigos.class, someFriends.getAmigosPK());
            em.remove(someFriends);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
            value = false;
        } finally {
            em.close();
        }

        return value;
    }

    public boolean editFriendship(Amigos someFriends) {
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        boolean value = true;

        try {
            Amigos temp = em.merge(em.find(Amigos.class, someFriends.getAmigosPK()));
            temp.setAmigo(someFriends.getAmigo());
            temp.setUsuario(someFriends.getUsuario());
            temp.setEstatusRelacion(someFriends.getEstatusRelacion());
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
            value = false;
        } finally {
            em.close();
        }

        return value;
    }
    
    /**
     * Método usado para obtener los usuarios que poseen alguna relación con
     * el usuario dado.
     * <p>
     * Recibe la id del usuario en cuestión y el estado de los usuarios que
     * se quieren
     * @param usrId ID del usuario
     * @param state Estado de la amistad buscada (Friends, Pending, Bloqued)
     * @return Lista de las relaciones en el estado pedido
     */
    public List<Amigos> getFriendsFrom(Integer usrId, Integer state) {
        EntityManager em = EFactory.createEntityManager();
        List<Amigos> usrFriends = null;
        Query q = em.createNamedQuery("Amigos.findByIdUsuarioAndState").setParameter("idUsuario", usrId).setParameter("estatusRelacion", state);
        try {
            usrFriends = (List<Amigos>) q.getResultList();
            //Refrescar valores desde la db para entidades ya almacenadas
            for (Amigos usrFriend : usrFriends) {
                em.refresh(usrFriend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return usrFriends;
    }
    
    /**
     * Método usado para obtener los usuarios que poseen alguna relación con
     * el usuario dado.
     * <p>
     * Recibe la id del usuario en cuestión y el estado de los usuarios que
     * se quieren
     * @param usrId ID del usuario
     * @param state Estado de la amistad buscada (Friends, Pending, Bloqued)
     * @return Lista de las relaciones en el estado pedido
     */
    public List<Amigos> getRequestsTo(Integer usrId, Integer state) {
        EntityManager em = EFactory.createEntityManager();
        List<Amigos> usrFriends = null;
        Query q = em.createNamedQuery("Amigos.findByIdUsuarioAmigoYStatus").setParameter("idAmigo", usrId).setParameter("estatusRelacion", state);
        try {
            usrFriends = (List<Amigos>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return usrFriends;
    }
    
    /**
     * Método usado para obtener los usuarios que poseen alguna relación con
     * el usuario dado.
     * <p>
     * Recibe la id del usuario en cuestión
     * @param usrId ID del usuario
     * @return Lista completa de todas las relaciones del usuario dado
     */
    public List<Amigos> getFriendsFrom(Integer usrId) {
        EntityManager em = EFactory.createEntityManager();
        List<Amigos> usrFriends = null;
        Query q = em.createNamedQuery("Amigos.findByIdUsuario").setParameter("idUsuario", usrId);
        try {
            usrFriends = (List<Amigos>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return usrFriends;
    }
    
    public Amigos getFriendship(Integer usrId1, Integer usrId2) {
        EntityManager em = EFactory.createEntityManager();
        Amigos friendship = null;
        Query q = em.createNamedQuery("Amigos.findByIdAmigo").setParameter("idUsuario", usrId1).setParameter("idAmigo", usrId2);
        try {
            friendship = (Amigos) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return friendship;
    }
    
    public List<Amigos> getAllFriends() {
        EntityManager em = EFactory.createEntityManager();
        List<Amigos> usrFriends = null;
        Query q = em.createNamedQuery("Amigos.findAll");
        try {
            usrFriends = (List<Amigos>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return usrFriends;
    }

    public List<Usuario> getSuggestedFriendsFrom(Integer usrId) {
        EntityManager em = EFactory.createEntityManager();
        List<Usuario> usrFriends = null;
        Query q = em.createNativeQuery("SELECT DISTINCT c.* FROM USUARIO b INNER JOIN AMIGOS am1 ON b.ID_USUARIO = am1.ID_USUARIO INNER JOIN USUARIO c ON c.ID_USUARIO = am1.ID_AMIGO AND b.ID_USUARIO IN ( SELECT DISTINCT b.ID_USUARIO FROM USUARIO a INNER JOIN AMIGOS am ON a.ID_USUARIO = am.ID_USUARIO INNER JOIN USUARIO b ON b.ID_USUARIO = am.ID_AMIGO AND a.ID_USUARIO = "+usrId+" )AND c.ID_USUARIO not in( SELECT DISTINCT c.id_usuario FROM USUARIO a INNER JOIN AMIGOS am3 ON a.ID_USUARIO = am3.ID_USUARIO INNER JOIN USUARIO c on c.ID_USUARIO = am3.ID_AMIGO AND a.ID_USUARIO = "+usrId+" ) and c.ID_USUARIO <> "+usrId+";", Usuario.class).setParameter("id", usrId);
        try {
            usrFriends = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return usrFriends;
    }
    
    public List<Object[]> getSuggestedFriendsFrom2(Integer usrId) {
        EntityManager em = EFactory.createEntityManager();
        List<Object[]> usrFriends = null;
        //Retorna dos columnas: la primera es la ID del usuario y la segunda los amigos en común
        Query q = em.createNativeQuery("SELECT amigosR.`ID_USUARIO`, COUNT(*) AS enComun FROM (SELECT a.* FROM amigos a, amigos b WHERE a.`ID_USUARIO` != :id AND (b.`ID_USUARIO` = :id AND a.`ID_AMIGO`= b.`ID_AMIGO`)) AS amigosR, amigos f WHERE amigosR.`ID_USUARIO`!= f.`ID_AMIGO` AND amigosR.`ID_AMIGO` != f.`ID_AMIGO` AND f.`ID_USUARIO`= :id GROUP BY amigosR.`ID_USUARIO` ORDER BY enComun DESC;", Usuario.class).setParameter("id", usrId);
        try {
            usrFriends = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return usrFriends;
    }

    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory(DataAccess.Entity.DataBaseController.DB_NAME);

    public static final Integer Friends = 1;
    public static final Integer Pending = 2;
    public static final Integer Blocked = 3;
}
