package com.wanted.preonboarding.ticket.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.preonboarding.ticket.domain.entity.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, UUID> {
	List<Performance> findByIsReserve(String isReserve);

	Performance findByName(String name);
}
