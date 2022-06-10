package com.zalinius.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GridTest {
	
	@Test
	void size_of5x5grid_is25() throws Exception {
		Grid<Integer> grid = new Grid<>(5, 5);
		
		int result = grid.size();
		
		assertEquals(25, result);
		assertFalse(grid.isEmpty());
	}
	
	@Test
	void stream_onGrid_streams() throws Exception {
		Grid<String> grid = new Grid<>(3, 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				grid.set(i, j, "a");
			}
		}
		
		String result = grid.stream().map(String::toUpperCase).reduce("", (a,b) -> a+b);

		assertEquals("AAAAAAAAA", result);
	}

}
