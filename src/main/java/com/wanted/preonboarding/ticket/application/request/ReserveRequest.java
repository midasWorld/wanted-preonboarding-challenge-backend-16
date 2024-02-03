package com.wanted.preonboarding.ticket.application.request;

import java.util.UUID;

import com.wanted.preonboarding.ticket.domain.entity.Reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReserveRequest {
	private UUID performanceId;
	private String reservationName;
	private String reservationPhoneNumber;
	private String reservationStatus; // 예약; 취소;
	private long availableBalance; // 결재 가능한 금액(잔고)
	private int round;
	private char line;
	private int seat;

	public ReserveRequest(
		UUID performanceId,
		String reservationName,
		String reservationPhoneNumber,
		String reservationStatus,
		long availableBalance,
		int round,
		char line,
		int seat
	) {
		this.performanceId = performanceId;
		this.reservationName = reservationName;
		this.reservationPhoneNumber = reservationPhoneNumber;
		this.reservationStatus = reservationStatus;
		this.availableBalance = availableBalance;
		this.round = round;
		this.line = line;
		this.seat = seat;
	}

	public Reservation toEntity() {
		return Reservation.builder()
			.performanceId(this.performanceId)
			.name(this.reservationName)
			.phoneNumber(this.reservationPhoneNumber)
			.round(this.round)
			.gate(1)
			.line(this.line)
			.seat(this.seat)
			.build();
	}
}