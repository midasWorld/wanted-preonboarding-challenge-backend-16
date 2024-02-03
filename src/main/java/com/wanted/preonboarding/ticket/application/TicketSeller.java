package com.wanted.preonboarding.ticket.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.preonboarding.ticket.application.request.FindReservationRequest;
import com.wanted.preonboarding.ticket.application.request.ReserveRequest;
import com.wanted.preonboarding.ticket.application.response.PerformanceResponse;
import com.wanted.preonboarding.ticket.application.response.ReserveResponse;
import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import com.wanted.preonboarding.ticket.domain.enums.ReserveState;
import com.wanted.preonboarding.ticket.infrastructure.repository.PerformanceRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TicketSeller {
	private final PerformanceRepository performanceRepository;
	private final ReservationRepository reservationRepository;
	private long totalAmount = 0L;

	public List<PerformanceResponse> getAllPerformances() {
		return performanceRepository.findAllByIsReserve(ReserveState.ENABLE)
			.stream().map(PerformanceResponse::of)
			.toList();
	}

	@Transactional
	public ReserveResponse reserve(ReserveRequest request) {
		Performance performance = performanceRepository.findById(request.getPerformanceId())
			.orElseThrow(() -> new IllegalArgumentException("해당 공연은 존재하지 않습니다. id= " + request.getPerformanceId()));

		if (performance.getIsReserve() == ReserveState.ENABLE) {
			throw new IllegalArgumentException("해당 공연은 예매가 불가능합니다.");
		}

		int price = performance.getPrice();
		if (request.getAvailableBalance() - price < 0) {
			throw new IllegalArgumentException("사용 가능한 잔액이 부족합니다. 잔액을 확인하고 다시 시도해주세요.");
		}

		Reservation reservation = reservationRepository.save(request.toEntity());

		return ReserveResponse.of(reservation);
	}

	public List<ReserveResponse> getReservations(FindReservationRequest request) {
		return reservationRepository.findAllByNameAndPhoneNumber(request.getName(), request.getPhoneNumber())
			.stream().map(ReserveResponse::of)
			.toList();
	}
}
