package com.darzalgames.darzalcommon.errorhandling;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class LoggingUncaughtExceptionHandlerTest {

	@Test
	void uncaughtException_printsToGivenStream() {
		LoggingUncaughtExceptionHandlerSpy exceptionHandlerSpy = new LoggingUncaughtExceptionHandlerSpy();
		RuntimeException exception = new RuntimeException("Exception for testing LoggingUncaughtExceptionHandler");

		exceptionHandlerSpy.uncaughtException(Thread.currentThread(), exception);

		assertTrue(exceptionHandlerSpy.getPrintStreamAsString().contains("Exception for testing LoggingUncaughtExceptionHandler"));
	}

	private static class LoggingUncaughtExceptionHandlerSpy extends LoggingUncaughtExceptionHandler {

		private final ByteArrayOutputStream printStreamSpy;

		public LoggingUncaughtExceptionHandlerSpy() {
			printStreamSpy = new ByteArrayOutputStream();
		}

		@Override
		protected PrintStream getPrintStream(TimestampedThrowable report) {
			return new PrintStream(printStreamSpy, true, StandardCharsets.UTF_8);
		}

		public String getPrintStreamAsString() {
			return printStreamSpy.toString(StandardCharsets.UTF_8);
		}
	}

}
