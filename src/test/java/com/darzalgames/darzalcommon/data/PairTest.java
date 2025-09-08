package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
