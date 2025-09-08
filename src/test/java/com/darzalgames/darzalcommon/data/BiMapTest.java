package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class BiMapTest {

	@Test
	void addPair_toEmptyBiMap_addsPair() {
		BiMap<Integer, String> biMap = new BiMap<>();

		biMap.addPair(1, "one");

		assertEquals("one", biMap.getSecondValue(1));
		assertEquals(1, biMap.getFirstValue("one"));
		assertFalse(biMap.isEmpty());
		assertEquals(1, biMap.size());
	}


	@Test
	void removePair_inBiMap_removesPair() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.addPair(1, "one");

		Tuple<Integer, String> removedPair = biMap.removePair(1, "one");

		assertNotNull(removedPair);
		assertEquals(1, removedPair.e());
		assertEquals("one", removedPair.f());
		assertNull(biMap.getSecondValue(1));
		assertNull(biMap.getFirstValue("one"));
		assertEquals(0, biMap.size());
		assertTrue(biMap.isEmpty());
	}

	@Test
	void remove_NonExistingPair_ReturnsFalse(){
		BiMap <String, String> bimap = new BiMap<>();
		String input1 = "hello", input2 = "world";

		Tuple<String, String> removedPair = bimap.removePair(input1, input2);

		assertNull(removedPair);
		assertNull(bimap.getFirstValue(input2));
		assertNull(bimap.getSecondValue(input1));
	}

	@Test
	void removePair_ofPresentButUnlinkedKeys_doesNotRemovePair() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.addPair(1, "one");
		biMap.addPair(2, "two");

		Tuple<Integer, String> removedPair = biMap.removePair(1, "two");

		assertNull(removedPair);
		assertEquals(1, biMap.getFirstValue("one"));
		assertEquals(2, biMap.getFirstValue("two"));
	}

	@Test
	void getKeySets_returnsApropriateKeySet() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.addPair(1, "one");
		biMap.addPair(2, "two");

		Set<Integer> firstKeySet = biMap.getFirstKeySet();
		Set<String> secondKeySet = biMap.getSecondKeyset();

		assertEquals(Set.of(1, 2), firstKeySet);
		assertEquals(Set.of("one", "two"), secondKeySet);
	}

	@Test
	void containsKey_checksIfValueIsPresent() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.addPair(1, "one");
		biMap.addPair(2, "two");

		assertTrue(biMap.containsFirstKey(1));
		assertFalse(biMap.containsFirstKey(0));
		assertTrue(biMap.containsSecondKey("one"));
		assertFalse(biMap.containsSecondKey("zero"));
	}

	@Test
	void removeByKey_inBiMapWithKeyPresent_removesPair() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.addPair(1, "one");
		biMap.addPair(2, "two");

		Tuple<Integer, String> removedPairByFirstType = biMap.removeByFirstType(1);
		Tuple<Integer, String> removedPairBySecondType = biMap.removeBySecondType("two");

		assertNotNull(removedPairByFirstType);
		assertEquals(1, removedPairByFirstType.e());
		assertEquals("one", removedPairByFirstType.f());

		assertNotNull(removedPairBySecondType);
		assertEquals(2, removedPairBySecondType.e());
		assertEquals("two", removedPairBySecondType.f());

		assertTrue(biMap.isEmpty());
	}


}
