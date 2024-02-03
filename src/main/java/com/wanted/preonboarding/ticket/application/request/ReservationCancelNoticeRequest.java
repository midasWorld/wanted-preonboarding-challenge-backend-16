package com.wanted.preonboarding.ticket.application.request;

import java.util.UUID;

import com.wanted.preonboarding.ticket.domain.entity.ReservationCancelNotice;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservationCancelNoticeRequest {
	private UUID performanceId;
	private String name;
	private String phoneNumber;

	public ReservationCancelNoticeRequest(UUID performanceId, String name, String phoneNumber) {
		this.performanceId = performanceId;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public ReservationCancelNotice toEntity() {
		return ReservationCancelNotice.builder()
			.performanceId(performanceId)
			.name(name)
			.phoneNumber(phoneNumber)
			.build();
	}
}
