package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import models.AuthorizedStore;
import models.TypeSeller;

public class AuthorizedStoreDAO {

	private static final String sus = "BuildWeek4";

	 public static void save(String sellerName) {
		 
	        
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction t = em.getTransaction();
	        
	     
	        AuthorizedStore s = new AuthorizedStore(sellerName);
	
	        
	        t.begin();
	        
	        em.persist(s);
	        
	        t.commit();
	        
	        em.close();
	        emf.close();
	        
	    }
	    
	    public static AuthorizedStore getById(int id) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
	        EntityManager em = emf.createEntityManager();
	        
	        AuthorizedStore e= em.find(AuthorizedStore.class, id);
	        
	        em.close();
	        emf.close();
	        
	        if(!e.equals(null))
	        return e;
	        else {
	        	System.out.println("No subscription for the boss :(");
				return null;
			}
	    }
	    
	    public static void delete(AuthorizedStore s) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction t = em.getTransaction();
	        
	        t.begin();
	        
	        em.remove(s);
	        
	        t.commit();
	        
	        em.close();
	        emf.close();
	        
	        
	    }
	
}
