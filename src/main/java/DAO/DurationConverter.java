package DAO;

import java.time.Duration;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long>{

	@Override
	public Long convertToDatabaseColumn(Duration d) {	
		return d.getSeconds();
	}
	
	@Override
	public Duration convertToEntityAttribute(Long l) {
		return Duration.ofSeconds(l);
	}
}
