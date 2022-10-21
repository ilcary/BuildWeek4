package DAO;

import java.time.Period;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PeriodConverter implements AttributeConverter<Period, Integer>{
	
	@Override
	public Integer convertToDatabaseColumn(Period p) {	
		return p.getDays();
	}
	
	@Override
	public Period convertToEntityAttribute(Integer i) {
		return Period.ofDays(i);
	}
}
