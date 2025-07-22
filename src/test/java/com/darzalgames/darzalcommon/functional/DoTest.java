package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

class DoTest {

	@Test
	void xTimes_callIncrement20Times_increments20Times() {
		AtomicInteger counter = new AtomicInteger(0);

		Do.xTimes(20, () -> counter.incrementAndGet());

		assertEquals(20, counter.get());
	}

	@Test
	void xTimes_callIncrement0Times_doesntIncrement() {
		AtomicInteger counter = new AtomicInteger(0);

		Do.xTimes(0, () -> counter.incrementAndGet());

		assertEquals(0, counter.get());
	}

	@Test
	void xTimes_callIncrementNegativeTimes_doesntIncrement() {
		AtomicInteger counter = new AtomicInteger(0);

		Do.xTimes(-5, () -> counter.incrementAndGet());

		assertEquals(0, counter.get());
	}

}
