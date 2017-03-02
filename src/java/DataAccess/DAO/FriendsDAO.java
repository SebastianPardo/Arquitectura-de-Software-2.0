/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author arqsoft2017i
 */
public class FriendsDAO {
    
    public FriendsDAO(){
        
    }
    
    public Amigos persist(Amigos someFriends){
        EntityManager em = EFactory.createEntityManager();
        
        em.getTransaction().begin();
        
        try {
            em.persist(someFriends);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
        return someFriends;
    }
    
    public boolean editFriendship(Amigos someFriends){
        EntityManager em = EFactory.createEntityManager();
        Amigos temp = null;
        em.getTransaction().begin();        
        boolean value = true;
    
        try {
            /*
             * No se como buscar cuando la llave primaria es combinación de 2 atributos
             */            
            em.getTransaction().commit();
        } catch (Exception e) {            
            e.printStackTrace();
            em.getTransaction().rollback();
            value = false;
        }finally{            
            em.close();
        }
        
        return value;
    }
    
    public void getFriendsFrom (Integer usrId){
        /*
         * En este caso hay que obtener una lista, ya sea de amigos o identificadores de usuario
         */
    }
    
    public boolean deleteFriend(Amigos someFriends){
        return false;
    }
    
    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory("ProfilerUN - EMF");
}
