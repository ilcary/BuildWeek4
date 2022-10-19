package models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean obliterated;
	
	@ManyToOne
	@JoinColumn(name = "seller")
	private Seller seller;
	
	private LocalDateTime dataRelease;
	
	private LocalDateTime dataObliterated;

	public Ticket() {}
	
	public Ticket(Seller seller) {
		this.seller = seller;
		this.dataRelease = LocalDateTime.now();
	}

	public boolean isObliterated() {
		return obliterated;
	}

	public void setObliterated(boolean obliterated) {
		this.obliterated = obliterated;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public LocalDateTime getDataObliterated() {
		return dataObliterated;
	}

	public void setDataObliterated(LocalDateTime dataObliterated) {
		this.dataObliterated = dataObliterated;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getDataRelease() {
		return dataRelease;
	}
	
	
	
	


}
