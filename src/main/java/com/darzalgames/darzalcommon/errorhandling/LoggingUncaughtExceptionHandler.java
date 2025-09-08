package com.darzalgames.darzalcommon.errorhandling;

import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.time.Instant;
import java.time.ZoneId;

import com.darzalgames.darzalcommon.time.FileFriendlyTimeFormatter;

/**
 * An abstract UncaughtException handler with timestamping and logging facilities
 */
public abstract class LoggingUncaughtExceptionHandler implements UncaughtExceptionHandler {

	/**
	 * Builds a LoggingUncaughtExceptionHandler
	 */
	protected LoggingUncaughtExceptionHandler() {
	}

	@Override
	public void uncaughtException(Thread originatingThread, Throwable throwable){
		TimestampedThrowable log = new TimestampedThrowable(throwable, Instant.now());
		try (PrintStream printStream = getPrintStream(log)) {
			printStream.println(FileFriendlyTimeFormatter.dateTimeFileFriendlyFormat(log.getTimestamp(), ZoneId.systemDefault()));
			log.printStackTrace(printStream);
		}
		throwable.printStackTrace();
	}

	/**
	 * Get a printstream for logging uncaught exceptions
	 * @param report the throwable that will later be printed to the returned stream.<br>
	 * Useful for naming or initializing file streams
	 * @return A printstream ready for logging the given TimestampedThrowable
	 */
	protected abstract PrintStream getPrintStream(TimestampedThrowable report);

}
