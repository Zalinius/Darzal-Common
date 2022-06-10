package com.zalinius.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GridTest {
	
	@Test
	void size_of5x5grid_is25() throws Exception {
		Grid<Integer> grid = new Grid<>(5, 5);
		
		int result = grid.size();
		
		assertEquals(25, result);
	}

}
