package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BiMapTest {
	
	@Test
	void addPair_toEmptyBiMap_addsPair() throws Exception {
		BiMap<Integer, String> biMap = new BiMap<>();
		
		biMap.addPair(1, "one");
		
		assertEquals("one", biMap.getSecondValue(1));
		assertEquals(1, biMap.getFirstValue("one"));
	}


	@Test
	void removePair_inBiMap_removesPair() throws Exception {
		BiMap<Integer, String> biMap = new BiMap<>();
		biMap.addPair(1, "one");

		biMap.removePair(1, "one");

		
		assertNull(biMap.getSecondValue(1));
		assertNull(biMap.getFirstValue("one"));
	}

}
