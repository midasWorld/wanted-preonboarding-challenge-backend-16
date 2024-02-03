package com.wanted.preonboarding.ticket.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PerformanceTypeConverter implements AttributeConverter<PerformanceType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(PerformanceType attribute) {
		return attribute.getCategory();
	}

	@Override
	public PerformanceType convertToEntityAttribute(Integer dbData) {
		return PerformanceType.ofCategory(dbData);
	}
}
