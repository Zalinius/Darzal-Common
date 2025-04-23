package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class GridTest {
	
	@Test
	void size_of5x7grid_is35() throws Exception {
		Grid<Integer> grid = new Grid<>(5, 7);
		
		int result = grid.size();
		
		assertEquals(35, result);
		assertFalse(grid.isEmpty());
	}

	@Test
	void sizeNonNull_of5x7nullGrid_is0() throws Exception {
		Grid<Integer> grid = new Grid<>(5, 7);
		
		int result = grid.sizeNonNull();
		
		assertEquals(0, result);
	}

	@Test
	void set_outsideOfGrid_throwsIllegalArgumentException() throws Exception {
		Grid<Integer> grid = new Grid<>(5, 7);
		
		assertThrows(IllegalArgumentException.class, () -> grid.set(6, 8, 23));
	}
	
	@Test
	void contains_onObjectNotInGrid_returnsFalse() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
		Integer integer = 5;
		
		boolean result = grid.contains(integer);
		
		assertFalse(result);
	}

	@Test
	void contains_onObjectInGridOnce_returnsTrue() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
		Integer integer = 5;
		grid.set(2,  3, integer);
		
		boolean result = grid.contains(integer);
		
		assertTrue(result);
	}

	@Test
	void contains_onObjectInGridMultipleTimes_returnsTrue() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
		Integer integer = 5;
		grid.set(2, 3, integer);
		grid.set(3, 6, integer);
		
		boolean result = grid.contains(integer);
		
		assertTrue(result);
	}

	@Test
	void coordinatesOf_onObjectNotInGrid_returnsNull() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
		Integer integer = 5;
		
		Coordinate result = grid.coordinatesOf(integer);
		
		assertNull(result);
	}

	@Test
	void coordinatesOf_onObjecInGrid_returnsCoordinates() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
		Integer integer = 5;
		grid.set(2, 3, integer); 
		
		Coordinate result = grid.coordinatesOf(integer);
		
		assertEquals(new Coordinate(2, 3), result);
	}
	
	@Test
	void getDirectlyAdjacentElements_onEmptyGrid_returns4NullObjects() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
		
		List<Integer> result = grid.getDirectlyAdjacentElements(2, 3);
		
		assertEquals(4, result.size());
		assertNull(result.get(0));
		assertNull(result.get(1));
		assertNull(result.get(2));
		assertNull(result.get(3));
	}

	@Test
	void getDirectlyAdjacentCoordinates_onMiddle_returns4DirectlyAdjacentCoordinates() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
		
		List<Coordinate> results = grid.getDirectlyAdjacentCoordinates(2, 3);
		
		assertEquals(4, results.size());
		assertTrue(results.contains(new Coordinate(2, 2)));
		assertTrue(results.contains(new Coordinate(2, 4)));
		assertTrue(results.contains(new Coordinate(1, 3)));
		assertTrue(results.contains(new Coordinate(3, 3)));
	}

	@Test
	void getDirectlyAdjacentElements_onOrigin_returns2NullObjects() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
		
		List<Integer> result = grid.getDirectlyAdjacentElements(0, 0);
		
		assertEquals(2, result.size());
		assertNull(result.get(0));
		assertNull(result.get(1));
	}

	@Test
	void getDirectlyAdjacentElements_onEdge_returns3NullObjects() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
		
		List<Integer> result = grid.getDirectlyAdjacentElements(2, 6);
		
		assertEquals(3, result.size());
		assertNull(result.get(0));
		assertNull(result.get(1));
		assertNull(result.get(2));
	}
	
	@Test
	void getAdjacentCoordinates_onMiddle_returns8AdjacentCoordinates() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
		
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
	void getAdjacentElements_onOrigin_returns3Objects() throws Exception {
		Grid<Integer> grid = new Grid<>(4, 7);
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
	void constructorWithInitializerAndDefaultValue_whenGivenInitializerLambdaAndDefault_initializedValues() {
		Grid<String> grid = new Grid<>(2, 3, (i, j) -> (i+","+j), "default");
		
		assertEquals(6, grid.size());
		
		assertEquals("0,0", grid.get(0, 0));
		assertEquals("1,0", grid.get(1, 0));
		assertEquals("0,1", grid.get(0, 1));
		assertEquals("1,1", grid.get(1, 1));
		assertEquals("0,2", grid.get(0, 2));
		assertEquals("1,2", grid.get(1, 2));
		
		assertFalse(grid.isInGrid(3, 4));
		assertEquals("default", grid.get(3, 4));
	}
	
	@Test
	void stream_onGrid_streams() throws Exception {
		Grid<String> grid = new Grid<>(3, 4);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				grid.set(i, j, "a");
			}
		}
		
		String result = grid.stream().map(String::toUpperCase).reduce("", (a,b) -> a+b);

		assertEquals("AAAAAAAAAAAA", result);
	}

	@Test
	void streamCoordinatess_onGrid_streamsCoordinates() throws Exception {
		Grid<String> grid = new Grid<>(2, 3);
		
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
	void toString_doesNotThrow() throws Exception {
		Grid<String> grid = new Grid<>(2, 3);
		grid.set(0, 1, "4");

		assertDoesNotThrow(() -> grid.toString());
	}

}
