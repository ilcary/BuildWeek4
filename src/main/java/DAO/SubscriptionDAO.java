package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import models.Seller;
import models.Subscription;
import models.SubscriptionDuration;
import models.User;


public class SubscriptionDAO {

	private static final String sus = "BuildWeek4";

	 public static void save(Seller seller,User user, SubscriptionDuration duration) {
		 
	        
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction t = em.getTransaction();
	        
	     
	        Subscription s = new Subscription(seller, user, duration);
	
	        
	        t.begin();
	        
	        em.persist(s);
	        
	        t.commit();
	        
	        em.close();
	        emf.close();
	        
	    }
	    
	    public static Subscription getById(int id) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
	        EntityManager em = emf.createEntityManager();
	        
	        Subscription e= em.find(Subscription.class, id);
	        
	        em.close();
	        emf.close();
	        
	        if(!e.equals(null))
	        return e;
	        else {
	        	System.out.println("No subscription for the boss :(");
				return null;
			}
	    }
	    
	    public static void delete(Subscription s) {
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
