package com.zalinius.darzalcommon.random;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * We're deliberately not using the same seed here, as we're not testing the source of randomness,
 *  instead we're testing the method interfaces, using a barrage approach
 */
public class RandomExtendedTest {

	private RandomExtended randomExtended;
	
	@BeforeEach
	public void setup() {
		randomExtended = new RandomExtended();
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
}
