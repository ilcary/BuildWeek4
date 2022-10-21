package DAO;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
			Root<User> e = update.from(User.class);
			
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

	public static List<User> getAllUsers(EntityManager em){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq2 = cb.createQuery(User.class);
		Root<User> root2 = cq2.from(User.class);
		cq2.select(root2);
		TypedQuery<User> q2 = em.createQuery(cq2);

		List<User> users = q2.getResultList();

		return users;
	}

	public static void updateUser(EntityManager em, User u){
		EntityTransaction t = em.getTransaction();	
		t.begin();
		
		em.merge(u);
		
		t.commit();
	}
	
	public static boolean checkUserSubscriptionValidity(EntityManager em, int id){
		return getById(id).getSubscription().isValid();
	}
	
	
}
