package models;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double startPointX;
	private double startPointY;
	private double endPointX;
	private double endPointY;
	private double speedVector;
	private double distance;
	
	private LocalTime raceStart;
	private LocalTime raceEnd;
	private LocalTime avgTravelTime;
	
	@ManyToMany(mappedBy = "routes")
	private Set<Transport> transports;
	
	@ElementCollection
	private List<Long> travelTimes;

	
	public void race() {
//		if(raceEnd != null) {
//			return;
//		}
		for(int i = 0; i < 10; i++) {
			if(Math.random() > 0.9) {
				speedVector *= Math.random();	
			}
			else if (Math.random() < 0.1) {
				speedVector *= (1 + Math.random());
			}	
		}
	}
		
	public void calcTravelTime() {
		if (travelTimes == null) {
			travelTimes = new ArrayList<Long>();		
		}
		
		raceEnd = raceStart.plusMinutes(Double.valueOf(distance/speedVector).longValue());
		travelTimes.add(Duration.between(raceStart, raceEnd).getSeconds());
	}
	
	public void calcAvgTime() {
		long sum = 0;
		for(long d : travelTimes) {
			sum += d;
		}
		long avg = sum / travelTimes.size();
		
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
