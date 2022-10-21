package models;

import java.time.LocalDate;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "seller")
	private Seller seller;
	
	private LocalDate SubRelease;
	
	private LocalDate SubDeadline;
	
	@Column(name = "subscription_heading")
	private String SubHeading;
	
	@OneToOne(mappedBy = "subscription")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private SubscriptionDuration duration;
	
	//return the deadline based on the duration
	public void calcDeadline() {
		if(duration.equals(SubscriptionDuration.WEEKLY)) {
			this.SubDeadline = this.SubRelease.plusWeeks(1);
		} else {
			this.SubDeadline = this.SubRelease.plusMonths(1);
		}
	}
	
	//return if the deadline date is before than today false either true, let us know if the sub is valid :)
	public boolean isValid() {
		if(this.SubDeadline.isBefore(LocalDate.now())) {
			return false;
		}
		return true;
	}

}
