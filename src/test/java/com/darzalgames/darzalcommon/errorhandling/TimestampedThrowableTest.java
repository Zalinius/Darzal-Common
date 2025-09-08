package com.darzalgames.darzalcommon.errorhandling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;

class TimestampedThrowableTest {

	@Test
	void getTimestamp_returnsTimestamp() {
		Instant timestamp = Instant.parse("2025-09-08T10:15:30.00Z");

		TimestampedThrowable throwable = new TimestampedThrowable(new RuntimeException(), timestamp);

		assertEquals(timestamp, throwable.getTimestamp());
	}

}
