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
public class PermissionsDAO {
    
    public PermissionsDAO(){
    
    }
    
    public Permisos persist(Permisos permissions){
        EntityManager em = EFactory.createEntityManager();
        
        em.getTransaction().begin();
        
        try {
            em.persist(permissions);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
        return permissions;
    }
    
    public Permisos searchById(Integer permId){
        EntityManager em = EFactory.createEntityManager();
        
        Permisos value = null;
        
        try {
            value = em.find(Permisos.class, permId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory("ProfilerUN - EMF");
}
