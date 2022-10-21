package DAO;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import models.AuthorizedStore;
import models.Seller;
import models.Subscription;
import models.Ticket;

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
	
	public static List<Seller> getAllSellers(EntityManager em){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Seller> cq = cb.createQuery(Seller.class);
		Root<Seller> root = cq.from(Seller.class);
		cq.select(root);
		TypedQuery<Seller> q = em.createQuery(cq);

		List<Seller> list = q.getResultList();

		return list;
	}

	public static int getNumberOfTicketSoldInTimeRange(EntityManager em, int id, LocalDate start, LocalDate end){

		Predicate<LocalDate> inRange = (d) -> d.isAfter(start) && d.isBefore(end);

		try {
			List<Subscription> subs = SubscriptionDAO.getAllSubscriptions(em);
			List<Ticket> tickets = TicketDAO.getAllTickets(em);
			int totSubs = 0;
			int totTickets = 0;

			for (Subscription sub : subs) {
				if(sub.getSeller().getId() == id && inRange.test(sub.getSubRelease())) totSubs++;
			}
			for (Ticket t : tickets) {
				if(t.getSeller().getId() == id && inRange.test(t.getDataRelease())) totTickets++;
			}

			return totSubs + totTickets;
		} catch(Exception e){
			System.out.println("Seller not found");
			return 0;
		}
	}


}
