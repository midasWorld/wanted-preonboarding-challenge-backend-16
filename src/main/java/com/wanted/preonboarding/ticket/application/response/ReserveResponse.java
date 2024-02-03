package com.wanted.preonboarding.ticket.application.response;

import java.util.UUID;

import com.wanted.preonboarding.ticket.domain.entity.Reservation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReserveResponse {
	private UUID performanceId;
	private String reservationName;
	private String reservationPhoneNumber;
	private int round;
	private int gate;
	private char line;
	private int seat;

	public static ReserveResponse of(Reservation reservation) {
		return ReserveResponse.builder()
			.performanceId(reservation.getPerformanceId())
			.reservationName(reservation.getName())
			.reservationPhoneNumber(reservation.getPhoneNumber())
			.round(reservation.getRound())
			.gate(reservation.getGate())
			.line(reservation.getLine())
			.seat(reservation.getSeat())
			.build();
	}
}