package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class PositiveCountMapTest {

	@Test
	void constructor_withInitialIncrements_incrementsThoseValues() {
		List<String> strings = List.of("a", "b", "c", "a");

		CountMap<String> countMap = new PositiveCountMap<>(strings);

		assertEquals(2, countMap.get("a"));
		assertEquals(1, countMap.get("b"));
		assertEquals(1, countMap.get("c"));
	}

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
