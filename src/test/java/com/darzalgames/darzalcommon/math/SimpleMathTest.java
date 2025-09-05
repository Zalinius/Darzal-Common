package com.darzalgames.darzalcommon.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SimpleMathTest {

	@Test
	void isEven_onEvenInt_returnsTrue(){
		int input = 6;

		boolean result = SimpleMath.isEven(input);
		assertTrue(result);
	}

	@Test
	void isEven_onOddInt_returnsFalse(){
		int input = 5;

		boolean result = SimpleMath.isEven(input);

		assertFalse(result);
	}

	@Test
	void isEven_onZero_returnsTrue(){
		int input = 0;

		boolean result = SimpleMath.isEven(input);

		assertTrue(result);
	}

	@Test
	void isEven_onNegativeEvenInt_returnsTrue(){
		int input = -4;

		boolean result = SimpleMath.isEven(input);

		assertTrue(result);
	}

	@Test
	void isEven_onNegativeOddInt_returnsFalse(){
		int input = -3;

		boolean result = SimpleMath.isEven(input);

		assertFalse(result);
	}


	//isOdd int
	@Test
	void isOdd_onEvenInt_returnsFalse(){
		int input = 6;

		boolean result = SimpleMath.isOdd(input);

		assertFalse(result);
	}

	@Test
	void isOdd_onOddInt_returnsTrue(){
		int input = 5;

		boolean result = SimpleMath.isOdd(input);

		assertTrue(result);
	}

	@Test
	void isOdd_onZero_returnsFalse(){
		int input = 0;

		boolean result = SimpleMath.isOdd(input);

		assertFalse(result);
	}

	@Test
	void isOdd_onNegativeEvenInt_returnsFalse(){
		int input = -4;

		boolean result = SimpleMath.isOdd(input);

		assertFalse(result);
	}

	@Test
	void isOdd_onNegativeOddInt_returnsTrue(){
		int input = -3;

		boolean result = SimpleMath.isOdd(input);

		assertTrue(result);
	}

	@Test
	void isBetween_onLeftBound_returnsTrue(){
		int left = 0;
		int right = 5;
		int input = 0;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertTrue(result);
	}

	@Test
	void isBetween_onRightBound_returnsTrue(){
		int left = 0;
		int right = 5;
		int input = 5;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertTrue(result);
	}

	@Test
	void isBetween_withinBounds_returnsTrue(){
		int left = 0;
		int right = 5;
		int input = 2;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertTrue(result);
	}

	@Test
	void isBetween_outsideLeftOfBounds_returnsFalse(){
		int left = 0;
		int right = 5;
		int input = -3;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertFalse(result);
	}

	@Test
	void isBetween_outsideRightOfBounds_returnsFalse(){
		int left = 0;
		int right = 5;
		int input = 8;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertFalse(result);
	}

	@Test
	void isBetween_withinReversedBounds_returnsTrue(){
		int left = 5;
		int right = 0;
		int input = 2;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertTrue(result);
	}

	@Test
	void isBetween_outsideReversedBounds_returnsFalse(){
		int left = 5;
		int right = 0;
		int input = -3;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertFalse(result);
	}

	@Test
	void isMultiple_onMultipleOddInts_returnsTrue(){
		int value = 9;
		int base = 3;

		boolean result = SimpleMath.isMultiple(value, base);

		assertTrue(result);
	}

	@Test
	void isMultiple_onMultipleEvenInts_returnsTrue(){
		int value = 8;
		int base = 4;

		boolean result = SimpleMath.isMultiple(value, base);

		assertTrue(result);
	}

	@Test
	void isMultiple_onNonMultipleOddInts_returnsFalse(){
		int value = 11;
		int base = 3;

		boolean result = SimpleMath.isMultiple(value, base);

		assertFalse(result);
	}

	@Test
	void isMultiple_onNonMultipleEvenInts_returnsFalse(){
		int value = 6;
		int base = 4;

		boolean result = SimpleMath.isMultiple(value, base);

		assertFalse(result);
	}

	//Note: this is part of the mathematical definition of GCD
	@Test
	void gcd_0and0_is0() {
		int result = SimpleMath.greatestCommonDivisor(0, 0);

		assertEquals(0, result);
	}

	@Test
	void gcd_0andAPositiveInteger_isThePositiveInteger() {
		int result = SimpleMath.greatestCommonDivisor(0, 4);

		assertEquals(4, result);
	}

	@Test
	void gcd_0andANegativeInteger_isTheAbsoluteValueOfTheNegativeInteger() {
		int result = SimpleMath.greatestCommonDivisor(0, -4);

		assertEquals(4, result);
	}

	@Test
	void gcd_twoPositiveNumbers_isPositive() {
		int result = SimpleMath.greatestCommonDivisor(2, 4);

		assertTrue(result > 0);
	}

	@Test
	void gcd_onePositiveNumberOneNegativeNumber_isPositive() {
		int result1 = SimpleMath.greatestCommonDivisor(-2, 4);
		int result2 = SimpleMath.greatestCommonDivisor(2, -4);

		assertTrue(result1 > 0);
		assertTrue(result2 > 0);
	}

	@Test
	void gcd_twoNegativeNumbers_isPositive() {
		int result = SimpleMath.greatestCommonDivisor(-2, -4);

		assertTrue(result > 0);
	}

	@Test
	void gcd_twoIdenticalNumbers_isThatNumber() {
		int result = SimpleMath.greatestCommonDivisor(7, 7);

		assertEquals(7, result);
	}


	@Test
	void gcd_twoPrimeNumbers_is1() {
		int result = SimpleMath.greatestCommonDivisor(7, 11);

		assertEquals(1, result);
	}

	@Test
	void gcd_twoRelativelyPrimeNumbers_is1() {
		int result = SimpleMath.greatestCommonDivisor(8, 15);

		assertEquals(1, result);
	}

	@ParameterizedTest
	@CsvSource({"2,4,2", "12,15,3", "63,28,7"})
	void gcd_twoIntegers_returnsTheirGCD(int a, int b, int expectedResult) {
		int result = SimpleMath.greatestCommonDivisor(a, b);

		assertEquals(expectedResult, result);
	}

	@Test
	void canParseToInteger() {
		assertTrue(SimpleMath.canParseToInteger("0"));
		assertTrue(SimpleMath.canParseToInteger("001"));
		assertTrue(SimpleMath.canParseToInteger("-287"));
		assertTrue(SimpleMath.canParseToInteger("2888"));
		assertTrue(SimpleMath.canParseToInteger("2147483647"));

		assertFalse(SimpleMath.canParseToInteger(null));
		assertFalse(SimpleMath.canParseToInteger(""));
		assertFalse(SimpleMath.canParseToInteger("ten"));
		assertFalse(SimpleMath.canParseToInteger("-28.3"));
		assertFalse(SimpleMath.canParseToInteger("0.0"));
		assertFalse(SimpleMath.canParseToInteger("2147483648"));
	}

	@Test
	void canParseToFloat() {
		assertTrue(SimpleMath.canParseToFloat("0"));
		assertTrue(SimpleMath.canParseToFloat("0.0"));
		assertTrue(SimpleMath.canParseToFloat("001"));
		assertTrue(SimpleMath.canParseToFloat("-287"));
		assertTrue(SimpleMath.canParseToFloat("2888"));
		assertTrue(SimpleMath.canParseToFloat("2147483647"));
		assertTrue(SimpleMath.canParseToFloat("3.4028235E38"));
		assertTrue(SimpleMath.canParseToFloat("3.14159"));
		assertTrue(SimpleMath.canParseToFloat("-3.14159f"));

		assertFalse(SimpleMath.canParseToFloat(null));
		assertFalse(SimpleMath.canParseToFloat(""));
		assertFalse(SimpleMath.canParseToFloat("ten"));
	}

}
