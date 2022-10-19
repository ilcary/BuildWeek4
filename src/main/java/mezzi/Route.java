package mezzi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Route {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;


	private String capolinea;
	private String partenza;
	private int numPer;
	
	public Route() {
		
	}

	public Route(String capolinea, String partenza, int numPer) {
		super();
		this.capolinea = capolinea;
		this.partenza = partenza;
		this.numPer = numPer;
	}

	public String getCapolinea() {
		return capolinea;
	}

	public void setCapolinea(String capolinea) {
		this.capolinea = capolinea;
	}

	public String getPartenza() {
		return partenza;
	}

	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}

	public int getNumPer() {
		return numPer;
	}

	public void setNumPer(int numPer) {
		this.numPer = numPer;
	}
		
	
	
}
