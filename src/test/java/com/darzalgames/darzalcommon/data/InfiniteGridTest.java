package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;

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

		assertThrows(IllegalStateException.class, grid::minX);
		assertThrows(IllegalStateException.class, grid::maxX);
		assertThrows(IllegalStateException.class, grid::minY);
		assertThrows(IllegalStateException.class, grid::maxY);
	}

	@Test
	void singleEntryGrid_returnsSingleEntryForMinMaxFunctions() {
		InfiniteGrid<String> grid = new InfiniteGrid<>(" ");
		grid.put(3, -5, "apple");

		assertEquals(3, grid.minX());
		assertEquals(3, grid.maxX());
		assertEquals(-5, grid.minY());
		assertEquals(-5, grid.maxY());
	}

	@Test
	void twoEntryGrid_returnsExtremeIAndJValuesForMinMaxFunctions() {
		InfiniteGrid<String> grid = new InfiniteGrid<>(" ");
		grid.put(3, -5, "apple");
		grid.put(-4, 6, "apple");

		assertEquals(-4, grid.minX());
		assertEquals(3, grid.maxX());
		assertEquals(-5, grid.minY());
		assertEquals(6, grid.maxY());
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

		assertEquals("a", it.next());
		assertEquals("b", it.next());
		assertEquals(" ", it.next());
		assertEquals("d", it.next());
		assertFalse(it.hasNext());
	}

}
