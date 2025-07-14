package com.darzalgames.darzalcommon.errorhandling;

import java.time.Instant;

/**
 * A wrapper class for throwables, bundling in the time at which it was thrown
 */
public class TimestampedThrowable extends Throwable{

	private static final long serialVersionUID = 1L;

	/**
	 * The time at which the exception was thrown, in UTC
	 */
	public final Instant timestamp;

	/**
	 * Constructs a TimeStampledThrowable
	 * @param error the throwable for the exception
	 * @param timestamp the instant at which the exception was thrown, in UTC
	 */
	public TimestampedThrowable(Throwable error, Instant timestamp) {
		super(error);
		this.timestamp = timestamp;
	}
}
