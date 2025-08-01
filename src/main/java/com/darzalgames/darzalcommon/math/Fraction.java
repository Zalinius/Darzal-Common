package com.darzalgames.darzalcommon.math;

import java.util.Objects;

/**
 * A class to represent exact fractions.
 */
public class Fraction implements Comparable<Fraction> {

	//TODO can this become a record?
	//TODO make these private at version 0.7.0
	public final int numerator;
	public final int denominator;

	/**
	 * Creates a fraction with a value of 0
	 */
	public Fraction(){
		this(0, 1);
	}

	/**
	 * Creates an integer fraction
	 * @param wholeNumber The integer value of the fraction
	 */
	public Fraction(int wholeNumber){
		this(wholeNumber, 1);
	}

	/**
	 * Creates a fraction
	 * @param numerator The integer numerator
	 * @param denominator The integer denominator. Must be non zero
	 */
	public Fraction(int numerator, int denominator){
		if(denominator == 0) {
			throw new ArithmeticException("Fraction can not have a zero denominator");
		}

		if(denominator < 0) {
			numerator = -numerator;
			denominator = -denominator;
		}

		int greatestCommonDivisor = GCD.gcd(numerator, denominator);

		this.numerator = numerator / greatestCommonDivisor;
		this.denominator = denominator / greatestCommonDivisor;
	}

	/**
	 * The numerator of the fraction. May be 0 or negative
	 * @return the numerator as an integer
	 */
	public int numerator() {
		return numerator;
	}

	/**
	 * The denominator of the fraction, guaranteed to be a positive integer
	 * @return the denominator as an integer
	 */
	public int denominator() {
		return denominator;
	}

	/**
	 * Computes this fraction's value as a float
	 * @return this fraction as a float
	 */
	public float toFloat() {
		return (float)numerator / (float)denominator;
	}

	/**
	 * Computes this fraction's value as a double
	 * @return this fraction as a double
	 */
	public double toDouble()  {
		return (double)numerator / (double)denominator;
	}

	/**
	 * Checks if this fraction is strictly-positive
	 * @return true if the fraction is strictly-positive
	 */
	public boolean isPositive() {
		return numerator > 0;
	}

	/**
	 * Checks if this fraction is zero
	 * @return true if the fraction is equal to zero
	 */
	public boolean isZero() {
		return numerator == 0;
	}

	/**
	 * Checks if this fraction is strictly-negative
	 * @return true if the fraction is strictly-negative
	 */
	public boolean isNegative() {
		return numerator < 0;
	}

	/**
	 * Checks if this fraction represents a whole number
	 * @return true if the fraction is an integer
	 */
	public boolean isInteger() {
		return denominator == 1;
	}

	/**
	 * Returns a new fraction equal to the multiplication of this fraction and an integer factor
	 * @param factor the factor to scale the fraction by
	 * @return a new scaled fraction
	 */
	public Fraction scale(int factor){
		return new Fraction(numerator*factor, denominator);
	}

	/**
	 * Inverts the numerator and denominator, and returns it as a new fraction
	 * @return A new fraction, representing the inverse of this fraction
	 * @throws ArithmeticException if this fraction's numerator is 0, as the zero fraction cannot be inverted
	 */
	public Fraction invert() {
		if(isZero()) {
			throw new ArithmeticException("Cannot invert fraction as numerator is zero: " + toString());
		}

		return new Fraction(denominator, numerator);
	}

	/**
	 * Returns a new fraction, which is this fraction negated
	 * @return a new fraction negated
	 */
	public Fraction negate() {
		return new Fraction(-numerator, denominator);
	}

	/**
	 * Checks if this fraction is greater than another
	 * @param other the fraction to compare to
	 * @return true if this fraction is strictly greater than the other, false otherwise
	 */
	public boolean isGreaterThan(Fraction other) {
		return compareTo(other) > 0;
	}

	/**
	 * Checks if this fraction is greater or equal to another
	 * @param other the fraction to compare to
	 * @return true if this fraction is greater or equal to the other, false otherwise
	 */
	public boolean isGreaterThanOrEqual(Fraction other) {
		return compareTo(other) >= 0;
	}

	/**
	 * Checks if this fraction is less than another
	 * @param other the fraction to compare to
	 * @return true if this fraction is strictly smaller than the other, false otherwise
	 */
	public boolean isLessThan(Fraction other) {
		return compareTo(other) < 0;
	}

	/**
	 * Checks if this fraction is less or equal to another
	 * @param other the fraction to compare to
	 * @return true if this fraction is less or equal to the other, false otherwise
	 */
	public boolean isLessThanOrEqual(Fraction other) {
		return compareTo(other) <= 0;
	}


