package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordinateTest {

	@Test
	void accessors_bothValues_works() {
		Coordinate coordinate = new Coordinate(4, 2);

		int firstResult = coordinate.i();
		double secondResult = coordinate.j();

		assertEquals(4, firstResult);
		assertEquals(2, secondResult);
	}

	@Test
	void equals_withTwoIdenticalCoordinates_true() {
		Coordinate coordinate = new Coordinate(4, 2);

		assertEquals(coordinate, coordinate);
	}

	@Test
	void equals_withTwoEqualCoordinates_true() {
		Coordinate coordinate = new Coordinate(4, 2);

		assertEquals(new Coordinate(4, 2), coordinate);
	}

	@Test
	void equals_withTwoCoordinatesWithDifferentData_false() {
		Coordinate coordinate = new Coordinate(4, 2);

		assertNotEquals(new Coordinate(7, 9), coordinate);
	}

	@Test
	void noArgConstructor_createsAnOriginCoordinate() {
		Coordinate coordinate = new Coordinate();

		assertEquals(new Coordinate(0, 0), coordinate);
	}

	@Test
	void tupleConstructor_createsCoordinate() {
		Tuple<Integer, Integer> tuple = new Tuple<>(2, 4);
		Coordinate coordinate = new Coordinate(tuple);

		assertEquals(new Coordinate(2, 4), coordinate);
	}

	@Test
	void tupleConstructor_tupleWithNullEntries_throwsNullPointerException() {
		Tuple<Integer, Integer> tuple = new Tuple<>(null, null);

		assertThrows(NullPointerException.class, () -> new Coordinate(tuple));

	}

	@Test
	void pairConstructor_createsCoordinate() {
		Pair<Integer> pair = new Pair<>(2, 4);
		Coordinate coordinate = new Coordinate(pair);

		assertEquals(new Coordinate(2, 4), coordinate);
	}

	@Test
	void toPair_createsIntegerPairFromCoordinate() {
		Coordinate coordinate = new Coordinate(1, 2);

		Pair<Integer> pair = coordinate.toPair();

		assertEquals(1, pair.e1());
		assertEquals(2, pair.e2());
	}

	@Test
	void toTuple_createsIntegerTupleFromCoordinate() {
		Coordinate coordinate = new Coordinate(1, 2);

		Tuple<Integer, Integer> tuple = coordinate.toTuple();

		assertEquals(1, tuple.e());
		assertEquals(2, tuple.f());
	}

	@Test
	void toString_doesNotThrow() {
		Coordinate coordinate = new Coordinate();

		assertDoesNotThrow(() -> coordinate.toString());
	}

	@Test
	void taxiDistance_betweenCoordinateAndItself_is0() {
		Coordinate coordinate = new Coordinate(2, 4);

		int distance = coordinate.taxiDistance(coordinate);

		assertEquals(0, distance);
	}

	@Test
	void taxiDistance_betweenCoordinateAndOrigin_isSumOfCoordinates() {
		Coordinate coordinate = new Coordinate(2, 4);
		Coordinate origin = new Coordinate(0, 0);

		int distance = coordinate.taxiDistance(origin);
		int distanceReversed = origin.taxiDistance(coordinate);

		assertEquals(6, distance);
		assertEquals(6, distanceReversed);
	}

	@Test
	void taxiDistance_betweenTwoCoordinates_isTheirTaxiDistance() {
		Coordinate coordinate1 = new Coordinate(3, 4);
		Coordinate coordinate2 = new Coordinate(2, 7);

		int distance = coordinate1.taxiDistance(coordinate2);
		int distanceReversed = coordinate2.taxiDistance(coordinate1);

		assertEquals(4, distance);
		assertEquals(4, distanceReversed);
	}

	@Test
	void kingDistance_betweenCoordinateAndItself_is0() {
		Coordinate coordinate = new Coordinate(2, 4);

		int distance = coordinate.kingDistance(coordinate);

		assertEquals(0, distance);
	}

	@Test
	void kingDistance_betweenCoordinateAndOrigin_isLargestOfCoordinates() {
		Coordinate coordinate = new Coordinate(2, 4);
		Coordinate origin = new Coordinate(0, 0);

		int distance = coordinate.kingDistance(origin);
		int distanceReversed = origin.kingDistance(coordinate);

		assertEquals(4, distance);
		assertEquals(4, distanceReversed);
	}

	@Test
	void KingDistance_betweenTwoCoordinates_isTheirKingDistance() {
		Coordinate coordinate1 = new Coordinate(3, 4);
		Coordinate coordinate2 = new Coordinate(2, 7);

		int distance = coordinate1.kingDistance(coordinate2);
		int distanceReversed = coordinate2.kingDistance(coordinate1);

		assertEquals(3, distance);
		assertEquals(3, distanceReversed);
	}

}
