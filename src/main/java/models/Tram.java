package models;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Tram extends Transport{

	
	@Getter @Setter int capacity = 50;

	//all args constructor
	public Tram(boolean inService, LocalDate startService, LocalDate startMantainance, List<Period> servicePeriod,
			List<Period> mantainancePeriod, int capacity) {
		super(inService, startService, startMantainance, servicePeriod, mantainancePeriod);
		this.capacity = capacity;
	}

	
	public Tram(boolean inService) {
		super(inService);
	}


	
	
	
	
}