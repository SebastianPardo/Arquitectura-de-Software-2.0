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
public class InterestsDAO {
    
    public InterestsDAO(){
    
    }
    
    public Interes persist(Interes someInterest){
        EntityManager em = EFactory.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(someInterest);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
        return someInterest;
    }
    
    public Interes searchById(Integer intId){
        EntityManager em = EFactory.createEntityManager();
        
        Interes value = null;
        
        try {
            value = em.find(Interes.class, intId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory("ProfilerUN - EMF");
}
