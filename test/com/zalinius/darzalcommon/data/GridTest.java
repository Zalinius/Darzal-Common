package com.zalinius.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class GridTest {
	
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
	void streamCoordinatess_onGrid_streamsCoords() throws Exception {
		Grid<String> grid = new Grid<>(2, 3);
		
		List<Coord> coords = grid.streamCoordinates().collect(Collectors.toList());

		assertEquals(6, coords.size());
		assertTrue(coords.contains(new Coord(0, 0)));
		assertTrue(coords.contains(new Coord(1, 0)));
		assertTrue(coords.contains(new Coord(0, 1)));
		assertTrue(coords.contains(new Coord(1, 1)));
		assertTrue(coords.contains(new Coord(0, 2)));
		assertTrue(coords.contains(new Coord(1, 2)));
	}
	
	@Test 
	void toString_doesNotThrow() throws Exception {
		Grid<String> grid = new Grid<>(2, 3);
		grid.set(0, 1, "4");

		assertDoesNotThrow(() -> grid.toString());
	}

}
