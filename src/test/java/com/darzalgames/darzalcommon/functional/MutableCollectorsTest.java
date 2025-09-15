package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class MutableCollectorsTest {

	@Test
	void toList_collectsIntoMutableList() {
		Stream<Integer> stream = List.of(0, 1, 2).stream();

		List<Integer> list = stream.collect(MutableCollectors.toList());

		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertDoesNotThrow(() -> list.add(3));
		assertDoesNotThrow(() -> list.set(0, -1));
		assertEquals(-1, list.get(0));
	}

}
