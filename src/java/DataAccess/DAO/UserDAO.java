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
import javax.persistence.Query;

/**
 *
 * @author arqsoft2017i
 */
public class UserDAO {
    
    public UserDAO(){
    
    }
    
    public Usuario persist(Usuario aUser){
        EntityManager em = EFactory.createEntityManager();
        
        em.getTransaction().begin();
        
        try {
            em.persist(aUser);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
        return aUser;
    }
    
    public Usuario searchById(Integer usrId){
        EntityManager em = EFactory.createEntityManager();
        
        Usuario value = null;
        
        try {
            value = em.find(Usuario.class, usrId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public Usuario searchByName(String name){
        EntityManager em = EFactory.createEntityManager();
        
        Usuario value = null;
        
        try {
            value = em.find(Usuario.class, name);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory("ProfilerUN - EMF");
}
