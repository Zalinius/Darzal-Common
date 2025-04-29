package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

class CountMapTest {

	@Test
	void constructor_withoutArgument_createsEmptyCountMap() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();

		assertTrue(countMap.isEmpty());
	}

	@Test
	void constructor_withExistingCollection_createsCountMapWithCorrectCounts() throws Exception {
		Collection<String> initialStrings = List.of("apple", "banana", "banana");

		CountMap<String> countMap = new CountMap<>(initialStrings);

		assertEquals(0, countMap.get("orange"));
		assertEquals(1, countMap.get("apple"));
		assertEquals(2, countMap.get("banana"));
	}

	@Test
	void get_onUnusedCountMapKey_returns0() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;

		int result = countMap.get(key);

		assertEquals(0, result);
	}

	@Test
	void get_onNullKeyAndEmptyMap_returns0() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();

		int result = countMap.get(null);

		assertEquals(0, result);
	}

	@Test
	void add_withCountMapKey_incrementsCountForKey() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;

		countMap.add(key);
		int result = countMap.get(key);

		assertEquals(1, result);
	}

	@Test
	void add_withNullCountMapKey_incrementsCountForNullKey() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		Integer key = null;

		countMap.add(key);
		int result = countMap.get(key);

		assertEquals(1, result);
	}

	@Test
	void remove_withCountMapKey_decrementsCountForKey() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;

		countMap.remove(key);
		int result = countMap.get(key);

		assertEquals(-1, result);
	}

	@Test
	void remove_withNullKey_decrementsCountForNullKey() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		Integer key = null;

		countMap.remove(key);
		int result = countMap.get(key);

		assertEquals(-1, result);
	}

	@Test
	void reset_withCountMapKeyAfterAdd_resetsCountForKeyTo0() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;

		countMap.add(key);
		countMap.reset(key);
		int result = countMap.get(key);

		assertEquals(0, result);
	}

	@Test
	void clear_withCountMapKeysAfterAdds_resetsAllCountsTo0() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int keyA = 2;
		int keyB = 7;

		countMap.add(keyA);
		countMap.add(keyB);
		countMap.clear();
		int resultA = countMap.get(keyA);
		int resultB = countMap.get(keyB);

		assertEquals(0, resultA);
		assertEquals(0, resultB);
	}

	@Test
	void addMany_withCountMapKeyAndValue_incrementsCountForKeyByValue() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;

		countMap.addMany(key, 4);
		int result = countMap.get(key);

		assertEquals(4, result);
	}

	@Test
	void removeMany_withCountMapKeyAndValue_decrementsCountForKeyByValue() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;

		countMap.removeMany(key, 7);
		int result = countMap.get(key);

		assertEquals(-7, result);
	}

	@Test
	void keySet_returnsPreviouslyUsedKeys() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();

		countMap.add(4);
		countMap.add(null);
		Set<Integer> keySet = countMap.keySet();

		assertEquals(2, keySet.size());
		assertTrue(keySet.contains(4));
		assertTrue(keySet.contains(null));
	}

	@Test
	void iterator_returnsIteratorWithPreviouslyUsedKeys() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();

		countMap.add(4);
		countMap.add(null);
		Iterator<Integer> iterator = countMap.iterator();

		Set<Integer> iteratorValues = new HashSet<>();
		while (iterator.hasNext()) {
			iteratorValues.add(iterator.next());
		}

		assertEquals(2, iteratorValues.size());
		assertTrue(iteratorValues.contains(4));
		assertTrue(iteratorValues.contains(null));
	}

	@Test
	void values_returnsCountsOfKeys() throws Exception {
		CountMap<String> countMap = new CountMap<>();

		countMap.add("a");
		countMap.add("a");
		countMap.add(null);
		Collection<Integer> counts = countMap.values();

		assertEquals(2, counts.size());
		assertFalse(counts.contains(0));
		assertTrue(counts.contains(1));
		assertTrue(counts.contains(2));
	}

	@Test
	void contains_returnsTrueIfKeyPresent() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();

		countMap.add(4);
		countMap.add(null);

		assertFalse(countMap.contains(2));
		assertTrue(countMap.contains(null));
		assertTrue(countMap.contains(4));
	}

	@Test
	void isEmpty_onNewCountMap_returnsTrue() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();

		assertTrue(countMap.isEmpty());
	}

	@Test
	void isEmpty_onCountMapWithCounts_returnsFalse() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();

		countMap.add(5);

		assertFalse(countMap.isEmpty());
	}

	@Test
	void isEmpty_onCountMapWithCountsThatAre0_returnsFalse() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 5;

		countMap.add(key);
		countMap.remove(key);

		assertEquals(0, countMap.get(key));
		assertFalse(countMap.isEmpty());
	}

	@Test
	void total_onCountMap_sumsCountsInCountMap() throws Exception {
		CountMap<String> countMap = new CountMap<>();

		countMap.add(null);
		countMap.addMany(null, 3);
		countMap.remove("apple");
		countMap.remove("pear");

		assertEquals(2, countMap.total());
	}

	@Test
	void addAll_incrementsTheCountsOfManyKeys() throws Exception {
		CountMap<String> countMap = new CountMap<>();
		List<String> keysToIncrement = Arrays.asList(null, "apple", "apple", "pear");

		countMap.addAll(keysToIncrement);

		assertEquals(0, countMap.get("banana"));
		assertEquals(1, countMap.get(null));
		assertEquals(1, countMap.get("pear"));
		assertEquals(2, countMap.get("apple"));
	}

	@Test
	void removeAll_decrementsTheCountsOfManyKeys() throws Exception {
		CountMap<String> countMap = new CountMap<>();
		List<String> keysToDecrement = Arrays.asList(null, "apple", "apple", "pear");

		countMap.removeAll(keysToDecrement);

		assertEquals(0, countMap.get("banana"));
		assertEquals(-1, countMap.get(null));
		assertEquals(-1, countMap.get("pear"));
		assertEquals(-2, countMap.get("apple"));
	}

	@Test
	void retainAll_withCollectionOfKeys_resetsCountsForOtherKeys() throws Exception {
		CountMap<String> countMap = new CountMap<>();
		countMap.add("apple");
		countMap.add("apple");
		countMap.add("pear");
		countMap.add(null);
		countMap.add(null);
		countMap.add(null);
		countMap.remove("banana");

		Set<String> keysToRetain = new HashSet<>(Arrays.asList(null, "pear"));
		countMap.retainAll(keysToRetain);

		assertEquals(0, countMap.get("banana"));
		assertEquals(0, countMap.get("apple"));
		assertEquals(1, countMap.get("pear"));
		assertEquals(3, countMap.get(null));
	}

	@Test
	void containsAll_withSomeKeysPresent_returnsFalse() throws Exception {
		CountMap<String> countMap = new CountMap<>();

		countMap.add("apple");

		Set<String> desiredKeys = new HashSet<>();
		desiredKeys.add("apple");
		desiredKeys.add(null);
		desiredKeys.add("pear");
		assertFalse(countMap.containsAll(desiredKeys));
	}

	@Test
	void containsAll_withAllKeysPresent_returnsTrue() throws Exception {
		CountMap<String> countMap = new CountMap<>();

		countMap.add(null);
		countMap.remove("apple");
		countMap.add("pear");

		Set<String> desiredKeys = new HashSet<>();
		desiredKeys.add("apple");
		desiredKeys.add(null);
		assertTrue(countMap.containsAll(desiredKeys));
	}


	@Test
	void toString_doesNotThrow() throws Exception {
		CountMap<Integer> map = new CountMap<>();

		map.addMany(2, 7);
		map.remove(9);

		assertDoesNotThrow(() -> map.toString());
	}


	@Test
	void toArrayFunctions_throwsUnsupportedOperationExceptions() throws Exception {
		CountMap<Integer> map = new CountMap<>();

		assertThrows(UnsupportedOperationException.class, () -> map.toArray());
		assertThrows(UnsupportedOperationException.class, () -> map.toArray(new Integer[0]));
	}

}
