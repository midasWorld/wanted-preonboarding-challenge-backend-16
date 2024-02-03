package com.wanted.preonboarding.ticket.domain.entity;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.wanted.preonboarding.ticket.domain.enums.PerformanceType;
import com.wanted.preonboarding.ticket.domain.enums.PerformanceTypeConverter;
import com.wanted.preonboarding.ticket.domain.enums.ReserveState;
import com.wanted.preonboarding.ticket.domain.enums.ReserveStatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Performance {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false)
	private int round;

	@Convert(converter = PerformanceTypeConverter.class)
	@Column(nullable = false)
	private PerformanceType type;

	@Column(nullable = false)
	private Date start_date;

	@Convert(converter = ReserveStatusConverter.class)
	@Column(nullable = false, name = "is_reserve", columnDefinition = "varchar default 'disable'")
	private ReserveState isReserve;

	@Builder
	public Performance(UUID id, String name, int price, int round, PerformanceType type, Date start_date, ReserveState isReserve) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.round = round;
		this.type = type;
		this.start_date = start_date;
		this.isReserve = isReserve;
	}
}
