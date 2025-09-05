package com.darzalgames.darzalcommon.errorhandling;

import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.time.Instant;
import java.time.ZoneId;

import com.darzalgames.darzalcommon.time.FileFriendlyTimeFormatter;

/**
 * An abstract UncaughtException handler with timestamping facilities
 */
public abstract class LoggingUncaughtExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread originatingThread, Throwable throwable){
		TimestampedThrowable log = new TimestampedThrowable(throwable, Instant.now());
		try (PrintStream printStream = getPrintStream(log)) {
			printStream.println(FileFriendlyTimeFormatter.dateTimeFileFriendlyFormat(log.timestamp, ZoneId.systemDefault()));
			log.printStackTrace(printStream);
		}
		throwable.printStackTrace();
	}

	protected abstract PrintStream getPrintStream(TimestampedThrowable report);

}
