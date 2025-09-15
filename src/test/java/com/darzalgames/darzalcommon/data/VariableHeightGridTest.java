package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.darzalgames.darzalcommon.functional.Do;

class VariableHeightGridTest {

	@Test
	void width_returnsGridWidth() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(3);

		assertTrue(grid.isEmpty());
		assertEquals(3, grid.width());
	}

	@ParameterizedTest
	@MethodSource("entryCountAndExpectedHeight")
	void getHeight_width3VariousValues_returnsExpected(int entryCount, int expectedHeight) {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(3);
		Do.xTimes(entryCount, () -> grid.add("test"));

		int result = grid.height();

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
	void contains_objectInGridMultipleTimes_returnsTrue() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);
		Integer integer = 5;
		grid.add(integer);
		grid.add(integer);

		boolean result = grid.contains(integer);

		assertTrue(result);
	}

	@Test
	void coordinatesOf_objectNotInGrid_throwsIllegalArgumentException() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);
		Integer integer = 5;

		assertThrows(IllegalArgumentException.class, () -> grid.coordinatesOf(integer));
	}

	@Test
	void get_coordinatesNotInGrid_throwsIndexOutOfBoundsException() {
		VariableHeightGrid<Integer> grid = new VariableHeightGrid<>(4);

		assertThrows(IndexOutOfBoundsException.class, () -> grid.get(10, 10));
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
	void stream_onGrid_streams() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(3);
		Do.xTimes(12, () -> grid.add("a"));

		String result = grid.stream().map(String::toUpperCase).reduce("", (a, b) -> a + b);

		assertEquals("AAAAAAAAAAAA", result);
	}

	@Test
	void iterator_onGrid_returnsIteratorForAllValues() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(3);
		grid.add("a");
		grid.add("b");
		grid.add("c");
		grid.add("d");

		Iterator<String> it = grid.iterator();

		assertEquals("a", it.next());
		assertEquals("b", it.next());
		assertEquals("c", it.next());
		assertEquals("d", it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void toString_doesNotThrow() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("4");

		assertDoesNotThrow(() -> grid.toString());
	}

	@ParameterizedTest
	@MethodSource("validCoordinates")
	void hasEntryAt_validCoordinates_returnsTrue(int i, int j) {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(4);
		Do.xTimes(12, () -> grid.add("test"));

		boolean result = grid.hasEntryAt(i, j);

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
	void hasEntryAt_invalidCoordinates_returnsFalse(int i, int j) {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(3);
		Do.xTimes(12, () -> grid.add(""));

		boolean result = grid.hasEntryAt(i, j);

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

		int initialHeight = grid.height();
		grid.addAll(List.of("r", "t"));
		int finalHeight = grid.height();

		assertTrue(grid.containsAll(List.of("a", "b", "r", "t")));
		assertFalse(grid.contains(""));
		assertEquals(1, initialHeight);
		assertEquals(2, finalHeight);
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
	void clear_removesAllElementsFromGrid() {
		VariableHeightGrid<String> grid = new VariableHeightGrid<>(2);
		grid.add("a");
		grid.add("b");
		grid.add("c");

		grid.clear();

		assertTrue(grid.isEmpty());
		assertEquals(0, grid.size());
	}

}
