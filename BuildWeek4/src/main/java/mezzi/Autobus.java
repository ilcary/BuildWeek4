package mezzi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Autobus extends Mezzi {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	public Autobus() {
		
	}
	
	public Autobus(int id, Stato stato, String inMain, String inServ, int seats, int route_id ) {
		super();
		this.id= id;
		super.stato= stato;
		super.inMain= inMain;
		super.inServ= inServ;
		super.seats= seats;
		super.route_id= route_id;
		
	}
	
	
	
	
}
