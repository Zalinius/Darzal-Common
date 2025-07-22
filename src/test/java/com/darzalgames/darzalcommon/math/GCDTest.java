package com.darzalgames.darzalcommon.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GCDTest {


	//Note: this is part of the mathematical definition of GCD
	@Test
	void gcd_0and0_is0() {
		int result = GCD.gcd(0, 0);

		assertEquals(0, result);
	}

	@Test
	void gcd_0andAPositiveInteger_isThePositiveInteger() {
		int result = GCD.gcd(0, 4);

		assertEquals(4, result);
	}

	@Test
	void gcd_0andANegativeInteger_isTheAbsoluteValueOfTheNegativeInteger() {
		int result = GCD.gcd(0, -4);

		assertEquals(4, result);
	}

	@Test
	void gcd_twoPositiveNumbers_isPositive() {
		int result = GCD.gcd(2, 4);

		assertTrue(result > 0);
	}

	@Test
	void gcd_onePositiveNumberOneNegativeNumber_isPositive() {
		int result1 = GCD.gcd(-2, 4);
		int result2 = GCD.gcd(2, -4);

		assertTrue(result1 > 0);
		assertTrue(result2 > 0);
	}

	@Test
	void gcd_twoNegativeNumbers_isPositive() {
		int result = GCD.gcd(-2, -4);

		assertTrue(result > 0);
	}

	@Test
	void gcd_twoIdenticalNumbers_isThatNumber() {
		int result = GCD.gcd(7, 7);

		assertEquals(7, result);
	}


	@Test
	void gcd_twoPrimeNumbers_is1() {
		int result = GCD.gcd(7, 11);

		assertEquals(1, result);
	}

	@Test
	void gcd_twoRelativelyPrimeNumbers_is1() {
		int result = GCD.gcd(8, 15);

		assertEquals(1, result);
	}

	@ParameterizedTest
	@CsvSource({"2,4,2", "12,15,3", "63,28,7"})
	void gcd_twoIntegers_returnsTheirGCD(int a, int b, int expectedResult) {
		int result = GCD.gcd(a, b);

		assertEquals(expectedResult, result);
	}




}
