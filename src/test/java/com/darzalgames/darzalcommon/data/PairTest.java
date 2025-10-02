package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class PairTest {

	@Test
	void getters_returnValues() {
		Pair<Integer> pair = new Pair<>(0, 1);

		assertEquals(0, pair.e1());
		assertEquals(1, pair.e2());
	}

	@Test
	void toTuple_returnsEquivalentTuple() {
		Pair<Integer> pair = new Pair<>(0, 1);

		Tuple<Integer, Integer> tuple = pair.toTuple();

		assertEquals(new Tuple<>(0, 1), tuple);
		assertEquals(0, tuple.e());
		assertEquals(1, tuple.f());
	}

	@Test
	void toUPair_returnsUPairWithSameValues() {
		Pair<Integer> pair = new Pair<>(0, 1);

		UPair<Integer> uPair = pair.toUPair();

		assertEquals(new UPair<>(0, 1), uPair);
		assertEquals(new UPair<>(1, 0), uPair);
	}

	@Test
	void reverse_createsAReversedPair() {
		Pair<Integer> pair = new Pair<>(0, 1);

		Pair<Integer> reverse = pair.reverse();

		assertNotEquals(new Pair<>(0, 1), reverse);
		assertEquals(new Pair<>(1, 0), reverse);
	}

}
