package com.zalinius.darzalcommon.errorhandling;

import java.time.Instant;

public class TimestampedThrowable extends Throwable{

	private static final long serialVersionUID = 1L;

	public final Instant timestamp;
	
	public TimestampedThrowable(Throwable error, Instant timestamp) {
		super(error);
		this.timestamp = timestamp;
	}	
}
