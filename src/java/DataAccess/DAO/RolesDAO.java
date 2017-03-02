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
public class RolesDAO {
    
    public RolesDAO(){
    
    }
    
    public Roles persist(Roles someRole){
        EntityManager em = EFactory.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(someRole);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
        return someRole;
    }
    
    public Roles searchById(Integer roleId){
        EntityManager em = EFactory.createEntityManager();
        
        Roles value = null;
        
        try {
            value = em.find(Roles.class, roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory("ProfilerUN - EMF");
}
