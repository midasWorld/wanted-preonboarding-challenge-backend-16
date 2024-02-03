package com.wanted.preonboarding.ticket.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ReserveStatusConverter implements AttributeConverter<ReserveState, String> {

	@Override
	public String convertToDatabaseColumn(ReserveState attribute) {
		return attribute.getStatus();
	}

	@Override
	public ReserveState convertToEntityAttribute(String dbData) {
		return ReserveState.ofStatus(dbData);
	}
}
