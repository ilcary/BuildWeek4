package DAO;


import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import models.User;

public class UserDAO {

	private static final String sus = "BuildWeek4";

	 public static void save(String name, String lastname, String dob) {
	        
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction t = em.getTransaction();
	        
	     
	        User user = new User(name, lastname, dob);
	        
	        t.begin();
	        
	        em.persist(user);
	        
	        t.commit();
	        
	        em.close();
	        emf.close();
	        
	    }
	    
	    public static User getById(int id) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
	        EntityManager em = emf.createEntityManager();
	        
	        User e= em.find(User.class, id);
	        
	        em.close();
	        emf.close();
	        
	        if(!e.equals(null))
	        return e;
	        else {
	        	System.out.println("No user for the boss :(");
				return null;
			}
	    }
	    
	    public static void delete(User e) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction t = em.getTransaction();
	        
	        t.begin();
	        
	        em.remove(e);
	        
	        t.commit();
	        
	        em.close();
	        emf.close();
	        
	        
	    }
	    
	    public static void renewCard(User u) {
	    	 	
	    	if(u.getCardDeadline().isAfter(LocalDateTime.now())) {
	    				System.out.println("Hey boss your card is still valid and will expire on " + u.getCardDeadline().toString());
	    	}
	    	else {
				
	    		EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
	    		EntityManager em = emf.createEntityManager();
	    		EntityTransaction t = em.getTransaction();
	    		CriteriaBuilder cb = em.getCriteriaBuilder();
	    		t.begin();
	    		
	    		// create update
	    		CriteriaUpdate<User> update = cb.
	    				createCriteriaUpdate(User.class);
	    		
	    		// set the root class
	    		Root e = update.from(User.class);
	    		
	    		// set update and where clause
	    		update.set("cardDeadline", LocalDateTime.now().plusYears(1));//add a year from now to the deadline of the card
	    		update.where(cb.equal(e.get("cardId"), u.getCardId()));//to the card that we give as param
	    		
	    		// perform update
	    		em.createQuery(update).executeUpdate();
	    		
	    		em.flush();
	    		t.commit();
	    		
	    		em.close();
	    		emf.close();	
	    		
	    		System.out.println("The card has been updated");
			}
	    	
	    }
	    
	    
	
}
