package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class InfiniteGridTest {

	@Test
	void get_withEmptyGrid_returnsDefaultValue() {
		InfiniteGrid<String> grid = new InfiniteGrid<>("apple");

		assertEquals("apple", grid.get(2, 82));
	}

	@Test
	void put_intoEmptySlot_insertsValueIntoGrid() {
		InfiniteGrid<String> grid = new InfiniteGrid<>("apple");

		grid.put(2, 10, "pear");

		assertEquals("pear", grid.get(2, 10));
	}

	@Test
	void emptyGrid_throwsForMinMaxFunctions() {
		InfiniteGrid<String> grid = new InfiniteGrid<>(" ");

		assertThrows(IllegalStateException.class, grid::minI);
		assertThrows(IllegalStateException.class, grid::maxI);
		assertThrows(IllegalStateException.class, grid::minJ);
		assertThrows(IllegalStateException.class, grid::maxJ);
	}

	@Test
	void emptyGrid_has0WidthAndHeight() {
		InfiniteGrid<String> grid = new InfiniteGrid<>(" ");

		assertEquals(0, grid.width());
		assertEquals(0, grid.height());
	}

	@Test
	void singleEntryGrid_returnsSingleEntryForMinMaxFunctions() {
		InfiniteGrid<String> grid = new InfiniteGrid<>(" ");
		grid.put(3, -5, "apple");

		assertEquals(3, grid.minI());
		assertEquals(3, grid.maxI());
		assertEquals(-5, grid.minJ());
		assertEquals(-5, grid.maxJ());
	}

	@Test
	void twoEntryGrid_returnsExtremeIAndJValuesForMinMaxFunctions() {
		InfiniteGrid<String> grid = new InfiniteGrid<>(" ");
		grid.put(3, -5, "apple");
		grid.put(-4, 6, "apple");

		assertEquals(-4, grid.minI());
		assertEquals(3, grid.maxI());
		assertEquals(-5, grid.minJ());
		assertEquals(6, grid.maxJ());
	}

	@Test
	void width_returnsDistanceBetweenMostExtremeIValues() {
		InfiniteGrid<String> grid = new InfiniteGrid<>(" ");
		grid.put(3, -5, "apple");
		grid.put(0, -12, "apple");
		grid.put(-4, 6, "apple");

		assertEquals(8, grid.width());
	}

	@Test
	void height_returnsDistanceBetweenMostExtremeJValues() {
		InfiniteGrid<String> grid = new InfiniteGrid<>(" ");
		grid.put(3, -5, "apple");
		grid.put(28, 0, "apple");
		grid.put(-4, 6, "apple");

		assertEquals(12, grid.height());
	}

	@Test
	void iterator_onRaggedGrid_iteratesThroughEmptyValuesToo() {
		InfiniteGrid<String> grid = new InfiniteGrid<>(" ");
		grid.put(0, 0, "a");
		grid.put(1, 0, "b");
		grid.put(1, 1, "d");
		Iterator<String> it = grid.iterator();

		assertTrue(it.hasNext());
		assertEquals("a", it.next());
		assertTrue(it.hasNext());
		assertEquals("b", it.next());
		assertTrue(it.hasNext());
		assertEquals(" ", it.next());
		assertTrue(it.hasNext());
		assertEquals("d", it.next());
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, it::next);
	}

}
