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
import javax.persistence.Query;

/**
 *
 * @author arqsoft2017i
 */
public class AuthenticationDAO {
    
    public AuthenticationDAO(){
    
    }
    
    public Autenticacion persist(Autenticacion anAuth){
        EntityManager em = EFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        try {
            em.persist(anAuth);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
        }finally{
            em.close();
        }
        
        return anAuth;
    }
    
    public Autenticacion searhByMail (String mail){
        EntityManager em = EFactory.createEntityManager();
        Autenticacion value = null;
        
        try {
            value = em.find(Autenticacion.class, mail);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
        
        return value;
    }
    
    public Autenticacion searchByUsrId(Integer usrId) {
        EntityManager em = EFactory.createEntityManager();
        Autenticacion autenticacion = null;
        
        try {
            autenticacion = em.find(Autenticacion.class, usrId);
        } catch (Exception e){
        } finally {
            em.close();
        }
        return autenticacion;
    }
    
    public boolean editAuthentication (Autenticacion anAuth){
        EntityManager em = EFactory.createEntityManager();
        Autenticacion temp = null;
        em.getTransaction().begin();
        boolean value = true;        
        
        try {
            temp = em.merge(em.find(Autenticacion.class, anAuth.getIdUsuario()));
            temp.setCorreo(anAuth.getCorreo());
            temp.setPass(anAuth.getPass());
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
