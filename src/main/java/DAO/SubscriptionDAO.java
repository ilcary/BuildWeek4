package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

		Subscription s = Subscription.builder().seller(seller).user(user).duration(duration).build();

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
	
	public static List<Subscription> getAllSubscriptions(EntityManager em){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Subscription> cq = cb.createQuery(Subscription.class);
		Root<Subscription> root = cq.from(Subscription.class);
		cq.select(root);
		TypedQuery<Subscription> q = em.createQuery(cq);

		List<Subscription> list = q.getResultList();

		return list;
	}
	
	public static void updateSubscriptions(EntityManager em, Subscription s){
		EntityTransaction t = em.getTransaction();	
		t.begin();
		
		em.merge(s);
		
		t.commit();
	}
	


}
