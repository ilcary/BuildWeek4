import java.time.LocalDate;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import DAO.AuthorizedStoreDAO;
import DAO.Randomizer;
import DAO.TransportDAO;
import DAO.UserDAO;
import models.Route;
import models.Seller;
import models.Ticket;
import models.Transport;
import models.User;

public class Main {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BuildWeek4");
		EntityManager em = emf.createEntityManager();
		Scanner scanner = new Scanner(System.in);
		
		/*for(int i = 0; i < 10; i++){
			
			Transport t = Randomizer.randomTransport();
			TransportDAO.addElement(em, t);

			Route r = Randomizer.randomRoute(em);
			TransportDAO.addElement(em, r);

			User u = Randomizer.randomUser(em);
			TransportDAO.addElement(em, u);

			Seller s = Randomizer.randomSeller();
			TransportDAO.addElement(em, s);

			Randomizer.randomSubscription(em);

			Ticket tic = Randomizer.randomTicket(em);			
			TransportDAO.addElement(em, tic);

			TransportDAO.validateTicket(em, t, tic);
			
		}*/
		

		System.out.println("Check the validity of the user's subscription by entering user's card number");
		System.out.println(UserDAO.checkUserSubscriptionValidity(em, Integer.parseInt(scanner.nextLine())));
		System.out.println();

		System.out.println("Check the total amount of subscriptions and tickets sold by a seller in a range of time by entering the seller's id and two dates");
		System.out.println("I.E. '1 2022-01-01 2022-12-31'");
		String[] data = scanner.nextLine().split(" ");
		System.out.println(AuthorizedStoreDAO.getNumberOfTicketSoldInTimeRange(em, Integer.parseInt(data[0]), LocalDate.parse(data[1]), LocalDate.parse(data[2])));
		System.out.println();

		System.out.println("Check the total amount of races of a transport on a specific route by entering the transport id and the route id");
		System.out.println("I.E. '1 1");
		String[] ids = scanner.nextLine().split(" ");
		System.out.println(TransportDAO.getTotalRaceOnSpecificRoute(em, Integer.parseInt(ids[0]), Integer.parseInt(ids[1])));


		scanner.close();
		em.close();
		emf.close();
	}

}
