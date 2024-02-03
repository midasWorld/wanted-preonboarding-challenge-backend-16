package com.wanted.preonboarding.ticket.application.response;

import java.util.Date;
import java.util.UUID;

import com.wanted.preonboarding.ticket.domain.entity.Performance;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PerformanceResponse {
	private UUID performanceId;
	private String performanceName;
	private String performanceType;
	private Date startDate;
	private String isReserve;

	public static PerformanceResponse of(Performance entity) {
		return PerformanceResponse.builder()
			.performanceId(entity.getId())
			.performanceName(entity.getName())
			.performanceType(entity.getType().name())
			.startDate(entity.getStartDate())
			.isReserve(entity.getIsReserve().getStatus())
			.build();
	}
}
