package models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity  
@Table(name="transports")  
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  
public class Transport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	protected boolean inService;
	protected LocalDate startService;
	protected LocalDate startMantainance;
	
	@ElementCollection
	protected List<Period> ServicePeriod = new ArrayList<Period>();
	@ElementCollection
	protected List<Period> MantainancePeriod = new ArrayList<Period>();

	@OneToMany(mappedBy = "transport")
	protected Set<Ticket> tickets = new HashSet<Ticket>();
	
	@ManyToMany(mappedBy = "transports")
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
	protected List<Route> routes = new ArrayList<Route>();
	
	//AllArgsConstructor
	public Transport(boolean inService, LocalDate startService, LocalDate startMantainance,
			List<Period> servicePeriod, List<Period> mantainancePeriod) {
		this.inService = inService;
		this.startService = startService;
		this.startMantainance = startMantainance;
		ServicePeriod = servicePeriod;
		MantainancePeriod = mantainancePeriod;
	}
	
	//Real Constructor
	public Transport(boolean inService) {
		this.inService = inService;
	}
	
	public void setInService() {
		this.inService = !this.inService; //toggle inService
		
		if(this.inService) {//if inService set startInService to now
			this.startService = LocalDate.now(); 
			if(startMantainance != null && startService.isAfter(startMantainance)) {//if transport was in mantainance, calculate mantainance period
				MantainancePeriod.add(Period.between(startMantainance, startService));
			}
		} else { //if not inService set startMantainance to now
			this.startMantainance = LocalDate.now(); 
			if(startService != null && startMantainance.isAfter(startService)) { //if transport was in service, calculate service period
				ServicePeriod.add(Period.between(startService, startMantainance));
			}
		}
	}
	
	public void addRoute(Route r) {
		routes.add(r);
	}

	public void validateTicket(Ticket t) {
		if(inService && !t.isObliterated()) {
			t.setObliterated(true);
			t.setDataObliterated(LocalDate.now());
			tickets.add(t);
		}
	}
}
