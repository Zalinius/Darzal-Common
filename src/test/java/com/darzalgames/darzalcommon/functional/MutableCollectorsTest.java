package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
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

	@Test
	void toSortedSet_collectsIntoMutableSortedSet() {
		Stream<Integer> stream = Stream.of(3, 1, 4, 1, 0, 2, 1);

		SortedSet<Integer> sortedSet = stream.collect(MutableCollectors.toSortedSet());

		assertDoesNotThrow(() -> sortedSet.add(10));
		assertDoesNotThrow(() -> sortedSet.add(20));
		assertDoesNotThrow(() -> sortedSet.remove(1));
		assertEquals(6, sortedSet.size());
		Iterator<Integer> it = sortedSet.iterator();
		assertEquals(0, it.next());
		assertEquals(2, it.next());
		assertEquals(3, it.next());
		assertEquals(4, it.next());
		assertEquals(10, it.next());
		assertEquals(20, it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void toSortedSet_withDescendingComparator_collectsIntoMutableDescendingSortedSet() {
		Stream<Integer> stream = Stream.of(3, 1, 4, 1, 0, 2, 1);
		Comparator<Integer> descendingComparator = (a, b) -> -Integer.compare(a, b);

		SortedSet<Integer> sortedSet = stream.collect(MutableCollectors.toSortedSet(descendingComparator));

		assertDoesNotThrow(() -> sortedSet.add(10));
		assertDoesNotThrow(() -> sortedSet.add(20));
		assertDoesNotThrow(() -> sortedSet.remove(1));
		assertEquals(6, sortedSet.size());
		Iterator<Integer> it = sortedSet.iterator();
		assertEquals(20, it.next());
		assertEquals(10, it.next());
		assertEquals(4, it.next());
		assertEquals(3, it.next());
		assertEquals(2, it.next());
		assertEquals(0, it.next());
		assertFalse(it.hasNext());
	}

}
