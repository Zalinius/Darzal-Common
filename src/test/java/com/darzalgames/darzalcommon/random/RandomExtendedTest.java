package com.darzalgames.darzalcommon.random;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.functional.Do;
import com.darzalgames.darzalcommon.math.Fraction;

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
	void getRandomSeed_withMultipleInvocations_returnsDifferentSeeds() {
		List<Long> seeds = new ArrayList<>();

		Do.xTimes(100, () -> seeds.add(RandomExtended.getRandomSeed()));

		assertEquals(100, seeds.stream().distinct().count());
	}

	@Test
	void constructorWithSeed_behavesLikeBasicRandomObjectWithSameSeed() {
		RandomExtended randomExtendedSeeded = new RandomExtended(0);
		Random random = new Random(0);

		assertEquals(random.nextInt(), randomExtendedSeeded.nextInt());
		assertEquals(random.nextFloat(), randomExtendedSeeded.nextFloat());
		assertEquals(random.nextBoolean(), randomExtendedSeeded.nextCoinFlip());
	}

	@Test
	void constructorWithSameSeedAsAnotherRandomExtended_behavesIdenticallyToThatRandomExtended() {
		RandomExtended randomExtendedOriginal = new RandomExtended();
		RandomExtended randomExtendedWithSameSeed = new RandomExtended(randomExtendedOriginal.getSeed());

		assertEquals(randomExtendedOriginal.nextInt(), randomExtendedWithSameSeed.nextInt());
		assertEquals(randomExtendedOriginal.nextFloat(), randomExtendedWithSameSeed.nextFloat());
		assertEquals(randomExtendedOriginal.nextBoolean(), randomExtendedWithSameSeed.nextBoolean());
	}

	@Test
	void getSeed_fromSeededConstructor_returnsThatSeed() {
		RandomExtended random = new RandomExtended(5);

		assertEquals(5, random.getSeed());
	}

	@Test
	void getSeed_fromDifferentRandomObjects_returnsDifferentSeeds() {
		RandomExtended random1 = new RandomExtended();
		RandomExtended random2 = new RandomExtended();

		assertNotEquals(random1.getSeed(), random2.getSeed());
	}

	@Test
	void getRandomCallCount_onNewInstance_returns0() {
		RandomExtended random = new RandomExtended();

		assertEquals(0, random.getRandomCallCount());
	}

	@Test
	void getRandomCallCount_onNewSeededInstance_returns0() {
		RandomExtended random = new RandomExtended(27);

		assertEquals(0, random.getRandomCallCount());
	}

	@Test
	void getRandomCallCount_afterCallingNextInt5Times_returns5() {
		RandomExtended random = new RandomExtended();

		random.nextInt();
		random.nextInt();
		random.nextInt();
		random.nextInt();
		random.nextInt();

		assertEquals(5, random.getRandomCallCount());
	}

	@Test
	void getRandomCallCount_after4SingleRandomnessCalls_returns4() {
		RandomExtended random = new RandomExtended();

		random.nextInt();
		random.nextCoinFlip();
		random.nextFloat();
		random.nextChance(1, 7);

		assertEquals(4, random.getRandomCallCount());
	}

	@Test
	void getRandomCallCount_after2DoubleEntropyRandomnessCalls_returns4() {
		RandomExtended random = new RandomExtended();

		random.nextLong();
		random.nextDouble();

		assertEquals(4, random.getRandomCallCount());
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
	void nextChance_withZeroFraction_returnsAlwaysFalse() {
		Fraction odds = new Fraction(0, 7);

		boolean result = randomExtended.nextChance(odds);
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
	void nextChance_withNegativePercentage_throwsIllegalArgumentException() {
		float odds = -0.5f;

		assertThrows(IllegalArgumentException.class, () -> randomExtended.nextChance(odds));
	}

	@Test
	void nextChance_with0Percentage_returnsFalse() {
		float odds = 0f;

		boolean result = randomExtended.nextChance(odds);
		assertFalse(result);
	}

	@Test
	void nextChance_with100Percentage_returnsTrue() {
		float odds = 1f;

		boolean result = randomExtended.nextChance(odds);
		assertTrue(result);
	}

	@Test
	void nextChance_withGreaterThan100PercentChance_returnsTrue() {
		float odds = 2.5f;

		boolean result = randomExtended.nextChance(odds);
		assertTrue(result);
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
		Set<Integer> set = new LinkedHashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 3;

		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);

		assertEquals(3, subset.size());
		assertTrue(set.containsAll(subset));
		assertFalse(subset.containsAll(set));
	}

	@Test
	void randomSubset_withNegativeSubsetSize_throwsIllegalArgumentException() {
		Set<Integer> set = new LinkedHashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = -1;

		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_withSubsetSizeGreatherThanSetSize_throwsIllegalArgumentException() {
		Set<Integer> set = new LinkedHashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 9;

		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_with0SubsetSize_returnsEmptySet() {
		Set<Integer> set = new LinkedHashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 0;

		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);

		assertEquals(0, subset.size());
	}

	@Test
	void randomSubset_withSubsetSizeEqualToSetSize_returnsOriginalSet() {
		Set<Integer> set = new LinkedHashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 8;

		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);

		assertEquals(8, subset.size());
		assertEquals(set, subset);
	}

	@Test
	void randomSubset_withEmptySetAndNonZeroSubsetSize_throwsIllegalArgumentException() {
		Set<Integer> set = new LinkedHashSet<>(Arrays.asList());
		int subsetSize = 8;

		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_withEmptySetAndSubsetSize0_returnsEmptySet() {
		Set<Integer> set = new LinkedHashSet<>(Arrays.asList());
		int subsetSize = 0;

		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);

		assertEquals(0, subset.size());
	}

	@Test
	void getNextChunkedAmountList_with1ChunkAndTotal5_returnsAChunkOf5() {
		List<Integer> chunk = randomExtended.getNextChunkedAmountList(5, 1);

		assertEquals(1, chunk.size());
		assertEquals(5, chunk.getFirst());
	}

	@Test
	void getNextChunkedAmountList_with1ChunkAndTotal0_returnsAChunkOf0() {
		List<Integer> chunk = randomExtended.getNextChunkedAmountList(0, 1);

		assertEquals(1, chunk.size());
		assertEquals(0, chunk.getFirst());
	}

	@ParameterizedTest
	@CsvSource({ "10, 5", "23, 67", "200, 7", "28, 391", "3, 70" })
	void getNextChunkedAmountList_withVariousInputs_sumsToTotalWithDesiredNumberOfChunks(int total, int numberOfChunks) {
		List<Integer> chunks = randomExtended.getNextChunkedAmountList(total, numberOfChunks);

		assertEquals(total, chunks.stream().reduce(0, Integer::sum));
		assertEquals(numberOfChunks, chunks.size());
	}

	@Test
	void getNextChunkedAmountList_withNegativeTotal_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextChunkedAmountList(-5, 10));
	}

	@Test
	void getNextChunkedAmountList_withZeroChunks_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextChunkedAmountList(5, 0));
	}

}
