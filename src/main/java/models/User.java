package models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cardId;
	
	private String name;
	
	private String lastname;
	
	//date of birth
	private String dob;
	
	//date of issue of the card 
	private LocalDateTime cardReleased; 
	
	//expiry date of the card
	private LocalDateTime cardDeadline;
	
	@OneToOne
	@JoinColumn(name = "subscription")
	@Getter @Setter private Subscription subscription;
	
	
	public User() {}

	public User(String name, String lastname, String dob) {
		this.name = name;
		this.lastname = lastname;
		this.dob = dob;
		this.cardReleased = LocalDateTime.now();
		this.cardDeadline = cardReleased.plusYears(1);
	}

	
	//get and set

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public LocalDateTime getCardDeadline() {
		return cardDeadline;
	}

	public void setCardDeadline(LocalDateTime cardDeadline) {
		this.cardDeadline = cardDeadline;
	}

	public int getCardId() {
		return cardId;
	}

	public LocalDateTime getCardReleased() {
		return cardReleased;
	}
	
	//creare rinnovo--fatto e metodo to compare date
	

}
