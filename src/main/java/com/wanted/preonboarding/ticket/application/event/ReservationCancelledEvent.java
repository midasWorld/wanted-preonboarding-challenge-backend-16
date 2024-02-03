package com.wanted.preonboarding.ticket.application.event;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationCancelledEvent {
	private final UUID performanceId;
	private final String performanceName;

	@Builder
	public ReservationCancelledEvent(UUID performanceId, String performanceName) {
		this.performanceId = performanceId;
		this.performanceName = performanceName;
	}
}
