package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

	@Test
	void toFrequencyPercentageMap_withEmptyPositiveCountMap_returnsEmptyMap() {
		PositiveCountMap<String> countMap = new PositiveCountMap<>();

		Map<String, Float> frequencyMap = countMap.toFrequencyPercentageMap();

		assertTrue(frequencyMap.isEmpty());
	}

	@Test
	void toFrequencyPercentageMap_withVariousEntries_returnsCorrectFrequencyPercentages() {
		PositiveCountMap<String> countMap = new PositiveCountMap<>();
		countMap.increaseBy("apple", 1);
		countMap.increaseBy("banana", 2);
		countMap.increaseBy("cherry", 3);
		countMap.increaseBy("durian", 4);

		Map<String, Float> frequencyMap = countMap.toFrequencyPercentageMap();

		assertEquals(4, frequencyMap.size());
		assertEquals(0.1f, frequencyMap.get("apple"));
		assertEquals(0.2f, frequencyMap.get("banana"));
		assertEquals(0.3f, frequencyMap.get("cherry"));
		assertEquals(0.4f, frequencyMap.get("durian"));
	}

	@Test
	void toFrequencyPercentageMap_withVariousEntries_iteratesInDescendingOrderOfFrequency() {
		PositiveCountMap<String> countMap = new PositiveCountMap<>();
		countMap.increaseBy("banana", 2);
		countMap.increaseBy("apple", 1);
		countMap.increaseBy("durian", 4);
		countMap.increaseBy("cherry", 3);

		Map<String, Float> frequencyMap = countMap.toFrequencyPercentageMap();
		Iterator<String> keyIterator = frequencyMap.keySet().iterator();

		assertEquals("durian", keyIterator.next());
		assertEquals("cherry", keyIterator.next());
		assertEquals("banana", keyIterator.next());
		assertEquals("apple", keyIterator.next());
		assertFalse(keyIterator.hasNext());
	}

}
