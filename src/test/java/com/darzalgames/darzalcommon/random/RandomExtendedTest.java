package com.darzalgames.darzalcommon.random;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.functional.Do;

/**
 * We're deliberately not using the same seed here, as we're not testing the source of randomness,
 *  instead we're testing the method interfaces, using a barrage approach
 */
class RandomExtendedTest {

	private RandomExtended randomExtended;
	private static final int RUNS = 100;
	
	@BeforeEach
	void setup() {
		randomExtended = new RandomExtended();
	}
	
	void nextRadian__returnsAValueBetween0And2Pi() throws Exception {
		Do.xTimes(RUNS, () -> {
			double result = randomExtended.nextRadian();
			assertTrue(0.0 <= result);
			assertTrue(result < 2.0*Math.PI);			
		});
	}

	void nextDegree__returnsAValueBetween0And360() throws Exception {
		Do.xTimes(RUNS, () -> {
			double result = randomExtended.nextDegree();
			assertTrue(0.0 <= result);
			assertTrue(result < 360.0);			
		});
	}

	void nextRadianF__returnsAValueBetween0And2Pi() throws Exception {
		Do.xTimes(RUNS, () -> {
			float result = randomExtended.nextRadianF();
			assertTrue(0.0f <= result);
			assertTrue(result < 2.0f*(float)Math.PI);			
		});
	}

	void nextDegreeF__returnsAValueBetween0And360() throws Exception {
		Do.xTimes(RUNS, () -> {
			float result = randomExtended.nextDegreeF();
			assertTrue(0.0f <= result);
			assertTrue(result < 360.0f);			
		});
	}

	void nextChance_withZeroNumerator_returnsAlwaysFalse() throws Exception {
		int numerator = 0;
		int denominator = 7;		
		
		boolean result = randomExtended.nextChance(numerator, denominator);
		assertFalse(result);
	}

	void nextChance_withNumeratorEqualToDenominator_returnsAlwaysTrue() throws Exception {
		int numerator = 7;
		int denominator = 7;		
		
		Do.xTimes(RUNS, () -> {
			boolean result = randomExtended.nextChance(numerator, denominator);
			assertTrue(result);			
		});
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
		
		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);

		assertEquals(3, subset.size());
		assertTrue(set.containsAll(subset));
		assertFalse(subset.containsAll(set));
	}
	
	@Test
	void randomSubset_withNegativeSubsetSize_throwsIllegalArgumentException() throws Exception {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = -1;
		
		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_withSubsetSizeGreatherThanSetSize_throwsIllegalArgumentException() throws Exception {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 9;
		
		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_with0SubsetSize_returnsEmptySet() throws Exception {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 0;
		
		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);
		
		assertEquals(0, subset.size());
	}

	@Test
	void randomSubset_withSubsetSizeEqualToSetSize_returnsOriginalSet() throws Exception {
		Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		int subsetSize = 8;
		
		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);
		
		assertEquals(8, subset.size());
		assertEquals(set, subset);
	}

	@Test
	void randomSubset_withEmptySetAndNonZeroSubsetSize_throwsIllegalArgumentException() throws Exception {
		Set<Integer> set = new HashSet<>(Arrays.asList());
		int subsetSize = 8;
		
		assertThrows(IllegalArgumentException.class, () -> randomExtended.getNextRandomSubset(set, subsetSize));
	}

	@Test
	void randomSubset_withEmptySetAndSubsetSize0_returnsEmptySet() throws Exception {
		Set<Integer> set = new HashSet<>(Arrays.asList());
		int subsetSize = 0;
		
		Set<Integer> subset = randomExtended.getNextRandomSubset(set, subsetSize);
		
		assertEquals(0, subset.size());
	}


}
