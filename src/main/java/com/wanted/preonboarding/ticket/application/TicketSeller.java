package com.wanted.preonboarding.ticket.application;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.preonboarding.core.exception.BusinessException;
import com.wanted.preonboarding.core.exception.ErrorCode;
import com.wanted.preonboarding.ticket.application.event.ReservationCancelledEvent;
import com.wanted.preonboarding.ticket.application.request.FindReservationRequest;
import com.wanted.preonboarding.ticket.application.request.ReservationCancelNoticeRequest;
import com.wanted.preonboarding.ticket.application.request.ReservationCancelRequest;
import com.wanted.preonboarding.ticket.application.request.ReserveRequest;
import com.wanted.preonboarding.ticket.application.response.PerformanceResponse;
import com.wanted.preonboarding.ticket.application.response.ReserveResponse;
import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import com.wanted.preonboarding.ticket.domain.enums.ReserveState;
import com.wanted.preonboarding.ticket.infrastructure.repository.PerformanceRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.ReservationCancelNoticeRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TicketSeller {
	private final PerformanceRepository performanceRepository;
	private final ReservationRepository reservationRepository;
	private final ReservationCancelNoticeRepository reservationCancelNoticeRepository;

	private final ApplicationEventPublisher eventPublisher;

	public List<PerformanceResponse> getAllPerformances() {
		return performanceRepository.findAllByIsReserve(ReserveState.ENABLE)
			.stream().map(PerformanceResponse::of)
			.toList();
	}

	@Transactional
	public ReserveResponse reserve(ReserveRequest request) {
		Performance performance = performanceRepository.findById(request.getPerformanceId())
			.orElseThrow(() -> new BusinessException(ErrorCode.PERFORMANCE_NOT_FOUND, request.getPerformanceId()));

		if (performance.getIsReserve() == ReserveState.DISABLE) {
			throw new BusinessException(ErrorCode.PERFORMANCE_NOT_BOOKABLE);
		}

		int price = performance.getPrice();
		if (request.getAvailableBalance() - price < 0) {
			throw new BusinessException(ErrorCode.BALANCE_NOT_ENOUGH);
		}

		Reservation reservation = reservationRepository.save(request.toEntity());

		return ReserveResponse.of(reservation);
	}

	public List<ReserveResponse> getReservations(FindReservationRequest request) {
		return reservationRepository.findAllByNameAndPhoneNumber(request.getName(), request.getPhoneNumber())
			.stream().map(ReserveResponse::of)
			.toList();
	}

	@Transactional
	public void subscribeToCancelNotice(ReservationCancelNoticeRequest request) {
		performanceRepository.findById(request.getPerformanceId())
			.orElseThrow(() -> new BusinessException(ErrorCode.PERFORMANCE_NOT_FOUND, request.getPerformanceId()));

		reservationCancelNoticeRepository.findByPerformanceIdAndPhoneNumber(
			request.getPerformanceId(),
			request.getPhoneNumber()
		).ifPresent(value -> {
			throw new BusinessException(ErrorCode.RESERVATION_CANCEL_NOTICE_ALREADY_EXISTS);
		});

		reservationCancelNoticeRepository.save(request.toEntity());
	}

	@Transactional
	public void cancelReservation(ReservationCancelRequest request) {
		Performance performance = performanceRepository.findById(request.getPerformanceId())
			.orElseThrow(() -> new BusinessException(ErrorCode.PERFORMANCE_NOT_FOUND, request.getPerformanceId()));

		LocalDate performanceStartDate = performance.getStartDate().toLocalDate();
		if (!LocalDate.now().isBefore(performanceStartDate)) {
			throw new BusinessException(ErrorCode.RESERVATION_CANNOT_CANCEL_AFTER_TODAY);
		}

		Reservation reservation = reservationRepository.findByPerformanceIdAndNameAndPhoneNumber(
			request.getPerformanceId(),
			request.getReservationName(),
			request.getReservationPhoneNumber()
		).orElseThrow(() -> new BusinessException(ErrorCode.RESERVATION_NOT_FOUND));

		reservationRepository.delete(reservation);

		eventPublisher.publishEvent(ReservationCancelledEvent.builder()
				.performanceId(performance.getId())
				.performanceName(performance.getName())
				.build()
		);
	}
}
