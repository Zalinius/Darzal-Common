package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class BiMapTest {

	@Test
	void putPair_toEmptyBiMap_addsPairAndReturnsNull() {
		BiMap<Integer, String> biMap = new BiMap<>();

		biMap.putPair(1, "one");

		assertFalse(biMap.isEmpty());
		assertEquals(1, biMap.size());
		assertEquals("one", biMap.getSecondValue(1));
		assertEquals(1, biMap.getFirstValue("one"));
	}

	@Test
	void putPair_withExactCollision_addsPairAndOldPairIsGone() {
		BiMap<Integer, String> biMap = new BiMap<>();

		biMap.putPair(1, "one");
		biMap.putPair(1, "one");

		assertFalse(biMap.isEmpty());
		assertEquals(1, biMap.size());
		assertEquals("one", biMap.getSecondValue(1));
		assertEquals(1, biMap.getFirstValue("one"));
	}

	@Test
	void putPair_withDoubleCollision_addsPairAndRemovesTwoCollidedPair() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.putPair(1, "one");
		biMap.putPair(2, "two");

		biMap.putPair(1, "two");

		assertFalse(biMap.isEmpty());
		assertEquals(1, biMap.size());
		assertEquals("two", biMap.getSecondValue(1));
		assertEquals(1, biMap.getFirstValue("two"));
	}

	@Test
	void removePair_inBiMap_removesPair() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.putPair(1, "one");

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
	void remove_nonExistingPair_returnsNull(){
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
		biMap.putPair(1, "one");
		biMap.putPair(2, "two");

		Tuple<Integer, String> removedPair = biMap.removePair(1, "two");

		assertNull(removedPair);
		assertEquals(1, biMap.getFirstValue("one"));
		assertEquals(2, biMap.getFirstValue("two"));
	}

	@Test
	void getKeySets_returnsApropriateKeySet() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.putPair(1, "one");
		biMap.putPair(2, "two");

		Set<Integer> firstKeySet = biMap.getFirstKeySet();
		Set<String> secondKeySet = biMap.getSecondKeyset();

		assertEquals(Set.of(1, 2), firstKeySet);
		assertEquals(Set.of("one", "two"), secondKeySet);
	}

	@Test
	void containsKey_checksIfValueIsPresent() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.putPair(1, "one");
		biMap.putPair(2, "two");

		assertTrue(biMap.containsFirstKey(1));
		assertFalse(biMap.containsFirstKey(0));
		assertTrue(biMap.containsSecondKey("one"));
		assertFalse(biMap.containsSecondKey("zero"));
	}

	@Test
	void removeByKey_inBiMapWithKeyPresent_removesPair() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.putPair(1, "one");
		biMap.putPair(2, "two");

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

	@Test
	void removeByKey_inBiMapWithoutKeyPresent_removesNothingAndReturnsNull() {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.putPair(1, "one");
		biMap.putPair(2, "two");

		Tuple<Integer, String> removedPairByFirstType = biMap.removeByFirstType(3);
		Tuple<Integer, String> removedPairBySecondType = biMap.removeBySecondType("three");

		assertEquals(2, biMap.size());
		assertNull(removedPairByFirstType);
		assertNull(removedPairBySecondType);
	}


}
