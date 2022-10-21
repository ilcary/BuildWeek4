package DAO;
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

import models.Seller;
import models.Ticket;


public class TicketDAO {

private static final String sus = "BuildWeek4";

	public static void save(Seller seller) {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		
		Ticket ticket = new Ticket(seller);
		
		// we also add the ticket in to the list of the seller 
		seller.addTicket(ticket);
		
		t.begin();
		
		em.persist(ticket);
		em.merge(seller);
		
		t.commit();
		
		em.close();
		emf.close();
		
	}
	
	public static Ticket getById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
		EntityManager em = emf.createEntityManager();
		
		Ticket e= em.find(Ticket.class, id);
		
		em.close();
		emf.close();
		
		if(!e.equals(null))
		return e;
		else {
			System.out.println("No user for the boss :(");
			return null;
		}
	}
	
	public static void delete(Ticket e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		em.remove(e);
		
		t.commit();
		
		em.close();
		emf.close();
		
		
	}
	
	public void obliteriaml(Ticket e) {
		
		if(e.isObliterated()) {
			System.out.println("Hey boss no scammetty pleas your ticket is still obliterated :( ");
		}
		else {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
			EntityManager em = emf.createEntityManager();
			EntityTransaction t = em.getTransaction();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			t.begin();
			
			// create update
			CriteriaUpdate<Ticket> update = cb.
					createCriteriaUpdate(Ticket.class);
			
			// set the root class
			Root<Ticket> ticketty = update.from(Ticket.class);
			
			// set update and where clause
			update.set("obliterated", true);//set the property oblitereted to true
			update.where(cb.equal(ticketty.get("id"), e.getId()));//to the Ticket that we give as param
			
			// perform update
			em.createQuery(update).executeUpdate();
			
			em.flush();
			t.commit();
			
			em.close();
			emf.close();	
			
			System.out.println("The ticket has been obliterated");
		}
		
	}

	public static List<Ticket> getAllTickets(EntityManager em){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
		Root<Ticket> root = cq.from(Ticket.class);
		cq.select(root);
		TypedQuery<Ticket> q = em.createQuery(cq);

		List<Ticket> list = q.getResultList();

		return list;
	}

}
