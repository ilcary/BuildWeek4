package DAO;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import models.Route;
import models.Ticket;
import models.Transport;

public class TransportDAO {
	
	//-------------------------- POST ------------------------------------
	
	public static void addElement(EntityManager em, Object o) {
		
		EntityTransaction t = em.getTransaction();	
		t.begin();
		
		em.persist(o);
		
		t.commit();
		
	}
	
	//-------------------------- GET ------------------------------------
	
	public static Transport getTransportById(EntityManager em, int id) {
		return em.find(Transport.class, id);
	}
	
	public static Route getRouteById(EntityManager em, int id) {
		return em.find(Route.class, id);
	}
	
	public static List<Transport> getAllTransports(EntityManager em){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transport> cq = cb.createQuery(Transport.class);
		Root<Transport> root = cq.from(Transport.class);
		cq.select(root);
		TypedQuery<Transport> q = em.createQuery(cq);

		List<Transport> list = q.getResultList();

		return list;
	}

	public static int getTotalRaceOnSpecificRoute(EntityManager em, int tId, int rId){
		Query q = em.createNativeQuery("SELECT totaltransportsraces FROM route_totaltransportsraces WHERE route_id = :rId AND totaltransportsraces_key = :tId");
		q.setParameter("tId", tId);
		q.setParameter("rId", rId);
		return (int)q.getSingleResult();

	}
	//-------------------------- PUT ------------------------------------
	
	public static void updateTransport(EntityManager em, Transport tr) {
		EntityTransaction t = em.getTransaction();	
		t.begin();
		
		em.merge(tr);
		
		t.commit();
	}
	
	public static void updateRoute(EntityManager em, Route r) {
		EntityTransaction t = em.getTransaction();	
		t.begin();
		
		em.merge(r);
		
		t.commit();
	}

	public static void validateTicket(EntityManager em, Transport tr, Ticket ti) {
		EntityTransaction t = em.getTransaction();	
		t.begin();
		
		tr.validateTicket(ti);
		
		em.merge(tr);
		em.merge(ti);

		t.commit();
	}
	
	public static void setTransportService(EntityManager em, int id) {
		Transport tr = getTransportById(em, id);
		
		tr.setInService();
		
		updateTransport(em, tr);
	}
	//-------------------------- DELETE ------------------------------------
	
	public static void deleteTransport(EntityManager em, int id) {
		EntityTransaction t = em.getTransaction();	
		t.begin();
		
		em.remove(getTransportById(em, id));
		
		t.commit();
	}
	
	public static void deleteRoute(EntityManager em, int id) {
		EntityTransaction t = em.getTransaction();	
		t.begin();
		
		em.remove(getRouteById(em, id));
		
		t.commit();
	}
}
