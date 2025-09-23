package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

class CountMapTest {

	@Test
	void noArgConstructor_createsEmptySize0CountMap() {
		CountMap<Integer> countMap = new CountMap<>();

		assertTrue(countMap.isEmpty());
		assertEquals(0, countMap.size());
	}

	@Test
	void constructor_withExistingCollection_createsCountMapWithCorrectCounts() {
		Collection<String> initialStrings = Arrays.asList("apple", "banana", "banana", null);

		CountMap<String> countMap = new CountMap<>(initialStrings);

		assertEquals(0, countMap.get("orange"));
		assertEquals(1, countMap.get("apple"));
		assertEquals(2, countMap.get("banana"));
		assertEquals(1, countMap.get(null));
	}

	@Test
	void constructor_withExistingNullCollection_throwsNullPointerException() {
		Collection<Void> nullCollection = null;
		assertThrows(NullPointerException.class, () -> new CountMap<>(nullCollection));
	}

	@Test
	void get_onUnusedCountMapKey_returns0() {
		CountMap<String> countMap = new CountMap<>();
		String key = "apple";

		int result = countMap.get(key);

		assertEquals(0, result);
	}

	@Test
	void get_onNullKeyAndEmptyMap_returns0() {
		CountMap<Integer> countMap = new CountMap<>();

		int result = countMap.get(null);

		assertEquals(0, result);
	}

	@Test
	void increment_withCountMapKey_incrementsCountForKey() {
		CountMap<String> countMap = new CountMap<>();
		String key = "apple";

		countMap.increment(key);
		int result = countMap.get(key);

		assertEquals(1, result);
	}

	@Test
	void increment_withNullCountMapKey_incrementsCountForNullKey() {
		CountMap<String> countMap = new CountMap<>();
		String key = null;

		countMap.increment(key);
		int result = countMap.get(key);

		assertEquals(1, result);
	}

	@Test
	void decrement_withCountMapKey_decrementsCountForKey() {
		CountMap<String> countMap = new CountMap<>();
		String key = "apple";

		countMap.decrement(key);
		int result = countMap.get(key);

		assertEquals(-1, result);
	}

	@Test
	void reset_withCountMapKeyAfterIncrement_resetsCountForKeyTo0AndRemovesKey() {
		CountMap<String> countMap = new CountMap<>();
		String key = "apple";

		countMap.increment(key);
		countMap.reset(key);

		assertEquals(0, countMap.get(key));
		assertFalse(countMap.containsKey(key));
	}

	@Test
	void incrementAndDecrement_withCountMapKey_setsCountForKeyTo0AndButKeepsKey() {
		CountMap<String> countMap = new CountMap<>();
		String key = "apple";

		countMap.increment(key);
		countMap.decrement(key);

		assertEquals(0, countMap.get(key));
		assertTrue(countMap.containsKey(key));
		assertFalse(countMap.isEmpty());
	}

	@Test
	void clear_withCountMapKeysAfterIncrement_resetsAllCountsTo0AndKeysAreNotPresent() {
		CountMap<String> countMap = new CountMap<>();
		String keyA = "apple";
		String keyB = "banana";

		countMap.increment(keyA);
		countMap.increment(keyB);
		countMap.clear();
		int resultA = countMap.get(keyA);
		int resultB = countMap.get(keyB);

		assertEquals(0, resultA);
		assertEquals(0, resultB);
		assertFalse(countMap.containsKey(keyA));
		assertFalse(countMap.containsKey(keyB));
		assertTrue(countMap.isEmpty());
	}

	@Test
	void increaseBy_withCountMapKeyAndValue_incrementsCountForKeyByValue() {
		CountMap<String> countMap = new CountMap<>();
		String key = "apple";

		countMap.increaseBy(key, 4);
		int result = countMap.get(key);

		assertEquals(4, result);
	}

	@Test
	void decreaseBy_withCountMapKeyAndValue_decrementsCountForKeyByValue() {
		CountMap<String> countMap = new CountMap<>();
		String key = "apple";

		countMap.decreaseBy(key, 7);
		int result = countMap.get(key);

		assertEquals(-7, result);
	}

	@Test
	void keySet_returnsPreviouslyUsedKeys() {
		CountMap<String> countMap = new CountMap<>();

		countMap.increment("apple");
		countMap.increment(null);
		Set<String> keySet = countMap.keySet();

		assertEquals(2, keySet.size());
		assertTrue(keySet.contains("apple"));
		assertTrue(keySet.contains(null));
	}

	@Test
	void iterator_returnsIteratorWithPreviouslyUsedKeys() {
		CountMap<String> countMap = new CountMap<>();

		countMap.increment("apple");
		countMap.increment(null);
		Iterator<String> iterator = countMap.iterator();

		Set<String> iteratorValues = new HashSet<>();
		while (iterator.hasNext()) {
			iteratorValues.add(iterator.next());
		}

		assertEquals(2, iteratorValues.size());
		assertTrue(iteratorValues.contains("apple"));
		assertTrue(iteratorValues.contains(null));
	}

	@Test
	void values_returnsCountsOfKeys() {
		CountMap<String> countMap = new CountMap<>();

		countMap.increment("a");
		countMap.increment("a");
		countMap.decrement(null);
		Collection<Integer> counts = countMap.values();

		assertEquals(2, counts.size());
		assertFalse(counts.contains(0));
		assertTrue(counts.contains(-1));
		assertTrue(counts.contains(2));
	}

	@Test
	void containsKey_returnsTrueIfKeyPresent() {
		CountMap<String> countMap = new CountMap<>();

		countMap.increment("apple");
		countMap.increment(null);
		countMap.decrement(null);

		assertFalse(countMap.containsKey("banana"));
		assertTrue(countMap.containsKey(null));
		assertTrue(countMap.containsKey("apple"));
	}

	@Test
	void total_onCountMap_sumsCountsInCountMap() {
		CountMap<String> countMap = new CountMap<>();

		countMap.increment(null);
		countMap.increaseBy(null, 3);
		countMap.decrement("apple");
		countMap.decrement("pear");

		assertEquals(2, countMap.total());
	}

	@Test
	void incrementAll_incrementsTheCountsOfManyKeys() {
		CountMap<String> countMap = new CountMap<>();
		List<String> keysToIncrement = Arrays.asList(null, "apple", "apple", "pear");

		countMap.incrementAll(keysToIncrement);

		assertEquals(0, countMap.get("banana"));
		assertEquals(1, countMap.get(null));
		assertEquals(1, countMap.get("pear"));
		assertEquals(2, countMap.get("apple"));
	}

	@Test
	void decrementAll_decrementsTheCountsOfManyKeys() {
		CountMap<String> countMap = new CountMap<>();
		List<String> keysToDecrement = Arrays.asList(null, "apple", "apple", "pear");

		countMap.decrementAll(keysToDecrement);

		assertEquals(0, countMap.get("banana"));
		assertEquals(-1, countMap.get(null));
		assertEquals(-1, countMap.get("pear"));
		assertEquals(-2, countMap.get("apple"));
	}

	@Test
	void toString_createsStringWithTrackedKeysAndCounts() {
		CountMap<String> countMap = new CountMap<>();

		countMap.increaseBy("apple", 7);
		countMap.decrement("banana");
		countMap.increment("banana");
		countMap.decrement(null);
		String toString = countMap.toString();

		assertTrue(toString.contains("apple=7"));
		assertTrue(toString.contains("banana=0"));
		assertTrue(toString.contains("null=-1"));
	}

}
