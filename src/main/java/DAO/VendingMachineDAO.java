package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import models.VendingMachine;

public class VendingMachineDAO {

	private static final String sus = "BuildWeek4";

	public static void save(String sellerName) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		
		VendingMachine V = new VendingMachine(sellerName);
		
		t.begin();
		
		em.persist(V);
		
		t.commit();
		
		em.close();
		emf.close();
		
	}
	
	public static VendingMachine getById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
		EntityManager em = emf.createEntityManager();
		
		VendingMachine e= em.find(VendingMachine.class, id);
		
		em.close();
		emf.close();
		
		if(!e.equals(null))
		return e;
		else {
			System.out.println("No vending machine for the boss :(");
			return null;
		}
	}
	
	public static void delete(VendingMachine e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(sus);
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		em.remove(e);
		
		t.commit();
		
		em.close();
		emf.close();
		
		
	}
	
	
}
