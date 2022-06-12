package com.zalinius.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

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
	
	@Test
	void taxiDistance_betweenCoordinateAndItself_is0() throws Exception {
		Coord coord = new Coord(2, 4);
		
		int distance = coord.taxiDistance(coord);
		
		assertEquals(0, distance);
	}
	@Test
	void taxiDistance_betweenCoordAndOrigin_isSumOfCoordinates() throws Exception {
		Coord coord = new Coord(2, 4);
		Coord origin = new Coord(0, 0);
		
		int distance = coord.taxiDistance(origin);
		int distanceReversed = origin.taxiDistance(coord);
		
		assertEquals(6, distance);
		assertEquals(6, distanceReversed);
	}
	@Test
	void taxiDistance_betweenTwoCoords_isTheirTaxiDistance() throws Exception {
		Coord coord1 = new Coord(3, 4);
		Coord coord2 = new Coord(2, 7);
		
		int distance = coord1.taxiDistance(coord2);
		int distanceReversed = coord2.taxiDistance(coord1);
		
		assertEquals(4, distance);
		assertEquals(4, distanceReversed);
	}

	@Test
	void kingDistance_betweenCoordinateAndItself_is0() throws Exception {
		Coord coord = new Coord(2, 4);
		
		int distance = coord.kingDistance(coord);
		
		assertEquals(0, distance);
	}
	@Test
	void kingDistance_betweenCoordAndOrigin_isLargestOfCoordinates() throws Exception {
		Coord coord = new Coord(2, 4);
		Coord origin = new Coord(0, 0);
		
		int distance = coord.kingDistance(origin);
		int distanceReversed = origin.kingDistance(coord);
		
		assertEquals(4, distance);
		assertEquals(4, distanceReversed);
	}
	@Test
	void KingDistance_betweenTwoCoords_isTheirKingDistance() throws Exception {
		Coord coord1 = new Coord(3, 4);
		Coord coord2 = new Coord(2, 7);
		
		int distance = coord1.kingDistance(coord2);
		int distanceReversed = coord2.kingDistance(coord1);
		
		assertEquals(3, distance);
		assertEquals(3, distanceReversed);
	}

}
