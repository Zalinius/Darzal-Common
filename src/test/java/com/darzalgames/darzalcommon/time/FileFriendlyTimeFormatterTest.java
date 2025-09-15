package com.darzalgames.darzalcommon.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class FileFriendlyTimeFormatterTest {

	@Test
	void fileFriendlyTimeFormatter_default_createsFileFriendlyUTCTimeFormatter() {
		DateTimeFormatter fileFriendlyTimeFormatter = FileFriendlyTimeFormatter.fileFriendlyTimeFormatter();
		Instant time = Instant.EPOCH;

		String formattedTime = fileFriendlyTimeFormatter.format(time);

		assertFalse(formattedTime.contains(":"));
		assertFalse(formattedTime.contains("/"));
		assertEquals("1970-01-01T00.00.00Z", formattedTime);
	}

	@Test
	void dateTimeFileFriendlyUTC_onEpochInstant_formatsWell() {
		Instant time = Instant.EPOCH;

		String formattedTime = FileFriendlyTimeFormatter.dateTimeFileFriendlyFormat(time);

		assertFalse(formattedTime.contains(":"));
		assertFalse(formattedTime.contains("/"));
		assertEquals("1970-01-01T00.00.00Z", formattedTime);
	}

	@Test
	void fileFriendlyTimeFormatter_inEST_createsFileFriendlyUTCTimeFormatter() {
		DateTimeFormatter fileFriendlyTimeFormatter = FileFriendlyTimeFormatter.fileFriendlyTimeFormatter(ZoneId.of("America/Toronto"));
		Instant time = Instant.EPOCH;

		String formattedTime = fileFriendlyTimeFormatter.format(time);

		assertFalse(formattedTime.contains(":"));
		assertFalse(formattedTime.contains("/"));
		assertEquals("1969-12-31T19.00.00-0500", formattedTime); // 5 hours before the epoch
	}

}
