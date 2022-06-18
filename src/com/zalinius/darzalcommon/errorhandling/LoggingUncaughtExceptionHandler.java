package com.zalinius.darzalcommon.errorhandling;

import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.time.Instant;
import java.time.ZoneId;

import com.zalinius.darzalcommon.time.FileFriendlyTimeFormatter;

public abstract class LoggingUncaughtExceptionHandler implements UncaughtExceptionHandler {

	public LoggingUncaughtExceptionHandler() {}

	@Override
	public void uncaughtException(Thread originatingThread, Throwable throwable) {
		TimestampedThrowable log = new TimestampedThrowable(throwable, Instant.now());
		try (PrintStream printStream = getPrintStream(log)) {
			printStream.println(FileFriendlyTimeFormatter.dateTimeFileFriendlyFormat(log.timestamp, ZoneId.systemDefault()));
			log.printStackTrace(printStream);	
		}
	}

	protected abstract PrintStream getPrintStream(TimestampedThrowable report);

}
