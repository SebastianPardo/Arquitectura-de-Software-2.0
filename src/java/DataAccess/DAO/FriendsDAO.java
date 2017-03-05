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
    
    public FriendsDAO(){
        
    }
    
    public boolean persist(Amigos someFriends){
        boolean value = true;
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        
        try {
            Amigos someFriends_ = new Amigos(new AmigosPK(someFriends.getAmigosPK().getIdAmigo(),someFriends.getAmigosPK().getIdUsuario()), someFriends.getEstatusRelacion());
            em.persist(someFriends);
            em.persist(someFriends_);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
            value = false;
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public boolean persist(Amigos newFriendsSideA, Amigos newFriendsSideB){
        boolean value = true;
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        
        try {            
            em.persist(newFriendsSideA);
            em.persist(newFriendsSideB);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
            value = false;
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public boolean remove(Amigos someFriends){
        boolean value = true;
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        try {                        
            em.remove(someFriends);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
            value = false;
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public boolean editFriendship(Amigos someFriends){
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
        }finally{            
            em.close();
        }
        
        return value;
    }
    
    public List<Amigos> getFriendsFrom (Integer usrId){
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
    
    public List<Usuario> getSugestedFriendsFrom (Integer usrId){
        EntityManager em = EFactory.createEntityManager();
        List<Usuario> usrFriends = null;
        //Query q = em.createNamedQuery("Amigos.findAmigosRecomendados").setParameter("idUsuario", usrId);
        Query q = em.createNativeQuery("SELECT DISTINCT c.* FROM USUARIO b INNER JOIN AMIGOS am1 ON b.ID_USUARIO = am1.ID_USUARIO INNER JOIN USUARIO c ON c.ID_USUARIO = am1.ID_AMIGO AND b.ID_USUARIO IN ( SELECT DISTINCT b.ID_USUARIO FROM USUARIO a INNER JOIN AMIGOS am ON a.ID_USUARIO = am.ID_USUARIO INNER JOIN USUARIO b ON b.ID_USUARIO = am.ID_AMIGO AND a.ID_USUARIO = ?1 )AND c.ID_USUARIO not in( SELECT DISTINCT c.id_usuario FROM USUARIO a INNER JOIN AMIGOS am3 ON a.ID_USUARIO = am3.ID_USUARIO INNER JOIN USUARIO c on c.ID_USUARIO = am3.ID_AMIGO AND a.ID_USUARIO = ?1 ) and c.ID_USUARIO <>?1",Usuario.class).setParameter(1, usrId);
        try {
            usrFriends = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        for(Usuario a:usrFriends){            
            System.out.println(a.toString());
        }
        
        return usrFriends;
    }
    
    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory(DataAccess.Entity.DataBaseController.DB_NAME);
    
    public static final Integer Friends = 1;
    public static final Integer Pending = 2;
    public static final Integer Blocked = 3;
}
