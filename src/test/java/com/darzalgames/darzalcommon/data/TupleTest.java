package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class TupleTest {

	@Test
	void accessors_bothValues_works() {
		Tuple<Integer, Double> tuple = new Tuple<>(4, 2.7);

		int firstResult = tuple.e;
		double secondResult = tuple.f;
		int firstGetResult = tuple.getE();
		double secondGetResult = tuple.getF();


		assertEquals(4, firstResult);
		assertEquals(2.7, secondResult);
		assertEquals(4, firstGetResult);
		assertEquals(2.7, secondGetResult);
	}

	@Test
	void equals_withTwoIdenticalTuples_true() {
		Tuple<Integer, Double> tuple = new Tuple<>(4, 2.7);

		assertEquals(tuple, tuple);
	}

	@Test
	void equals_withTwoEqualTuples_true() {
		Tuple<Integer, Double> tuple = new Tuple<>(4, 2.7);

		assertEquals(new Tuple<>(4, 2.7), tuple);
	}

	@Test
	void equals_withTwoTuplesWithDifferentData_false() {
		Tuple<Integer, Double> tuple = new Tuple<>(4, 2.7);

		assertNotEquals(new Tuple<>(6, 2.9), tuple);
	}

	@Test
	void equals_withTwoTuplesWithDifferentGenericTypes_false() {
		Tuple<Integer, Double> tuple = new Tuple<>(4, 2.7);

		assertNotEquals(new Tuple<>(4L, 2.7), tuple);
	}


	@Test
	void toString_doesNotThrow() {
		Tuple<Integer, Double> tuple1 = new Tuple<>(4, 2.7);
		Tuple<Integer, Double> tuple2 = new Tuple<>(null, null);

		assertDoesNotThrow(() -> tuple1.toString());
		assertDoesNotThrow(() -> tuple2.toString());
	}

}

