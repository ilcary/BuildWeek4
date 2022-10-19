package models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	
	@ManyToMany
	@JoinTable(name = "routes_per_transport")
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
		this.inService = !this.inService;
		
		if(this.inService) {
			this.startService = LocalDate.now();
			if(startService.isAfter(startMantainance)) {
				MantainancePeriod.add(Period.between(startMantainance, startService));
			}
		} else {
			this.startMantainance = LocalDate.now();
			if(startMantainance.isAfter(startService)) {
				ServicePeriod.add(Period.between(startService, startMantainance));
			}
		}
	}
	
	public void addRoute(Route r) {
		routes.add(r);
	}
}
