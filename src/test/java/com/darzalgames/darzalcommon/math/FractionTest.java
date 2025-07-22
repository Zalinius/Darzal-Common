package com.darzalgames.darzalcommon.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FractionTest {


	@Test
	void defaultConstructor_constructsZeroFraction() throws Exception {
		Fraction defaultFraction = new Fraction();

		assertTrue(defaultFraction.isZero());
	}

	@Test
	void singleIntConstructor_constructsIntegerFraction() throws Exception {
		Fraction defaultFraction = new Fraction(2);

		assertTrue(defaultFraction.isInteger());
	}

	@Test
	void construct_fractionWith0Denominator_throwsArithmeticException() throws Exception {
		int numerator = 1;
		int denominator = 0;

		assertThrows(ArithmeticException.class, () -> new Fraction(numerator, denominator));
	}

	@Test
	void construct_unreducedArguments_becomeReduced() throws Exception {
		Fraction unreducedInputsFraction = new Fraction(2, 4);

		assertEquals(1, unreducedInputsFraction.numerator());
		assertEquals(2, unreducedInputsFraction.denominator());
	}

	@Test
	void construct_negativeArguments_becomePositive() throws Exception {
		Fraction negativeInputsFraction = new Fraction(-1, -2);

		assertEquals(1, negativeInputsFraction.numerator());
		assertEquals(2, negativeInputsFraction.denominator());
		assertTrue(negativeInputsFraction.isPositive());
	}

	@Test
	void toFloat_onIntegerFraction_returnsWholeFloat() {
		Fraction integerFraction = new Fraction(5);

		assertEquals(5f, integerFraction.toFloat());
	}

	@Test
	void toFloat_onFraction_returnsCorrespondingFloat() {
		assertEquals(0.75f, new Fraction(3, 4).toFloat());
		assertEquals(0.1f, new Fraction(1, 10).toFloat());
		assertEquals(-0.6f, new Fraction(-3, 5).toFloat());
	}

	@Test
	void toDouble_onIntegerFraction_returnsWholeDouble() {
		Fraction integerFraction = new Fraction(5);

		assertEquals(5d, integerFraction.toDouble());
	}

	@Test
	void toDouble_onFraction_returnsCorrespondingDouble() {
		assertEquals(0.75d, new Fraction(3, 4).toDouble());
		assertEquals(0.1d, new Fraction(1, 10).toDouble());
		assertEquals(-0.6d, new Fraction(-3, 5).toDouble());
	}

	@Test
	void invert_fractionWith0Numerator_throwsArithmeticException() throws Exception {
		Fraction fraction = new Fraction(0, 1);

		assertThrows(ArithmeticException.class, () -> fraction.invert());
	}

	@Test
	void invert_nonZeroFraction_invertsNumeratorAndDenominator() throws Exception {
		Fraction fraction = new Fraction(2, 3);

		Fraction result = fraction.invert();

		assertEquals(3, result.numerator());
		assertEquals(2, result.denominator());
	}

	@Test
	void scale_nonZeroFractionByZero_returnsZeroFraction() throws Exception {
		Fraction fraction = new Fraction(2, 3);

		Fraction result = fraction.scale(0);

		assertTrue(result.isZero());
	}

	@Test
	void scale_nonZeroFractionByInteger_returnsScaledFraction() throws Exception {
		Fraction fraction = new Fraction(2, 3);

		Fraction result = fraction.scale(4);

		assertEquals(8, result.numerator());
		assertEquals(3, result.denominator());
	}


	@Test
	void negate_positiveFraction_becomesNegative() throws Exception {
		Fraction fraction = new Fraction(2, 3);

		Fraction result = fraction.negate();

		assertTrue(result.isNegative());
		assertFalse(result.isPositive());
	}

	@Test
	void negate_negativeFraction_becomesPositive() throws Exception {
		Fraction fraction = new Fraction(-2, 3);

		Fraction result = fraction.negate();

		assertTrue(result.isPositive());
		assertFalse(result.isNegative());
	}

	@Test
	void negate_zeroFraction_staysZero() throws Exception {
		Fraction fraction = new Fraction(0, 3);

		Fraction result = fraction.negate();

		assertTrue(result.isZero());
	}


	@Test
	void isPositive_onAZeroFraction_isFalse() throws Exception {
		Fraction defaultFraction = new Fraction();

		assertFalse(defaultFraction.isPositive());
	}
	@Test
	void isNegative_onAZeroFraction_isFalse() throws Exception {
		Fraction defaultFraction = new Fraction();

		assertFalse(defaultFraction.isNegative());
	}

	@Test
	void isInteger_onFractionalFraction_isFalse() throws Exception {
		Fraction defaultFraction = new Fraction(1, 2);

		assertFalse(defaultFraction.isInteger());
	}

	@Test
	void isGreaterThan_onSmallerFraction_isTrue() throws Exception {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 3);

		assertTrue(f1.isGreaterThan(f2));
	}

	@Test
	void isGreaterThan_onEqualFraction_isFalse() throws Exception {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 2);

		assertFalse(f1.isGreaterThan(f2));
	}

	@Test
	void isGreaterThan_onLargerFraction_isFalse() throws Exception {
		Fraction f1 = new Fraction(1, 3);
		Fraction f2 = new Fraction(1, 2);

		assertFalse(f1.isGreaterThan(f2));
	}

	@Test
	void isGreaterThanOrEqual_onSmallerFraction_isTrue() throws Exception {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 3);

		assertTrue(f1.isGreaterThanOrEqual(f2));
	}

	@Test
	void isGreaterThanOrEqual_onEqualFraction_isTrue() throws Exception {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 2);

		assertTrue(f1.isGreaterThanOrEqual(f2));
	}

	@Test
	void isGreaterThanOrEqual_onLargerFraction_isFalse() throws Exception {
		Fraction f1 = new Fraction(1, 3);
		Fraction f2 = new Fraction(1, 2);

		assertFalse(f1.isGreaterThanOrEqual(f2));
	}

	@Test
	void islessThan_onGreaterFraction_isTrue() throws Exception {
		Fraction f1 = new Fraction(1, 3);
		Fraction f2 = new Fraction(1, 2);

		assertTrue(f1.isLessThan(f2));
	}

	@Test
	void islessThan_onEqualFraction_isFalse() throws Exception {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 2);

		assertFalse(f1.isLessThan(f2));
	}

	@Test
	void islessThan_onSmallerFraction_isFalse() throws Exception {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 3);

		assertFalse(f1.isLessThan(f2));
	}

	@Test
	void islessThanOrEqual_onGreaterFraction_isTrue() throws Exception {
		Fraction f1 = new Fraction(1, 3);
		Fraction f2 = new Fraction(1, 2);

		assertTrue(f1.isLessThanOrEqual(f2));
	}

	@Test
	void islessThanOrEqual_onEqualFraction_isTrue() throws Exception {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 2);

		assertTrue(f1.isLessThanOrEqual(f2));
	}

	@Test
	void islessThanOrEqual_onSmallerFraction_isFalse() throws Exception {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 3);

		assertFalse(f1.isLessThanOrEqual(f2));
	}

	@Test
	void add_twoFractions_isCorrect() throws Exception {
		Fraction f1 = new Fraction(1, 3);
		Fraction f2 = new Fraction(1, 2);

		Fraction addition = Fraction.add(f1, f2);

		Fraction expected = new Fraction(5, 6);
		assertEquals(expected, addition);
	}

	@Test
	void subtract_twoFractions_isCorrect() throws Exception {
		Fraction f1 = new Fraction(1, 3);
		Fraction f2 = new Fraction(1, 2);

		Fraction subtraction = Fraction.subtract(f1, f2);

		Fraction expected = new Fraction(-1, 6);
		assertEquals(expected, subtraction);
	}

	@Test
	void multiply_twoFractions_isCorrect() throws Exception {
		Fraction f1 = new Fraction(5,  7);
		Fraction f2 = new Fraction(7, 12);

		Fraction multiplication = Fraction.multiply(f1, f2);

		Fraction expected = new Fraction(5, 12);
		assertEquals(expected, multiplication);
	}

	@Test
	void divide_twoFractions_isCorrect() throws Exception {
		Fraction f1 = new Fraction(5,  7);
		Fraction f2 = new Fraction(7, 12);

		Fraction division = Fraction.divide(f1, f2);

		Fraction expected = new Fraction(60, 49);
		assertEquals(expected, division);
	}

	@Test
	void divide_fractionByZero_throwsArithmeticException() throws Exception {
		Fraction f1 = new Fraction(5,  7);
		Fraction f2 = new Fraction(0, 12);

		assertThrows(ArithmeticException.class, () -> Fraction.divide(f1, f2));
	}

	@Test
	void integerDivision_fractionByZero_throwsArithmeticException() {
		Fraction f1 = new Fraction(5,  7);
		Fraction f2 = new Fraction(0, 12);

		assertThrows(ArithmeticException.class, () -> Fraction.integerDivision(f1, f2));
	}

	@Test
	void integerDivision_withZeroDividend_returnsZero() {
		Fraction f1 = new Fraction(0);
		Fraction f2 = new Fraction(5, 7);

		assertEquals(0, Fraction.integerDivision(f1, f2));
	}

	@Test
	void integerDivision_withDividendSmallerThanDivisor_returnsZero() {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(5, 7);

		assertEquals(0, Fraction.integerDivision(f1, f2));
	}

	@Test
	void integerDivision_withDividendExactMultipleOfDivisor_returnsThatMultiple() {
		Fraction f1 = new Fraction(20, 7);
		Fraction f2 = new Fraction(5, 7);

		assertEquals(4, Fraction.integerDivision(f1, f2));
	}

	@Test
	void integerDivision_withDividendPartialMultipleOfDivisor_returnsIntegerPartOfThatMultiple() {
		Fraction f1 = new Fraction(22, 7);
		Fraction f2 = new Fraction(5, 7);

		assertEquals(4, Fraction.integerDivision(f1, f2));
	}

	@Test
	void integerRemainder_fractionByZero_throwsArithmeticException() {
		Fraction f1 = new Fraction(5,  7);
		Fraction f2 = new Fraction(0, 12);

		assertThrows(ArithmeticException.class, () -> Fraction.integerRemainder(f1, f2));
	}

	@Test
	void integerRemainder_withDividendMultipleOfDivisor_returnsZeroFraction() {
		Fraction f1 = new Fraction(10,  7);
		Fraction f2 = new Fraction(5, 7);

		assertTrue(Fraction.integerRemainder(f1, f2).isZero());
	}

	@Test
	void integerRemainder_withTwoFractionsWithCommonBase_returnsRemainder() {
		Fraction f1 = new Fraction(10,  7);
		Fraction f2 = new Fraction(3, 7);

		assertEquals(new Fraction(1, 7), Fraction.integerRemainder(f1, f2));
	}

	@Test
	void integerRemainder_withTwoFractionsWithDifferentBases_returnsRemainder() {
		Fraction f1 = new Fraction(10,  7);
		Fraction f2 = new Fraction(3, 8);

		assertEquals(new Fraction(17, 56), Fraction.integerRemainder(f1, f2));
	}

	@Test
	void lowestCommondDenominator_forSimpleMultiplicationCase_isCorrect() {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 3);

		assertEquals(6, Fraction.lowestCommonDenominator(f1, f2));
		assertEquals(6, Fraction.lowestCommonDenominator(f2, f1));
	}

	@Test
	void lowestCommondDenominator_forMultiplicationThenSimplicationCase_isCorrect() {
		Fraction f1 = new Fraction(1, 6);
		Fraction f2 = new Fraction(1, 21);

		assertEquals(42, Fraction.lowestCommonDenominator(f1, f2));
		assertEquals(42, Fraction.lowestCommonDenominator(f2, f1));
	}

	@Test
	void compareTo_onEqualFractions_returns0() {
		Fraction f1 = new Fraction(1, 6);
		Fraction f2 = new Fraction(1, 6);

		assertEquals(0, f1.compareTo(f2));
		assertEquals(0, f2.compareTo(f1));
	}

	@Test
	void compareTo_onDifferentFractions_returnsNegativeValueWhenFirstIsSmaller() {
		Fraction f1 = new Fraction(0, 6);
		Fraction f2 = new Fraction(1, 6);

		assertTrue(0 > f1.compareTo(f2));
	}

	@Test
	void compareTo_onDifferentFractions_returnsPositiveValueWhenFirstIsBigger() {
		Fraction f1 = new Fraction(1, 6);
		Fraction f2 = new Fraction(0, 6);

		assertTrue(0 < f1.compareTo(f2));
	}

	@Test
	void compareTo_onDifferentFractions_returnsInvertedValueWhenCallOrderInversed() {
		Fraction f1 = new Fraction(1, 6);
		Fraction f2 = new Fraction(0, 6);

		assertEquals(-f1.compareTo(f2), f2.compareTo(f1));
	}

}
