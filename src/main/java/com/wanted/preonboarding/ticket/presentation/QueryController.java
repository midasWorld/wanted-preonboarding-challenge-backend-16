package com.wanted.preonboarding.ticket.presentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.application.request.FindReservationRequest;
import com.wanted.preonboarding.ticket.application.response.PerformanceResponse;
import com.wanted.preonboarding.ticket.application.response.ReserveResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/query")
@RequiredArgsConstructor
public class QueryController {
	private final TicketSeller ticketSeller;

	@GetMapping("/performances")
	public ResponseEntity<ResponseHandler<List<PerformanceResponse>>> getAllPerformances() {
		return ResponseEntity.ok()
			.body(ResponseHandler.<List<PerformanceResponse>>builder()
				.message("Success")
				.statusCode(HttpStatus.OK)
				.data(ticketSeller.getAllPerformances())
				.build()
			);
	}

	@GetMapping("/reservations")
	public ResponseEntity getReservations(@RequestBody FindReservationRequest request) {
		return ResponseEntity.ok()
			.body(ResponseHandler.<List<ReserveResponse>>builder()
				.message("Success")
				.statusCode(HttpStatus.OK)
				.data(ticketSeller.getReservations(request))
				.build()
			);
	}
}
