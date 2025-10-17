package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FixedSizeGridTest {

	@Test
	void width_returnsGridWidth() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(5, 7);

		assertEquals(5, grid.width());
	}

	@Test
	void heigth_returnsGridHeight() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(5, 7);

		assertEquals(7, grid.height());
	}

	@Test
	void size_of5x7grid_is35() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(5, 7, -1);

		int result = grid.size();

		assertEquals(35, result);
	}

	@Test
	void sizeNonNull_of5x7nullGrid_is0() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(5, 7);

		int result = grid.sizeNonNull();

		assertEquals(0, result);
	}

	@Test
	void get_outsideOfGrid_throwsIndexOutOfBoundsException() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(5, 7);

		assertThrows(IndexOutOfBoundsException.class, () -> grid.get(6, 8));
	}

	@Test
	void set_outsideOfGrid_throwsIndexOutOfBoundsException() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(5, 7);

		assertThrows(IndexOutOfBoundsException.class, () -> grid.set(6, 8, 23));
	}

	@Test
	void set_whenCoordinateAlreadyHasValue_returnsOldValue() {
		final int defaultValue = 69;
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(5, 7, defaultValue);

		int replacedValue = grid.set(new Coordinate(2, 3), 420);

		assertEquals(69, replacedValue);
		assertEquals(420, grid.get(2, 3));
	}

	@Test
	void coordinatesOf_onObjectNotInGrid_returnsNull() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(4, 7, -1);
		Integer integer = 5;

		Coordinate result = grid.coordinatesOf(integer);

		assertNull(result);
	}

	@Test
	void coordinatesOf_onObjectInGrid_returnsCoordinates() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(4, 7, -1);
		Integer integer = 5;
		grid.set(2, 3, integer);

		Coordinate result = grid.coordinatesOf(integer);

		assertEquals(new Coordinate(2, 3), result);
	}

	@Test
	void getDirectlyAdjacentElements_onEmptyGrid_returns4NullObjects() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(4, 7);

		List<Integer> result = grid.getDirectlyAdjacentElements(2, 3);

		assertEquals(4, result.size());
		assertNull(result.get(0));
		assertNull(result.get(1));
		assertNull(result.get(2));
		assertNull(result.get(3));
	}

	@Test
	void getDirectlyAdjacentCoordinates_onMiddle_returns4DirectlyAdjacentCoordinates() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(4, 7);

		List<Coordinate> results = grid.getDirectlyAdjacentCoordinates(2, 3);

		assertEquals(4, results.size());
		assertTrue(results.contains(new Coordinate(2, 2)));
		assertTrue(results.contains(new Coordinate(2, 4)));
		assertTrue(results.contains(new Coordinate(1, 3)));
		assertTrue(results.contains(new Coordinate(3, 3)));
	}

	@Test
	void getDirectlyAdjacentElements_onOrigin_returns2NullObjects() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(4, 7);

		List<Integer> result = grid.getDirectlyAdjacentElements(0, 0);

		assertEquals(2, result.size());
		assertNull(result.get(0));
		assertNull(result.get(1));
	}

	@Test
	void getDirectlyAdjacentElements_onEdge_returns3NullObjects() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(4, 7);

		List<Integer> result = grid.getDirectlyAdjacentElements(2, 6);

		assertEquals(3, result.size());
		assertNull(result.get(0));
		assertNull(result.get(1));
		assertNull(result.get(2));
	}

	@Test
	void getAdjacentCoordinates_onMiddle_returns8AdjacentCoordinates() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(4, 7);

		List<Coordinate> results = grid.getAdjacentCoordinates(2, 3);

		assertEquals(8, results.size());
		assertTrue(results.contains(new Coordinate(1, 2)));
		assertTrue(results.contains(new Coordinate(2, 2)));
		assertTrue(results.contains(new Coordinate(3, 2)));
		assertTrue(results.contains(new Coordinate(1, 3)));
		assertTrue(results.contains(new Coordinate(3, 3)));
		assertTrue(results.contains(new Coordinate(1, 4)));
		assertTrue(results.contains(new Coordinate(2, 4)));
		assertTrue(results.contains(new Coordinate(3, 4)));
	}

	@Test
	void getAdjacentElements_onOrigin_returns3Objects() {
		FixedSizeGrid<Integer> grid = new FixedSizeGrid<>(4, 7, -1);
		grid.set(0, 1, 22);
		grid.set(1, 0, 23);
		grid.set(1, 1, 24);
		grid.set(0, 0, 19);

		List<Integer> results = grid.getAdjacentElements(0, 0);

		assertEquals(3, results.size());
		assertTrue(results.contains(22));
		assertTrue(results.contains(23));
		assertTrue(results.contains(24));
		assertFalse(results.contains(19));
	}

	@Test
	void constructorWithInitializer_whenGivenInitializer_initializesValues() {
		FixedSizeGrid<String> grid = new FixedSizeGrid<>(2, 3, (i, j) -> (i + "," + j));

		assertEquals(6, grid.size());

		assertEquals("0,0", grid.get(0, 0));
		assertEquals("1,0", grid.get(1, 0));
		assertEquals("0,1", grid.get(0, 1));
		assertEquals("1,1", grid.get(1, 1));
		assertEquals("0,2", grid.get(0, 2));
		assertEquals("1,2", grid.get(1, 2));
	}

	@Test
	void stream_onGrid_streams() {
		FixedSizeGrid<String> grid = new FixedSizeGrid<>(3, 4, (i, j) -> "a");

		String result = grid
				.streamElements()
				.map(String::toUpperCase)
				.reduce("", (a, b) -> a + b);

		assertEquals("AAAAAAAAAAAA", result);
	}

	@Test
	void streamCoordinatess_onGrid_streamsCoordinates() {
		FixedSizeGrid<String> grid = new FixedSizeGrid<>(2, 3);

		List<Coordinate> coordinates = grid.streamCoordinates().toList();

		assertEquals(6, coordinates.size());
		assertTrue(coordinates.contains(new Coordinate(0, 0)));
		assertTrue(coordinates.contains(new Coordinate(1, 0)));
		assertTrue(coordinates.contains(new Coordinate(0, 1)));
		assertTrue(coordinates.contains(new Coordinate(1, 1)));
		assertTrue(coordinates.contains(new Coordinate(0, 2)));
		assertTrue(coordinates.contains(new Coordinate(1, 2)));
	}

	@Test
	void iterator_createsIteratorThatIteratesThroughValuesInOrder() {
		FixedSizeGrid<String> grid = new FixedSizeGrid<>(2, 3, (i, j) -> i + "" + j);

		Iterator<String> it = grid.iterator();

		assertEquals("00", it.next());
		assertEquals("10", it.next());
		assertEquals("01", it.next());
		assertEquals("11", it.next());
		assertEquals("02", it.next());
		assertEquals("12", it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void toString_doesNotThrow() {
		FixedSizeGrid<String> grid = new FixedSizeGrid<>(2, 3, "");
		grid.set(0, 1, "4");

		assertDoesNotThrow(grid::toString);
	}

	@ParameterizedTest
	@MethodSource("validCoordinates")
	void isInGrid_validCoordinates_returnsTrue(int i, int j) {
		FixedSizeGrid<String> grid = new FixedSizeGrid<>(3, 4, "");

		boolean result = grid.isInGrid(i, j);

		assertTrue(result);
	}

	private static Stream<Arguments> validCoordinates() {
		return Stream.of(
				Arguments.of(1, 1),
				Arguments.of(2, 2),
				Arguments.of(0, 0),
				Arguments.of(2, 3),
				Arguments.of(0, 3)
		);
	}

	@ParameterizedTest
	@MethodSource("invalidCoordinates")
	void isInGrid_invalidCoordinates_returnsTrue(int i, int j) {
		FixedSizeGrid<String> grid = new FixedSizeGrid<>(3, 4, "");

		boolean result = grid.isInGrid(i, j);

		assertFalse(result);
	}

	private static Stream<Arguments> invalidCoordinates() {
		return Stream.of(
				Arguments.of(3, 4),
				Arguments.of(0, 4),
				Arguments.of(-1, 2),
				Arguments.of(2, -1)
		);
	}

}
