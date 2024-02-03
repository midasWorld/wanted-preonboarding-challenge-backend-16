package com.wanted.preonboarding.ticket.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.preonboarding.ticket.domain.entity.ReservationCancelNotice;

public interface ReservationCancelNoticeRepository extends JpaRepository<ReservationCancelNotice, Integer> {
	Optional<ReservationCancelNotice> findByPerformanceIdAndPhoneNumber(UUID performanceId, String phoneNumber);
	List<ReservationCancelNotice> findAllByPerformanceId(UUID performanceId);
}
