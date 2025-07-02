package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.darzalgames.darzalcommon.functional.Do;

class VariableHeightGridTest {

	@ParameterizedTest
	@MethodSource("entryCountAndExpectedHeight")
	void getHeight_width3VariousValues_returnsExpected(int entryCount, int expectedHeight) {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(3);
		Do.xTimes(entryCount, () -> grid.add("test"));

		int result = grid.getHeight();

		assertEquals(expectedHeight, result);
	}
	private static Stream<Arguments> entryCountAndExpectedHeight() {
		return Stream.of(
				Arguments.of(2, 1),
				Arguments.of(4, 2),
				Arguments.of(6, 2),
				Arguments.of(7, 3)
				);
	}


	@Test
	void contains_onObjectNotInGrid_returnsFalse() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);
		Integer integer = 5;

		boolean result = grid.contains(integer);

		assertFalse(result);
	}

	@Test
	void contains_afterAdding_returnsTrue() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);
		Integer integer = 5;
		grid.add(integer);

		boolean result = grid.contains(integer);

		assertTrue(result);
	}


	@Test
	void contains_onObjectInGridMultipleTimes_returnsTrue() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);
		Integer integer = 5;
		grid.add(integer);
		grid.add(integer);

		boolean result = grid.contains(integer);

		assertTrue(result);
	}

	@Test
	void coordinatesOf_onObjectNotInGrid_returnsNull() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);
		Integer integer = 5;

		Coordinate result = grid.coordinatesOf(integer);

		assertNull(result);
	}

	@Test
	void coordinatesOf_onObjectInGrid_returnsCoordinates() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);
		Integer integer = 5;
		grid.add(0);
		grid.add(integer);

		Coordinate result = grid.coordinatesOf(integer);

		assertEquals(new Coordinate(0, 1), result);
	}

	@Test
	void getDirectlyAdjacentCoordinates_onMiddle_returns4DirectlyAdjacentCoordinates() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);
		Do.xTimes(16, () -> grid.add(3));

		List<Coordinate> results = grid.getDirectlyAdjacentCoordinates(1, 1);

		assertEquals(4, results.size());
		assertTrue(results.contains(new Coordinate(0, 1)));
		assertTrue(results.contains(new Coordinate(1, 0)));
		assertTrue(results.contains(new Coordinate(1, 2)));
		assertTrue(results.contains(new Coordinate(2, 1)));
	}

	@Test
	void getDirectlyAdjacentElements_onOrigin_returns2Integers() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(2);
		Do.xTimes(4, () -> grid.add(3));

		List<Integer> result = grid.getDirectlyAdjacentElements(0, 0);

		assertEquals(2, result.size());
		assertEquals(3, result.get(0));
		assertEquals(3, result.get(1));
	}

	@Test
	void getDirectlyAdjacentElements_onEdge_returns3Integers() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(3);
		Do.xTimes(9, () -> grid.add(5));

		List<Integer> result = grid.getDirectlyAdjacentElements(0, 1);

		assertEquals(3, result.size());
		assertEquals(5, result.get(0));
		assertEquals(5, result.get(1));
		assertEquals(5, result.get(2));
	}

	@Test
	void getAdjacentCoordinates_onMiddleWithEmptyGrid_returnsEmpty() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);

		List<Coordinate> results = grid.getDirectlyAdjacentCoordinates(1, 1);

		assertTrue(results.isEmpty());
	}

	@Test
	void getAdjacentCoordinates_onMiddleOfPopulatedGrid_returns8AdjacentCoordinates() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);
		Do.xTimes(16, () -> grid.add(0));

		List<Coordinate> results = grid.getAdjacentCoordinates(1, 1);

		assertEquals(8, results.size());
		assertTrue(results.contains(new Coordinate(0, 0)));
		assertTrue(results.contains(new Coordinate(0, 1)));
		assertTrue(results.contains(new Coordinate(0, 2)));
		assertTrue(results.contains(new Coordinate(1, 2)));
		assertTrue(results.contains(new Coordinate(2, 2)));
		assertTrue(results.contains(new Coordinate(2, 1)));
		assertTrue(results.contains(new Coordinate(2, 0)));
		assertTrue(results.contains(new Coordinate(1, 0)));
	}

	@Test
	void getAdjacentElements_onOrigin_returns3Objects() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(2);
		grid.add(19); // 0,0
		grid.add(22); // 0,1
		grid.add(23); // 1,0
		grid.add(24); // 1,1

		List<Integer> results = grid.getAdjacentElements(0, 0);

		assertEquals(3, results.size());
		assertTrue(results.contains(22));
		assertTrue(results.contains(23));
		assertTrue(results.contains(24));
		assertFalse(results.contains(19));
	}

	@Test
	void toArray_onGrid_returnsExpectedArray() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(3);
		Do.xTimes(6, () -> grid.add(-1));

		Object[] result = grid.toArray();
		boolean fullOfNegativeOnes = Stream.of(result).allMatch(integer -> integer.equals(-1));

		assertEquals(6, grid.size());
		assertEquals(grid.size(), result.length);
		assertTrue(fullOfNegativeOnes);
	}

	@Test
	void toArrayWithType_onGrid_returnsExpectedArray() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(1, List.of(-1, -1, -1));

		Integer[] result = grid.toArray(new Integer[0]);
		boolean fullOfNegativeOnes = Stream.of(result).allMatch(integer -> integer == -1);

		assertEquals(grid.size(), result.length);
		assertTrue(fullOfNegativeOnes);
	}

	@Test
	void stream_onGrid_streams() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(3);
		Do.xTimes(12, () -> grid.add("a"));

		String result = grid.stream().map(String::toUpperCase).reduce("", (a,b) -> a+b);

		assertEquals("AAAAAAAAAAAA", result);
	}

	@Test
	void streamCoordinatess_onGrid_streamsCoordinates() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		Do.xTimes(6, () -> grid.add("a"));

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
	void toString_doesNotThrow() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("4");

		assertDoesNotThrow(() -> grid.toString());
	}

	@ParameterizedTest
	@MethodSource("validCoordinates")
	void isInGrid_validCoordinates_returnsTrue(int i, int j) {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(4);
		Do.xTimes(12, () -> grid.add("test"));

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
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(3);
		Do.xTimes(12, () -> grid.add(""));

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

	@Test
	void hasEntryAt_validEmptyCoordinates_returnsFalse() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);

		assertFalse(grid.hasEntryAt(0, 0));
	}

	@Test
	void hasEntryAt_validFilledCoordinates_returnsTrue() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("hi");

		assertTrue(grid.hasEntryAt(0, 0));
	}

	@Test
	void hasEntryAt_coordinatesOutsideOfGrid_returnsFalse() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);

		assertFalse(grid.hasEntryAt(6, 6));
	}

	@Test
	void hasEntryAt_coordinatesBeyondInnerListButInsideOfGrid_returnsFalse() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");

		assertFalse(grid.hasEntryAt(1, 1));
	}


	@Test
	void containsAll_withValidEntries_returnsTrue() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");
		grid.add("c");

		boolean result = grid.containsAll(List.of("a", "b", "c"));

		assertTrue(result);
	}

	@Test
	void containsAll_withInvalidEntries_returnsFalse() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");
		grid.add("c");

		boolean result = grid.containsAll(List.of("X", "b", "c"));

		assertFalse(result);
	}

	@Test
	void containsAll_withEmptyCollectionOnFullGrid_returnsTrue() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");
		grid.add("c");
		grid.add("d");

		boolean result = grid.containsAll(new ArrayList<>());

		assertTrue(result);
	}

	@Test
	void addAll_appendsToExistingValuesAndIncreasesHeight() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");

		int initialHeight = grid.getHeight();
		boolean changed = grid.addAll(List.of("r", "t"));
		int finalHeight = grid.getHeight();

		assertTrue(changed);
		assertTrue(grid.containsAll(List.of("a", "b", "r", "t")));
		assertFalse(grid.contains(""));
		assertEquals(1, initialHeight);
		assertEquals(2, finalHeight);
	}

	@Test
	void addAll_emptyList_returnsFalse() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");
		grid.add("c");

		boolean changed = grid.addAll(new ArrayList<>());

		assertFalse(changed);
	}

	@Test
	void remove_existingObject_removesItFromTheGridAndReturnsTrue() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");
		grid.add("c");

		boolean changed = grid.remove("b");

		assertTrue(changed);
		assertEquals(2, grid.size());
	}

	@Test
	void remove_existingDuplicateObject_removesOneCopyFromTheGridAndReturnsTrue() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");
		grid.add("b");

		boolean changed = grid.remove("b");

		assertTrue(changed);
		assertTrue(grid.contains("b"));
		assertEquals(2, grid.size());
	}

	@Test
	void remove_nonexistingObject_returnsFalse() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");
		grid.add("c");

		boolean changed = grid.remove("x");

		assertFalse(changed);
		assertEquals(3, grid.size());
	}

	@Test
	void remove_Null_returnsFalse() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");
		grid.add("c");

		boolean changed = grid.remove(null);

		assertFalse(changed);
		assertEquals(3, grid.size());
	}

	@Test
	void removeAll_removesValidValues() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");
		grid.add("c");

		boolean changed = grid.removeAll(List.of("a", "x"));

		assertTrue(changed);
		assertFalse(grid.contains("a"));
		assertEquals(2, grid.size());
	}

	@Test
	void retainAll_retainsValidValues() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("a");
		grid.add("b");
		grid.add("b");
		grid.add("c");

		boolean changed = grid.retainAll(List.of("a", "x"));

		assertTrue(changed);
		assertTrue(grid.contains("a"));
		assertEquals(2, grid.size());
	}
}

