package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PositiveCountMapTest {

	@Test
	void increment_onKeyNotInMap_incrementsValueTo1() {
		CountMap<String> countMap = new PositiveCountMap<>();

		countMap.increment("a");

		assertEquals(1, countMap.get("a"));
	}

	@Test
	void decrement_onKeyNotInMap_throwsIllegalStateException() {
		CountMap<String> countMap = new PositiveCountMap<>();

		assertThrows(IllegalStateException.class, () -> countMap.decrement("a"));
	}

	@Test
	void operationsWhichResultInValueBecoming0_removeKeyFromMap() {
		CountMap<String> countMap = new PositiveCountMap<>();

		countMap.increment("a");
		countMap.decrement("a");

		assertTrue(countMap.keySet().isEmpty());
	}

}
