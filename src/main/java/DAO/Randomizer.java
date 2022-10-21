package DAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;

import models.AuthorizedStore;
import models.Bus;
import models.Route;
import models.Seller;
import models.Subscription;
import models.SubscriptionDuration;
import models.Ticket;
import models.Tram;
import models.Transport;
import models.User;
import models.VendingMachine;

public class Randomizer {

	//------------------------------ PRIVATE METHODS ------------------------------
	private static String randomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = ThreadLocalRandom.current().nextInt(4, 9);
		Random random = new Random();
		
		String randomString = random.ints(leftLimit, rightLimit + 1)
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		return randomString.substring(0,1).toUpperCase() + randomString.substring(1);
	}

	private static LocalDate randomDate() {
		long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
		long maxDay = LocalDate.of(2021, 12, 31).toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		return LocalDate.ofEpochDay(randomDay);
	}

	//------------------------------ PUBLIC METHODS ------------------------------

	public static LocalTime randomLocalTime() {
		
		int time1 = LocalTime.of(6, 0, 0).toSecondOfDay();
		int time2 = LocalTime.of(22, 0, 0).toSecondOfDay();
		
		Random random = new Random();
		
		int randomSeconds = time1 + random.nextInt(time2 - time1);
		
		return LocalTime.ofSecondOfDay(randomSeconds);

	}

	public static Seller randomSeller() {
		boolean isMachine = Math.random() > 0.5 ? true : false;
		String name = randomString();
		if(isMachine) {
			return new VendingMachine(name);
		} else {
			return new AuthorizedStore(name);
		}
		
	}

	public static Subscription randomSubscription(EntityManager em) {

		List<Seller> sellers = AuthorizedStoreDAO.getAllSellers(em);
		List<User> users = UserDAO.getAllUsers(em);

		SubscriptionDuration sd = Math.random() > 0.5 ? SubscriptionDuration.WEEKLY : SubscriptionDuration.MONTHLY;

		Seller s = sellers.get(ThreadLocalRandom.current().nextInt(0, sellers.size()));
		Subscription sub = Subscription.builder()
					.seller(s)
					.SubRelease(LocalDate.now())
					.duration(sd)
					.build();
		sub.calcDeadline();

		TransportDAO.addElement(em, sub);
		for(User u : users){
			try {
				if (u.getSubscription() == null) {
					sub.setUser(u);
					u.setSubscription(sub);
					UserDAO.updateUser(em, u);
					SubscriptionDAO.updateSubscriptions(em, sub);
					break;
				}
			} catch (Exception e) {
                e.printStackTrace();
			}
		}
		return sub;
	}
	
	public static Ticket randomTicket(EntityManager em) {
		List<Seller> sellers = AuthorizedStoreDAO.getAllSellers(em);
		int index = ThreadLocalRandom.current().nextInt(0, sellers.size());
		
		return new Ticket(sellers.get(index));
		
	}

	public static Transport randomTransport() {
        
        boolean inService = Math.random() > 0.5 ? true : false;
		Bus b = new Bus(inService);
		Tram t = new Tram(!inService);

		if(Math.random() > 0.5) {
			return b;
		}
		else {
			return t;
		}
	}

	public static Route randomRoute(EntityManager em) {
		double startX = 10 * Math.random();
		double startY = 10 * Math.random();
		double endX = Math.random() * 1000 * startX;
		double endY = Math.random() * 1000 * startY;

		List<Transport> transports = TransportDAO.getAllTransports(em);

		int index = ThreadLocalRandom.current().nextInt(0, transports.size());
		Transport t = transports.get(index);
		
		Route r = Route.builder()
				.startPointX(startX)
				.startPointY(startY)
				.endPointX(endX)
				.endPointY(endY)
				.speedVector(30)
				.raceStart(LocalTime.now())
				.build();
		
		r.addTransport(t);
		r.calcDistance();
		r.setSpeedVector(30);
		for(int i = 0; i < 10; i++) {
			try{
				r.calcTravelTime(t);				
			} catch(NullPointerException e){
				e.printStackTrace();
			}
		}
		r.calcAvgTime();
		
		return r;
		
	}

	public static User randomUser(EntityManager em){
		String name = randomString();
		String surname = randomString();
		String dob = randomDate().toString();

		User u = new User(name, surname, dob);

		List<Subscription> subscriptions = SubscriptionDAO.getAllSubscriptions(em);

		for(Subscription s : subscriptions) {
			try{
				if(s.getUser().getCardId() == u.getCardId()){
					u.setSubscription(s);
				}
			} catch (NullPointerException e){
				System.out.println("No subscription found");
			}
		}

		return u;
	}
}
