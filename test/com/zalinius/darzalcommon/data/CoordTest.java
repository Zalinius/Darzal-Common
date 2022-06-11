package com.zalinius.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CoordTest {
	
	@Test
	void accessors_bothValues_works() throws Exception {
		Coord coord = new Coord(4, 2);
		
		int firstResult = coord.i;
		double secondResult = coord.j;
		int firstGetResult = coord.getI();
		double secondGetResult = coord.getJ();
		
		
		assertEquals(4, firstResult);
		assertEquals(2, secondResult);
		assertEquals(4, firstGetResult);
		assertEquals(2, secondGetResult);
	}
	
	@Test
	void equals_withTwoIdenticalCoords_true() {
		Coord coord = new Coord(4, 2);
		
		assertEquals(coord, coord);
	}

	@Test
	void equals_withTwoEqualCoords_true() {
		Coord coord = new Coord(4, 2);
		
		assertEquals(new Coord(4, 2), coord);
	}

	@Test
	void equals_withTwoCoordsWithDifferentData_false() {
		Coord coord = new Coord(4, 2);
		
		assertNotEquals(new Coord(7, 9), coord);
	}

	@Test
	void noArgConstrucror_createsAnOriginCoord() {
		Coord coord = new Coord();
		
		assertEquals(new Coord(0, 0), coord);
	}
	
	@Test
	void tupleConstrucror_createsCoord() {
		Tuple<Integer, Integer> tuple = new Tuple<>(2, 4);
		Coord coord = new Coord(tuple);
		
		assertEquals(new Coord(2, 4), coord);
	}

	@Test
	void tupleConstrucror_tupleWithNullEntries_throwsNullPointerException() {
		Tuple<Integer, Integer> tuple = new Tuple<>(null, null);
		
		assertThrows(NullPointerException.class, () -> new Coord(tuple));
		
	}

	
	@Test 
	void toString_doesNotThrow() throws Exception {
		Coord coord = new Coord();

		assertDoesNotThrow(() -> coord.toString());
	}

}
