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
public class InfoVisibilityDAO {
    
    public InfoVisibilityDAO(){
        
    }
    
    public VisibilidadInformacion persist(VisibilidadInformacion visibilityPermissions){
        EntityManager em = EFactory.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(visibilityPermissions);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
        return visibilityPermissions;
    }
    
    public VisibilidadInformacion searchByUsrId(Integer usrId){
    
        EntityManager em = EFactory.createEntityManager();
        
        VisibilidadInformacion value = null;
        
        try {
            value = em.find(VisibilidadInformacion.class, usrId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
        
        return value;    
    }
    
    public boolean editVisibilityFrom (VisibilidadInformacion visibilityPermissions){
        EntityManager em = EFactory.createEntityManager();
        VisibilidadInformacion temp = null;
        em.getTransaction().begin();
        boolean value = true;
        
        try {
            temp = em.merge(em.find(VisibilidadInformacion.class, visibilityPermissions.getIdUsuario()));
            temp.setVisAlias(visibilityPermissions.getVisAlias());
            temp.setVisCorreo(visibilityPermissions.getVisCorreo());
            temp.setVisFechaNac(visibilityPermissions.getVisFechaNac());
            temp.setVisSexo(visibilityPermissions.getVisSexo());
            temp.setVisTelefono(visibilityPermissions.getVisTelefono());
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
    
    public EntityManagerFactory EFactory = Persistence.createEntityManagerFactory("ProfilerUN - EMF");
}
