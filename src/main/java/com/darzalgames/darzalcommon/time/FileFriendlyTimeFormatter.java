package com.darzalgames.darzalcommon.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Formatters for adapting the ISO time standard to work with file names (colons can't be used in file names).
 * Note that this thus doesn't follow the standard.
 */
public class FileFriendlyTimeFormatter {

	private FileFriendlyTimeFormatter() {}

	/**
	 * Creates a formatter in UTC
	 * @return a file name friendly formatter in UTC
	 */
	public static DateTimeFormatter fileFriendlyTimeFormatter() {
		return fileFriendlyTimeFormatter(ZoneOffset.UTC);
	}

	/**
	 * Creates a formatter in a given time zone
	 * @param timeZone the time zone ID
	 * @return a file name friendly formatter in the specified zone ID
	 */
	public static DateTimeFormatter fileFriendlyTimeFormatter(ZoneId timeZone) {
		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
		builder.appendPattern("yyyy-MM-dd").appendLiteral("T").appendPattern("HH.mm.ss").appendPattern("XXXX");
		return builder.toFormatter().withZone(timeZone);
	}

	/**
	 * Formats an instant for a filename in UTC
	 * @param instant the instant to format
	 * @return a string representing the instant in UTC, suitable for use in filenames
	 */
	public static String dateTimeFileFriendlyFormat(Instant instant) {
		return dateTimeFileFriendlyFormat(instant, ZoneOffset.UTC);
	}

	/**
	 * Formats an instant for a filename in the given time zone
	 * @param instant  the instant to format
	 * @param timeZone the time zone ID
	 * @return a string representing the instant in the given time zone, suitable for use in filenames
	 */
	public static String dateTimeFileFriendlyFormat(Instant instant, ZoneId timeZone) {
		return fileFriendlyTimeFormatter(timeZone).format(instant);
	}

}
