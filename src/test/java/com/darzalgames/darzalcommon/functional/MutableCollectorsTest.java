package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class MutableCollectorsTest {

	@Test
	void toList_collectsIntoMutableList() {
		Stream<Integer> stream = Stream.of(0, 1, 2);

		List<Integer> list = stream.collect(MutableCollectors.toList());

		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertDoesNotThrow(() -> list.add(3));
		assertDoesNotThrow(() -> list.set(0, -1));
		assertEquals(-1, list.get(0));
	}

	@Test
	void toSet_collectsIntoMutableSet() {
		Stream<Integer> stream = Stream.of(0, 1, 2);

		Set<Integer> set = stream.collect(MutableCollectors.toSet());

		assertTrue(set.contains(0));
		assertTrue(set.contains(1));
		assertTrue(set.contains(2));
		assertDoesNotThrow(() -> set.add(10));
		assertDoesNotThrow(() -> set.add(20));
		assertDoesNotThrow(() -> set.remove(1));
		assertEquals(4, set.size());
	}

}
