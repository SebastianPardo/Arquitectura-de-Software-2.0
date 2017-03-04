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
public class CategoryDAO {
    
    public CategoryDAO(){
    
    }
    
    public Categoria persist(Categoria aCategory){
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        
        try {
            em.persist(aCategory);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
        }finally{
            em.close();
        }
        
        return aCategory;
    }
    
    public Categoria searchById(Integer catId){
        EntityManager em = EFactory.createEntityManager();
        
        Categoria value = null;
        
        try {
            value = em.find(Categoria.class, catId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public Categoria searchByName(String name){
        EntityManager em = EFactory.createEntityManager();
        
        Categoria value = null;
        
        try {
            value = em.find(Categoria.class, name);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public boolean editCategory(Categoria aCat){
        EntityManager em = EFactory.createEntityManager();
        Categoria temp = null;
        em.getTransaction().begin();
        boolean value = true;
        
        try {
            temp = em.merge(em.find(Categoria.class, aCat.getIdCategoria()));
            temp.setDescripcionCategoria(aCat.getDescripcionCategoria());
            temp.setInteresCollection(aCat.getInteresCollection());
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
    
    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory(DataAccess.Entity.DataBaseController.DB_NAME);
}
