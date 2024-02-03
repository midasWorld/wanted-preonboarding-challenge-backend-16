package com.wanted.preonboarding.ticket.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindReservationRequest {
	private String name;
	private String phoneNumber;

	public FindReservationRequest(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
}
