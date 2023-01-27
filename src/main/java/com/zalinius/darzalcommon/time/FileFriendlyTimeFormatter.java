package com.zalinius.darzalcommon.time;

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
	
	public static DateTimeFormatter fileFriendlyTimeFormatter() {
		return fileFriendlyTimeFormatter(ZoneOffset.UTC);
	}
	
	public static DateTimeFormatter fileFriendlyTimeFormatter(ZoneId timeZone) {
		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
		builder.appendPattern("yyyy-MM-dd").appendLiteral("T").appendPattern("HH.mm.ss").appendPattern("XXXX");
		return builder.toFormatter().withZone(timeZone);
	}
	
	public static String dateTimeFileFriendlyFormat(Instant instant) {
		return dateTimeFileFriendlyFormat(instant, ZoneOffset.UTC);
	}
	public static String dateTimeFileFriendlyFormat(Instant instant, ZoneId timeZone) {
		return fileFriendlyTimeFormatter(timeZone).format(instant);
	}

}
