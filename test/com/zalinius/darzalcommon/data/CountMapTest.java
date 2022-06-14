package com.zalinius.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CountMapTest {
	
	@Test
	void get_onUnusedCountMapKey_returns0() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;
		
		int result = countMap.get(key);
		
		assertEquals(0, result);
	}

	@Test
	void increment_withCountMapKey_incrementsCountForKey() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;
		
		countMap.increment(key);
		int result = countMap.get(key);
		
		assertEquals(1, result);
	}

	@Test
	void decrement_withCountMapKey_decrementsCountForKey() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;
		
		countMap.decrement(key);
		int result = countMap.get(key);
		
		assertEquals(-1, result);
	}

	@Test
	void reset_withCountMapKeyAfterIncrement_resetsCountForKeyTo0() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;
		
		countMap.increment(key);
		countMap.reset(key);
		int result = countMap.get(key);
		
		assertEquals(0, result);
	}

	@Test
	void clear_withCountMapKeysAfterIncrement_resetsAllCountsTo0() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int keyA = 2;
		int keyB = 7;
		
		countMap.increment(keyA);
		countMap.increment(keyB);
		countMap.clear();
		int resultA = countMap.get(keyA);
		int resultB = countMap.get(keyB);
		
		assertEquals(0, resultA);
		assertEquals(0, resultB);
	}

	@Test
	void increaseBy_withCountMapKeyAndValue_incrementsCountForKeyByValue() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;
		
		countMap.increaseBy(key, 4);
		int result = countMap.get(key);
		
		assertEquals(4, result);
	}

	@Test
	void decreaseBy_withCountMapKeyAndValue_decrementsCountForKeyByValue() throws Exception {
		CountMap<Integer> countMap = new CountMap<>();
		int key = 2;
		
		countMap.decreaseBy(key, 7);
		int result = countMap.get(key);
		
		assertEquals(-7, result);
	}
	
	
	
	@Test
	void toString_doesNotThrow() throws Exception {
		CountMap<Integer> map = new CountMap<>();
		
		map.increaseBy(2, 7);
		map.decrement(9);
		
		assertDoesNotThrow(() -> map.toString());
	}


}
