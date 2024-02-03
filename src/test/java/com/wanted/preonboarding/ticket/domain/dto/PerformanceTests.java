package com.wanted.preonboarding.ticket.domain.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wanted.preonboarding.ticket.domain.enums.PerformanceType;

@SpringBootTest
public class PerformanceTests {

	@Test
	public void enumTest() {
		int code = 2;
		String typeName = Arrays.stream(PerformanceType.values()).filter(value -> value.getCategory() == code)
			.findFirst()
			.orElse(PerformanceType.NONE)
			.name();
		assertEquals("EXHIBITION", typeName);
	}
}
