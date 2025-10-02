package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class UPairTest {

	@Test
	void getOther_returnsOtherHalfOfPair() {
		UPair<Integer> uPair = new UPair<>(1, 2);

		assertEquals(1, uPair.getOther(2));
		assertEquals(2, uPair.getOther(1));
	}

	@Test
	void getOther_whenBothValuesEqual_returnsOtherHalfOfPair() {
		int v1 = 5;
		int v2 = 5;
		UPair<Integer> uPair = new UPair<>(v1, v2);

		assertEquals(5, uPair.getOther(v1));
		assertEquals(5, uPair.getOther(v2));
	}

	@Test
	void getOther_whenValueNotPresent_throwsIllegalArgumentException() {
		UPair<Integer> uPair = new UPair<>(1, 2);

		assertThrows(IllegalArgumentException.class, () -> uPair.getOther(5));
	}

	@Test
	void values_returnsSetOfUPairsValues() {
		UPair<Integer> uPair1 = new UPair<>(1, 2);
		UPair<Integer> uPair2 = new UPair<>(5, 5);

		Set<Integer> set1 = uPair1.values();
		Set<Integer> set2 = uPair2.values();

		assertEquals(2, set1.size());
		assertTrue(set1.contains(1));
		assertTrue(set1.contains(2));
		assertEquals(1, set2.size());
		assertTrue(set2.contains(5));
	}

	@Test
	void toPair_returnEquivalentPairInUnspecifiedOrder() {
		UPair<Integer> uPair = new UPair<>(1, 2);

		Pair<Integer> pair = uPair.toPair();

		assertTrue(pair.equals(new Pair<>(1, 2)) || pair.equals(new Pair<>(2, 1)));
	}

	@Test
	void toPair_onUPairWithIdenticalValues_returnsPair() {
		UPair<Integer> uPair = new UPair<>(5, 5);

		Pair<Integer> pair = uPair.toPair();

		assertEquals(new Pair<>(5, 5), pair);
	}

	@Test
	void toTuple_returnEquivalentTupleInUnspecifiedOrder() {
		UPair<Integer> uPair = new UPair<>(1, 2);

		Tuple<Integer, Integer> tuple = uPair.toTuple();

		assertTrue(tuple.equals(new Tuple<>(1, 2)) || tuple.equals(new Tuple<>(2, 1)));
	}

	@Test
	void toTuple_onUPairWithIdenticalValues_returnsTuple() {
		UPair<Integer> uPair = new UPair<>(5, 5);

		Tuple<Integer, Integer> tuple = uPair.toTuple();

		assertEquals(new Tuple<>(5, 5), tuple);
	}

	@Test
	void equals_onTwoIdenticalUPairs_true() {
		UPair<Integer> uPair1 = new UPair<>(1, 2);
		UPair<Integer> uPair2 = new UPair<>(1, 2);

		assertEquals(uPair1, uPair1);
		assertEquals(uPair1, uPair2);
		assertEquals(uPair2, uPair1);
		assertEquals(uPair2, uPair2);
	}

	@Test
	void equals_onOppositeUPairs_true() {
		UPair<Integer> uPair1 = new UPair<>(1, 2);
		UPair<Integer> uPair2 = new UPair<>(2, 1);

		assertEquals(uPair1, uPair2);
		assertEquals(uPair2, uPair1);
	}

	@Test
	void equals_onDifferentUPairs_false() {
		UPair<Integer> uPair1 = new UPair<>(1, 2);
		UPair<Integer> uPair2 = new UPair<>(5, 5);

		assertNotEquals(uPair1, uPair2);
		assertNotEquals(uPair2, uPair1);
	}

	@Test
	void equals_onUPairWithIdenticalValues_true() {
		UPair<Integer> uPair1 = new UPair<>(5, 5);
		UPair<Integer> uPair2 = new UPair<>(5, 5);

		assertEquals(uPair1, uPair1);
		assertEquals(uPair1, uPair2);
		assertEquals(uPair2, uPair1);
		assertEquals(uPair2, uPair2);
	}

	@Test
	void equals_onDifferentClass_returnsFalse() {
		UPair<Integer> uPair = new UPair<>(1, 2);
		Pair<Integer> pair = new Pair(1, 2);

		assertNotEquals(uPair, pair);
	}

	@Test
	void equals_onNullValue_returnsFalse() {
		UPair<Integer> uPair = new UPair<>(1, 2);

		assertNotEquals(uPair, null);
	}

	@Test
	void hashCode_onTwoIdenticalUPairs_equal() {
		UPair<Integer> uPair1 = new UPair<>(1, 2);
		UPair<Integer> uPair2 = new UPair<>(1, 2);

		assertEquals(uPair1.hashCode(), uPair1.hashCode());
		assertEquals(uPair1.hashCode(), uPair2.hashCode());
		assertEquals(uPair2.hashCode(), uPair1.hashCode());
		assertEquals(uPair2.hashCode(), uPair2.hashCode());
	}

	@Test
	void hashCode_onOppositeUPairs_equal() {
		UPair<Integer> uPair1 = new UPair<>(1, 2);
		UPair<Integer> uPair2 = new UPair<>(2, 1);

		assertEquals(uPair1.hashCode(), uPair2.hashCode());
		assertEquals(uPair2.hashCode(), uPair1.hashCode());
	}

	@Test
	void hashCode_onUPairWithIdenticalValues_equal() {
		UPair<Integer> uPair1 = new UPair<>(5, 5);
		UPair<Integer> uPair2 = new UPair<>(5, 5);

		assertEquals(uPair1.hashCode(), uPair1.hashCode());
		assertEquals(uPair1.hashCode(), uPair2.hashCode());
		assertEquals(uPair2.hashCode(), uPair1.hashCode());
		assertEquals(uPair2.hashCode(), uPair2.hashCode());
	}

	@Test
	void toString_containsElementsOfUPair() {
		UPair<Integer> uPair = new UPair<>(1, 2);

		String string = uPair.toString();

		assertTrue(string.contains("1"));
		assertTrue(string.contains("2"));
	}

	@Test
	void toString_onUPairWithIdenticalValues_containsTheValueTwice() {
		UPair<Integer> uPair = new UPair<>(5, 5);

		String string = uPair.toString();
		String[] halves = string.split(",");
		String firstHalf = halves[0];
		String secondHalf = halves[1];

		assertTrue(firstHalf.contains("5"));
		assertTrue(secondHalf.contains("5"));
	}

}
