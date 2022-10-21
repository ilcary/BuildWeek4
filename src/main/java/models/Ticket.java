package models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean obliterated;
	
	@ManyToOne
	@JoinColumn(name = "seller")
	private Seller seller;
	
	private LocalDate dataRelease;
	
	private LocalDate dataObliterated;

	@ManyToOne
	@JoinColumn(name = "transport_id")
	private Transport transport;

	public Ticket() {}
	
	public Ticket(Seller seller) {
		this.seller = seller;
		this.dataRelease = LocalDate.now();
	}

	public boolean isObliterated() {
		return obliterated;
	}

	
	
	


}
