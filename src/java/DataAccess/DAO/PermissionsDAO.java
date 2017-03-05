/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        try {
            em.persist(permissions);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
        }finally{
            em.close();
        }
        
        return permissions;
    }
    
    public boolean remove(Permisos permissions){
        boolean value = true;
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {
            em.remove(permissions);
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
    
    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory(DataAccess.Entity.DataBaseController.DB_NAME);
}
