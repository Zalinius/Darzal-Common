package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TriPredicateTest {

	@Test
	void test_returnsCorrectResult() {
		TriPredicate<Integer, Integer, Integer> sumIs0 = (i, j, k) -> i + j + k == 0;

		assertTrue(sumIs0.test(0, 0, 0));
		assertTrue(sumIs0.test(-1, 0, 1));
		assertFalse(sumIs0.test(1, 0, 1));
	}

	@Test
	void negate_invertsTriPredicatesResults() {
		TriPredicate<Integer, Integer, Integer> sumIs0 = (i, j, k) -> i + j + k == 0;
		TriPredicate<Integer, Integer, Integer> sumIsNot0 = sumIs0.negate();

		assertFalse(sumIsNot0.test(0, 0, 0));
		assertFalse(sumIsNot0.test(-1, 0, 1));
		assertTrue(sumIsNot0.test(1, 0, 1));
	}

	@Test
	void and_createsConjunctionOfTriPredicates() {
		TriPredicate<Integer, Integer, Integer> sumIs0 = (i, j, k) -> i + j + k == 0;
		TriPredicate<Integer, Integer, Integer> allNonNegative = (i, j, k) -> i >= 0 && j >= 0 && k >= 0;

		TriPredicate<Integer, Integer, Integer> andTriPredicate = sumIs0.and(allNonNegative);

		assertTrue(andTriPredicate.test(0, 0, 0));
		assertFalse(andTriPredicate.test(-1, 0, 1));
		assertFalse(andTriPredicate.test(1, 0, 1));
	}

	@Test
	void or_createsDisjuntionOfTriPredicates() {
		TriPredicate<Integer, Integer, Integer> sumIs0 = (i, j, k) -> i + j + k == 0;
		TriPredicate<Integer, Integer, Integer> allNonNegative = (i, j, k) -> i >= 0 && j >= 0 && k >= 0;

		TriPredicate<Integer, Integer, Integer> orTriPredicate = sumIs0.or(allNonNegative);

		assertTrue(orTriPredicate.test(0, 0, 0));
		assertTrue(orTriPredicate.test(-1, 0, 1));
		assertTrue(orTriPredicate.test(1, 0, 1));
		assertFalse(orTriPredicate.test(-1, 0, -1));
	}

}
