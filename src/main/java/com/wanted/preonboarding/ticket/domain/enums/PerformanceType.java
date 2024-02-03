package com.wanted.preonboarding.ticket.domain.enums;

import java.text.MessageFormat;
import java.util.Arrays;

import lombok.Getter;

@Getter
public enum PerformanceType {
	NONE(0),
	CONCERT(1),
	EXHIBITION(2);

	private final int category;

	PerformanceType(int category) {
		this.category = category;
	}

	public static PerformanceType ofCategory(int category) {
		return Arrays.stream(PerformanceType.values())
			.filter(p -> p.getCategory() == category)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("공연 타입에 category={0} 는 존재하지 않습니다.", category)));
	}
}
