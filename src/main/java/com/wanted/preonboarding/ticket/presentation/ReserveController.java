package com.wanted.preonboarding.ticket.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.application.request.ReserveRequest;
import com.wanted.preonboarding.ticket.application.response.ReserveResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
	private final TicketSeller ticketSeller;

	@PostMapping
	public ResponseEntity reservation(@RequestBody ReserveRequest request) {

		ReserveResponse reservation = ticketSeller.reserve(request);

		return ResponseEntity.ok()
			.body(ResponseHandler.<ReserveResponse>builder()
				.message("Success")
				.statusCode(HttpStatus.OK)
				.data(reservation)
				.build()
			);
	}
}
