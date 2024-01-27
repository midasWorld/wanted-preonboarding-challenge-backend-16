package com.wanted.preonboarding.ticket.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.preonboarding.ticket.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	Reservation findByNameAndPhoneNumber(String name, String phoneNumber);
}
