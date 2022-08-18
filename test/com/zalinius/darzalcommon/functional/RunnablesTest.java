package com.zalinius.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class RunnablesTest {
	
	@Test
	void nullRunnable_whenRun_doesntThrow() throws Exception {
		Runnable nullRunnable = Runnables.nullRunnable();
		
		assertDoesNotThrow(nullRunnable::run);
	}

}
