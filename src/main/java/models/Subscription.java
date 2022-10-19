package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	//private Seller seller;
	
	private LocalDateTime SubRelease;
	
	private LocalDateTime SubDeadline;
	
	@Column(name = "subscription_heading")
	private String SubHeading;
	
	@OneToOne(mappedBy = "mySubs")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private SubscriptionDuration duration;
	
	public Subscription() {}

	public Subscription(Seller seller,User user, SubscriptionDuration duration) {
		//this.seller = seller;
		this.SubRelease = LocalDateTime.now();
		this.user = user;
		this.duration = duration;
		this.SubDeadline = gimmeTheDeadline();
		this.SubHeading = user.getName()+" "+user.getLastname();
	}
	
	//return the deadline based on the duration
	private LocalDateTime gimmeTheDeadline() {
		if(duration.equals(SubscriptionDuration.WEEKLY)) {
			return this.SubRelease.plusWeeks(1);
		}
		return this.SubRelease.plusMonths(1);
	}
	
	//return if the deadline date is before than today false either true, let us know if the sub is valid :)
	public boolean isValid() {
		if(this.SubDeadline.isBefore(LocalDateTime.now())) {
			return false;
		}
		return true;
	}

//	public Seller getSeller() {
//		return seller;
//	}
//
//	public void setSeller(Seller seller) {
//		this.seller = seller;
//	}

	public LocalDateTime getSubRelease() {
		return SubRelease;
	}

	public void setSubRelease(LocalDateTime subRelease) {
		SubRelease = subRelease;
	}

	public LocalDateTime getSubDeadline() {
		return SubDeadline;
	}

	public void setSubDeadline(LocalDateTime subDeadline) {
		SubDeadline = subDeadline;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SubscriptionDuration getDuration() {
		return duration;
	}

	public void setDuration(SubscriptionDuration duration) {
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	
	
	
}
