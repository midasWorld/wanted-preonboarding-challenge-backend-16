package com.wanted.preonboarding.ticket.domain.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "reservation_cancel_notice")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationCancelNotice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(columnDefinition = "BINARY(16)", nullable = false, name = "performance_id")
	private UUID performanceId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, name = "phone_number")
	private String phoneNumber;

	@Builder
	public ReservationCancelNotice(UUID performanceId, String name, String phoneNumber) {
		this.performanceId = performanceId;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
}