	/**
	 * Adds two fractions together
	 * @param f1 the first fraction
	 * @param f2 the second fraction
	 * @return the sum of the fractions, with a common denominator
	 */
	public static Fraction add(Fraction f1, Fraction f2){
		return new Fraction(f1.numerator*f2.denominator+f2.numerator*f1.denominator, f1.denominator*f2.denominator);
	}

	/**
	 * Subtracts one fraction from another
	 * @param f1 the first fraction
	 * @param f2 the second fraction
	 * @return the difference of the fractions, with a common denominator
	 */
	public static Fraction subtract(Fraction f1, Fraction f2){
		return new Fraction(f1.numerator*f2.denominator-f2.numerator*f1.denominator, f1.denominator*f2.denominator);
	}

	/**
	 * Multiplies two fractions together
	 * @param f1 the first fraction
	 * @param f2 the second fraction
	 * @return the product of the fractions, with a common denominator
	 */
	public static Fraction multiply(Fraction f1, Fraction f2){
		return new Fraction(f1.numerator*f2.numerator, f1.denominator*f2.denominator);
	}

	/**
	 * Divides one fraction with another
	 * @param f1 the first fraction
	 * @param f2 the second fraction
	 * @return the quotient of the fractions, with a common denominator
	 * @throws ArithmeticException if the second fraction (divisor) is equal to 0
	 */
	public static Fraction divide(Fraction f1, Fraction f2){
		if(f2.isZero()) {
			throw new ArithmeticException("Cannot divide by a zero fraction: " + f2);
		}
		return new Fraction(f1.numerator*f2.denominator, f1.denominator*f2.numerator );
	}

	/**
	 * Computes how many times a fraction can wholly divide another fraction
	 * @param dividend the fraction to divide
	 * @param divisor the fraction to divide by
	 * @return The integer/whole number of times the divisor fits into the dividend
	 */
	public static int integerDivision(Fraction dividend, Fraction divisor) {
		if(divisor.isZero()) {
			throw new ArithmeticException("Can't divide by zero fraction: " + divisor);
		}
		if(dividend.isNegative() || divisor.isNegative()) {
			throw new UnsupportedOperationException("negative integer division not implemented lol");
		}

		int lowestCommonDenominator = lowestCommonDenominator(dividend, divisor);

		int dividendNumeratorOnLCD = dividend.numerator * (lowestCommonDenominator / dividend.denominator);
		int divisorNumeratorOnLCD = divisor.numerator * (lowestCommonDenominator / divisor.denominator);

		return dividendNumeratorOnLCD / divisorNumeratorOnLCD;
	}

	/**
	 * Computes the remainder from  wholly dividing a fraction with another fraction
	 * @param dividend the fraction to divide
	 * @param divisor the fraction to divide by
	 * @return The remainder from the integer division. remainder is within [0, divisor[
	 */
	public static Fraction integerRemainder(Fraction dividend, Fraction divisor) {
		if(divisor.isZero()) {
			throw new ArithmeticException("Can't divide by zero fraction: " + divisor);
		}
		if(dividend.isNegative() || divisor.isNegative()) {
			throw new UnsupportedOperationException("negative integer remainder not implemented lol");
		}

		int lowestCommonDenominator = lowestCommonDenominator(dividend, divisor);

		int dividendNumeratorOnLCD = dividend.numerator * (lowestCommonDenominator / dividend.denominator);
		int divisorNumeratorOnLCD = divisor.numerator * (lowestCommonDenominator / divisor.denominator);

		return new Fraction(dividendNumeratorOnLCD % divisorNumeratorOnLCD, lowestCommonDenominator);
	}

	/**
	 * Computes the lowest common denominator for both fractions
	 * @param f1 the first fraction
	 * @param f2 the first fraction
	 * @return the smallest positive integer denominator common to both fractions
	 */
	public static int lowestCommonDenominator(Fraction f1, Fraction f2) {
		int d1 = f1.denominator;
		int d2 = f2.denominator;
		return d1 / GCD.gcd(d1, d2) * d2;
	}

	@Override
	public String toString(){
		return numerator+"/"+denominator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(denominator, numerator);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Fraction other = (Fraction) obj;
		return denominator == other.denominator && numerator == other.numerator;
	}

	@Override
	public int compareTo(Fraction other) {
		int lowestCommonDenominator = lowestCommonDenominator(this, other);

		int thisNumeratorOnLCD = numerator * (lowestCommonDenominator / denominator);
		int otherNumeratorOnLCD = other.numerator * (lowestCommonDenominator / other.denominator);

		return thisNumeratorOnLCD - otherNumeratorOnLCD;
	}

}
