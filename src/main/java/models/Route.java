package models;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	private double startPointX;
	private double startPointY;
	private double endPointX;
	private double endPointY;
	private double speedVector;
	private double distance;
	
	private LocalTime raceStart;
	private LocalTime raceEnd;
	private LocalTime avgTravelTime;
	
	@ManyToMany
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
	private Set<Transport> transports;
	
	@ElementCollection
	private List<Integer> travelTimes;

	@ElementCollection
	private Map<Transport, Integer> totalTransportsRaces;
	
	public void race() { //randomize speedVector
		for(int i = 0; i < 10; i++) {
			if(Math.random() > 0.9) {//decelerate 
				speedVector *= ThreadLocalRandom.current().nextDouble(0.5, 0.96);	
			}
			else if (Math.random() < 0.1) {//accelerate
				speedVector *= (1 + ThreadLocalRandom.current().nextDouble(0.04, 0.5));
			}	
		}
	}
	
	public void calcTravelTime(Transport t) {
		if (travelTimes == null) {
			travelTimes = new ArrayList<Integer>();		
		}
		race(); //set speedVector
		raceEnd = raceStart.plusMinutes(Double.valueOf(distance/speedVector).longValue()); //calculate travel time and set raceEnd
		travelTimes.add(Math.abs((int)Duration.between(raceStart, raceEnd).getSeconds())); //add travel time to the list of travel times
		if(totalTransportsRaces == null){
			totalTransportsRaces = new HashMap<Transport, Integer>();
		}
		if (totalTransportsRaces.containsKey(t))
			totalTransportsRaces.put(t, totalTransportsRaces.get(t)+1);
		else totalTransportsRaces.put(t, 1);
	}
	
	public void calcAvgTime() {
		Integer sum = 0;
		for(Integer d : travelTimes) {
			sum += d;
		}
		Integer avg = sum / travelTimes.size(); //get average travel time
		
		//format average travel time in hh:mm:ss 
		int s = (int) avg % 60;
		int m = (int) (avg / 60) % 60;
		int h = (int) (avg / 3600) % 24;

		this.avgTravelTime = LocalTime.of(h, m, s); 
	}
	
	public void addTransport(Transport t) {
		if(transports == null) {
			transports = new HashSet<Transport>();
		}
		transports.add(t);
	}
	
	public void calcDistance() {
		distance = Math.sqrt((endPointY - startPointY) * (endPointY - startPointY) + (endPointX - startPointX) * (endPointX - startPointX));
	}


}
