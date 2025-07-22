package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BiMapTest {

	@Test
	void addPair_toEmptyBiMap_addsPair() {
		BiMap<Integer, String> biMap = new BiMap<>();

		biMap.addPair(1, "one");

		assertEquals("one", biMap.getSecondValue(1));
		assertEquals(1, biMap.getFirstValue("one"));
	}


	@Test
	void removePair_inBiMap_removesPair() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.addPair(1, "one");

		boolean removed = biMap.removePair(1, "one");

		assertTrue(removed);
		assertNull(biMap.getSecondValue(1));
		assertNull(biMap.getFirstValue("one"));
	}

	@Test
	void remove_NonExistingPair_ReturnsFalse(){
		BiMap <String, String> bimap = new BiMap<>();
		String input1 = "hello", input2 = "world";

		boolean removed = bimap.removePair(input1, input2);

		assertFalse(removed);
		assertNull(bimap.getFirstValue(input2));
		assertNull(bimap.getSecondValue(input1));
	}

	@Test
	void removePair_ofPresentButUnlinkedKeys_doesNotRemovePair() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.addPair(1, "one");
		biMap.addPair(2, "two");

		boolean removed = biMap.removePair(1, "two");

		assertFalse(removed);
		assertEquals(1, biMap.getFirstValue("one"));
		assertEquals(2, biMap.getFirstValue("two"));
	}

}
