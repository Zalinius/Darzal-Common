package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DoTest {

	@ParameterizedTest
	@CsvSource({ "20, 20", "0, 0", "-5, 0" })
	void xTimes_callIncrementNTimes_incrementsCorrectly(int n, int expectedResult) {
		AtomicInteger counter = new AtomicInteger(0);

		Do.xTimes(n, counter::incrementAndGet);

		assertEquals(expectedResult, counter.get());
	}

	@ParameterizedTest
	@CsvSource({ "5, 15", "0, 0", "-5, 0" })
	void xTimesWithI_callAddUsingIndexNTimes_addsCorrectly(int n, int expectedResult) {
		AtomicInteger counter = new AtomicInteger(0);

		Do.xTimesWithI(n, i -> counter.addAndGet(i + 1));

		assertEquals(expectedResult, counter.get());
	}

}
