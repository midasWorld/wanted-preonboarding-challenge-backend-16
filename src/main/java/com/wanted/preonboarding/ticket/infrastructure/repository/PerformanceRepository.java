package com.wanted.preonboarding.ticket.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.enums.ReserveState;

public interface PerformanceRepository extends JpaRepository<Performance, UUID> {
	List<Performance> findAllByIsReserve(ReserveState isReserve);
}
