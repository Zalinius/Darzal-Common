package com.zalinius.darzalcommon.random;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * We're deliberately not using the same seed here, as we're not testing the source of randomness,
 *  instead we're testing the method interfaces, using a barrage approach
 */
class RandomExtendedTest {

	private RandomExtended randomExtended;
	
	@BeforeEach
	void setup() {
		randomExtended = new RandomExtended();
	}
	
	@Test
	void constructor_usedWithLongSeed_doesNotChangeParentFunctions() throws Exception {
		long seed = 0;
		RandomExtended childClass = new RandomExtended(seed);
		Random parentClass = new Random(seed);
		
		int childResult = childClass.nextInt();
		int parentResult = parentClass.nextInt();
		
		assertEquals(parentResult, childResult);
	}
	
	@RepeatedTest(100)
	void nextInt_withValidBounds_returnsAValueBetweenBounds() throws Exception {
		int lowerBound = 4;
		int upperBound = 70;		
		
		int result = randomExtended.nextInt(lowerBound, upperBound);
		assertTrue(4 <= result);
		assertTrue(result < 70);
	}

	@RepeatedTest(100)
	void nextInt_withValidNegativeBounds_returnsANegativeValueBetweenBounds() throws Exception {
		int lowerBound = -200;
		int upperBound = -20;		
		
		int result = randomExtended.nextInt(lowerBound, upperBound);
		assertTrue(-200 <= result);
		assertTrue(result < -20);
	}

	@RepeatedTest(100)
	void nextInt_withNegativeLowerBoundAndPositiveUpperBound_returnsAValueBetweenBounds() throws Exception {
		int lowerBound = -200;
		int upperBound = 200;		
		
		int result = randomExtended.nextInt(lowerBound, upperBound);
		assertTrue(-200 <= result);
		assertTrue(result < 200);
	}
	
	@RepeatedTest(100)
	void nextRadian__returnsAValueBetween0And2Pi() throws Exception {
		double result = randomExtended.nextRadian();
		assertTrue(0.0 <= result);
		assertTrue(result < 2.0*Math.PI);
	}

	@RepeatedTest(100)
	void nextDegree__returnsAValueBetween0And360() throws Exception {
		double result = randomExtended.nextDegree();
		assertTrue(0.0 <= result);
		assertTrue(result < 360.0);
	}

	@RepeatedTest(100)
	void nextRadianF__returnsAValueBetween0And2Pi() throws Exception {
		float result = randomExtended.nextRadianF();
		assertTrue(0.0f <= result);
		assertTrue(result < 2.0f*(float)Math.PI);
	}

	@RepeatedTest(100)
	void nextDegreeF__returnsAValueBetween0And360() throws Exception {
		float result = randomExtended.nextDegreeF();
		assertTrue(0.0f <= result);
		assertTrue(result < 360.0f);
	}

	@Test
	void nextInt_withIdenticalBounds_throwsIllegalArgumentException() throws Exception {
		int lowerBound = 0;
		int upperBound = 0;		
		
		assertThrows(IllegalArgumentException.class, () -> randomExtended.nextInt(lowerBound, upperBound));
	}

	@Test
	void nextInt_withLowerBoundAboveUpperBound_throwsIllegalArgumentException() throws Exception {
		int lowerBound = 7;
		int upperBound = -4;		
		
		assertThrows(IllegalArgumentException.class, () -> randomExtended.nextInt(lowerBound, upperBound));
	}
	
	@RepeatedTest(100)
	void nextChance_withZeroNumerator_returnsAlwaysFalse() throws Exception {
		int numerator = 0;
		int denominator = 7;		
		
		boolean result = randomExtended.nextChance(numerator, denominator);
		assertFalse(result);
	}

	@RepeatedTest(100)
	void nextChance_withNumeratorEqualToDenominator_returnsAlwaysTrue() throws Exception {
		int numerator = 7;
		int denominator = 7;		
		
		boolean result = randomExtended.nextChance(numerator, denominator);
		assertTrue(result);
	}

	@Test
	void nextChance_withNegativeNumerator_throwsIllegalArgumentException() throws Exception {
		int numerator = -2;
		int denominator = 7;		
		
		assertThrows(IllegalArgumentException.class, () -> randomExtended.nextChance(numerator, denominator));
	}

	@Test
	void nextChance_withNegativeDenominator_throwsIllegalArgumentException() throws Exception {
		int numerator = 2;
		int denominator = -7;		
		
		assertThrows(IllegalArgumentException.class, () -> randomExtended.nextChance(numerator, denominator));
	}
	
	@Test
	void randomSubset_withSet_returnsAProperSubsetOfCorrectSize() throws Exception {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 3;
		
		Set<Integer> subset = randomExtended.getRandomSubset(set, subsetSize);

		assertEquals(3, subset.size());
		assertTrue(set.containsAll(subset));
		assertFalse(subset.containsAll(set));
	}
	
	@Test
	void randomSubset_withZeroSubsetSize_throwsIllegalArgumentException() throws Exception {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 0;
		
		assertThrows(IllegalArgumentException.class, () -> randomExtended.getRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_withSubsetSizeEqualToSetSize_throwsIllegalArgumentException() throws Exception {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 8;
		
		assertThrows(IllegalArgumentException.class, () -> randomExtended.getRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_withEmptySet_throwsIllegalArgumentException() throws Exception {
		Set<Integer> set = new HashSet<>(Arrays.asList());
		int subsetSize = 8;
		
		assertThrows(IllegalArgumentException.class, () -> randomExtended.getRandomSubset(set, subsetSize));
	}


}
