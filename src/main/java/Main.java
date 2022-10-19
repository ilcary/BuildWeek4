import DAO.AuthorizedStoreDAO;
import DAO.SubscriptionDAO;
import DAO.TicketDAO;
import DAO.UserDAO;
import DAO.VendingMachineDAO;
import models.SubscriptionDuration;
import models.TypeSeller;
import models.VendingMachine;

public class Main {

	public static void main(String[] args) {

//		UserDAO.save("Carisio", "Popi Popi", "30/09/2001");
//		UserDAO.save("Daniele", "No Milketty", "12/10/1492");
		
//		AuthorizedStoreDAO.save("Edicoletty");
//		VendingMachineDAO.save("Distributore Stazione Avezzano");
//		
//		TicketDAO.save(VendingMachineDAO.getById(2));
		
//		System.out.println(VendingMachineDAO.getById(2));
//		
		SubscriptionDAO.save(AuthorizedStoreDAO.getById(1), UserDAO.getById(2), SubscriptionDuration.WEEKLY);
		SubscriptionDAO.save(VendingMachineDAO.getById(2), UserDAO.getById(1), SubscriptionDuration.MONTHLY);
		
	}

}
