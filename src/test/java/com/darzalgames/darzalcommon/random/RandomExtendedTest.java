package com.darzalgames.darzalcommon.random;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.functional.Do;

/**
 * We're deliberately not using the same seed here, as we're not testing the source of randomness,
 * instead we're testing the method interfaces, using a barrage approach
 */
class RandomExtendedTest {

	private RandomExtended randomExtended;
	private static final int RUNS = 100;

	@BeforeEach
	void setup() {
		randomExtended = new RandomExtended();
	}

	@Test
	void constructorWithSeed_behavesLikeBasicRandomObjectWithSameSeed() {
		RandomExtended randomExtended = new RandomExtended(0);
		Random random = new Random(0);

		assertEquals(random.nextInt(), randomExtended.nextInt());
		assertEquals(random.nextFloat(), randomExtended.nextFloat());
		assertEquals(random.nextBoolean(), randomExtended.nextCoinFlip());
	}

	@Test
	void nextRadian__returnsAValueBetween0And2Pi() {
		Do.xTimes(RUNS, () -> {
			double result = randomExtended.nextRadian();
			assertTrue(0.0 <= result);
			assertTrue(result < 2.0 * Math.PI);
		});
	}

	@Test
	void nextDegree__returnsAValueBetween0And360() {
		Do.xTimes(RUNS, () -> {
			double result = randomExtended.nextDegree();
			assertTrue(0.0 <= result);
			assertTrue(result < 360.0);
		});
	}

	@Test
	void nextRadianF__returnsAValueBetween0And2Pi() {
		Do.xTimes(RUNS, () -> {
			float result = randomExtended.nextRadianF();
			assertTrue(0.0f <= result);
			assertTrue(result < 2.0f * (float) Math.PI);
		});
	}

	@Test
	void nextDegreeF__returnsAValueBetween0And360() {
		Do.xTimes(RUNS, () -> {
			float result = randomExtended.nextDegreeF();
			assertTrue(0.0f <= result);
			assertTrue(result < 360.0f);
		});
	}

	@Test
	void nextChance_withZeroNumerator_returnsAlwaysFalse() {
		int numerator = 0;
		int denominator = 7;

		boolean result = randomExtended.nextChance(numerator, denominator);
		assertFalse(result);
	}

	@Test
	void nextChance_withNumeratorEqualToDenominator_returnsAlwaysTrue() {
		int numerator = 7;
		int denominator = 7;

		Do.xTimes(RUNS, () -> {
			boolean result = randomExtended.nextChance(numerator, denominator);
			assertTrue(result);
		});
	}

	@Test
	void nextChance_withNegativeNumerator_throwsIllegalArgumentException() {
		int numerator = -2;
		int denominator = 7;

		assertThrows(IllegalArgumentException.class, () -> randomExtended.nextChance(numerator, denominator));
	}

	@Test
	void nextChance_withNegativeDenominator_throwsIllegalArgumentException() {
		int numerator = 2;
		int denominator = -7;

		assertThrows(IllegalArgumentException.class, () -> randomExtended.nextChance(numerator, denominator));
	}

	@Test
	void getRandomElement_withEmptyCollection_throwsIllegalArgumentException() {
		Collection<Integer> empty = List.of();
		assertThrows(IllegalArgumentException.class, () -> randomExtended.getRandomElement(empty));
	}

	@Test
	void getRandomElement_returnsElementFromCollection() {
		Collection<Integer> collection = List.of(2, 7, 29, -5);
		Do.xTimes(RUNS, () -> {
			int result = randomExtended.getRandomElement(collection);
			assertTrue(collection.contains(result));
		});
	}

	@Test
	void randomSubset_withSet_returnsAProperSubsetOfCorrectSize() {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 3;

		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);

		assertEquals(3, subset.size());
		assertTrue(set.containsAll(subset));
		assertFalse(subset.containsAll(set));
	}

	@Test
	void randomSubset_withNegativeSubsetSize_throwsIllegalArgumentException() {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = -1;

		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_withSubsetSizeGreatherThanSetSize_throwsIllegalArgumentException() {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 9;

		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_with0SubsetSize_returnsEmptySet() {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 0;

		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);

		assertEquals(0, subset.size());
	}

	@Test
	void randomSubset_withSubsetSizeEqualToSetSize_returnsOriginalSet() {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 8;

		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);

		assertEquals(8, subset.size());
		assertEquals(set, subset);
	}

	@Test
	void randomSubset_withEmptySetAndNonZeroSubsetSize_throwsIllegalArgumentException() {
		Set<Integer> set = new HashSet<>(Arrays.asList());
		int subsetSize = 8;

		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_withEmptySetAndSubsetSize0_returnsEmptySet() {
		Set<Integer> set = new HashSet<>(Arrays.asList());
		int subsetSize = 0;

		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);

		assertEquals(0, subset.size());
	}

}
