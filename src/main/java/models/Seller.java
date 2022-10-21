package models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Seller {
	
	String SellerName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "type_seller")
	@Enumerated(EnumType.STRING)
	TypeSeller typeSeller;
	
	@OneToMany(mappedBy="seller",fetch = FetchType.EAGER)//Collections are lazy-loaded by default
	private Set<Ticket> soldTickets;
	
	@OneToMany(mappedBy="seller",fetch = FetchType.EAGER)
	private Set<Subscription> soldSubs;
	
	public Seller() {}

	public Seller(String sellerName) {
		SellerName = sellerName;
	}

	public String getSellerName() {
		return SellerName;
	}

	public void setSellerName(String sellerName) {
		SellerName = sellerName;
	}

	public TypeSeller getTypeSeller() {
		return typeSeller;
	}

	public void setTypeSeller(TypeSeller typeSeller) {
		this.typeSeller = typeSeller;
	}

	public int getId() {
		return id;
	}
	
	
	public void addTicket(Ticket t) {
		this.soldTickets.add(t);
	}
	
	
	
	
	
	
	

	
	
}
