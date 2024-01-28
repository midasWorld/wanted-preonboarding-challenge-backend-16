package com.wanted.preonboarding.ticket.domain.entity;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
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
	@Column(nullable = false)
	private int type;
	@Column(nullable = false)
	private Date start_date;
	@Column(nullable = false, name = "is_reserve", columnDefinition = "varchar default 'disable'")
	private String isReserve;

	@Builder
	public Performance(UUID id, String name, int price, int round, int type, Date start_date, String isReserve) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.round = round;
		this.type = type;
		this.start_date = start_date;
		this.isReserve = isReserve;
	}
}
