package com.darzalgames.darzalcommon.math;

import java.util.Objects;

/**
 * A class to represent exact fractions.
 */
public class Fraction {
	public final int numerator;
	public final int denominator;
	

	public Fraction(){
		this(0, 1);
	}
	
	public Fraction(int wholeNumber){
		this(wholeNumber, 1);
	}
	
	public Fraction(int numerator, int denominator){
		if(denominator == 0) {
			throw new ArithmeticException("Fraction can not have a zero denominator");
		}
		
		if(denominator < 0) {
			numerator =   -numerator;
			denominator = -denominator;
		}
		
		int greatestCommonDivisor = GCD.gcd(numerator, denominator);
		
		this.numerator = numerator / greatestCommonDivisor;
		this.denominator = denominator / greatestCommonDivisor;
	}
	
	public boolean isPositive() {
		return numerator > 0;
	}
	
	public boolean isZero() {
		return numerator == 0;
	}

	public boolean isNegative() {
		return numerator < 0;
	}
	
	public boolean isInteger() {
		return denominator == 1;
	}

	public Fraction scale(int k){
		return new Fraction(numerator*k, denominator);
	}
	
	public Fraction invert() {
		if(isZero()) {
			throw new ArithmeticException("Cannot invert fraction as numerator is zero: " + this.toString());
		}
		
		return new Fraction(denominator, numerator);
	}

	public Fraction negate() {
		return new Fraction(-numerator, denominator);
	}

	
	public static Fraction add(Fraction f1, Fraction f2){
		return new Fraction(f1.numerator*f2.denominator+f2.numerator*f1.denominator, f1.denominator*f2.denominator);
	}
	
	public static Fraction subtract(Fraction f1, Fraction f2){
		return new Fraction(f1.numerator*f2.denominator-f2.numerator*f1.denominator, f1.denominator*f2.denominator);
	}
	
	public static Fraction multiply(Fraction f1, Fraction f2){
		return new Fraction(f1.numerator*f2.numerator, f1.denominator*f2.denominator);
	}
	
	public static Fraction divide(Fraction f1, Fraction f2){
		if(f2.isZero()) {
			throw new ArithmeticException("Cannot divide by a zero fraction: " + f2);
		}
		return new Fraction(f1.numerator*f2.denominator, f1.denominator*f2.numerator );
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;
		return denominator == other.denominator && numerator == other.numerator;
	}

}
