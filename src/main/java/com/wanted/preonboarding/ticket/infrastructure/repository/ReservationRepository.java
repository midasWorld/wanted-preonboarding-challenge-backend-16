package com.wanted.preonboarding.ticket.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.preonboarding.ticket.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	List<Reservation> findAllByNameAndPhoneNumber(String name, String phoneNumber);
	Optional<Reservation> findByPerformanceIdAndNameAndPhoneNumber(UUID performanceId, String name, String phoneNumber);
}
