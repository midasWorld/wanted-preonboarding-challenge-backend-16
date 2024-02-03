package com.wanted.preonboarding.ticket.application.request;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservationCancelRequest {
	private UUID performanceId;
	private String reservationName;
	private String reservationPhoneNumber;

	public ReservationCancelRequest(UUID performanceId, String reservationName, String reservationPhoneNumber) {
		this.performanceId = performanceId;
		this.reservationName = reservationName;
		this.reservationPhoneNumber = reservationPhoneNumber;
	}
}
