package com.zalinius.darzalcommon.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GCDTest {

	
	//Note: this is part of the mathematical definition of GCD
	@Test
	void gcd_0and0_is0() throws Exception {
		int result = GCD.gcd(0, 0);
		
		assertEquals(0, result);
	}
	
	@Test
	void gcd_0andAPositiveInteger_isThePositiveInteger() throws Exception {
		int result = GCD.gcd(0, 4);
		
		assertEquals(4, result);
	}
	
	@Test
	void gcd_0andANegativeInteger_isTheAbsoluteValueOfTheNegativeInteger() throws Exception {
		int result = GCD.gcd(0, -4);
		
		assertEquals(4, result);
	}
	
	@Test
	void gcd_twoPositiveNumbers_isPositive() throws Exception {
		int result = GCD.gcd(2, 4);
		
		assertTrue(result > 0);
	}
	
	@Test
	void gcd_onePositiveNumberOneNegativeNumber_isPositive() throws Exception {
		int result1 = GCD.gcd(-2, 4);
		int result2 = GCD.gcd(2, -4);
		
		assertTrue(result1 > 0);
		assertTrue(result2 > 0);
	}
	
	@Test
	void gcd_twoNegativeNumbers_isPositive() throws Exception {
		int result = GCD.gcd(-2, -4);
				
		assertTrue(result > 0);
	}
	
	@Test
	void gcd_twoIdenticalNumbers_isThatNumber() throws Exception {
		int result = GCD.gcd(7, 7);
		
		assertEquals(7, result);
	}

	
	@Test
	void gcd_twoPrimeNumbers_is1() throws Exception {
		int result = GCD.gcd(7, 11);
				
		assertEquals(1, result);
	}
	
	@Test
	void gcd_twoRelativelyPrimeNumbers_is1() throws Exception {
		int result = GCD.gcd(8, 15);
				
		assertEquals(1, result);
	}

	@ParameterizedTest
	@CsvSource({"2,4,2", "12,15,3", "63,28,7"})
	void gcd_twoIntegers_returnsTheirGCD(int a, int b, int expectedResult) throws Exception {
		int result = GCD.gcd(a, b);
				
		assertEquals(expectedResult, result);
	}

	


}
