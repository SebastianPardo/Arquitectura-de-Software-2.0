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
public class RolesDAO {
    
    public RolesDAO(){
    
    }
    
    public Roles persist(Roles someRole){
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        try {
            em.persist(someRole);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
        }finally{
            em.close();
        }
        
        return someRole;
    }
    
    public boolean remove(Roles someRole){
        boolean value = true;
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {
            em.remove(someRole);
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
    
    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory(DataAccess.Entity.DataBaseController.DB_NAME);
}
