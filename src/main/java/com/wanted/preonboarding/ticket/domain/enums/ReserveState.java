package com.wanted.preonboarding.ticket.domain.enums;

import java.text.MessageFormat;
import java.util.Arrays;

import lombok.Getter;

@Getter
public enum ReserveState {
	ENABLE("enable"),
	DISABLE("disable");

	private String status;

	ReserveState(String status) {
		this.status = status;
	}

	public static ReserveState ofStatus(String status) {
		return Arrays.stream(ReserveState.values())
			.filter(r -> r.status.equals(status))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("예약 가능 상태에 status={0}는 존재하지 않습니다.", status)));
	}
}
