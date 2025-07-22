package com.darzalgames.darzalcommon.math;

/**
 * Some simple and convenient math functions for operating on integers and floats
 */
public class SimpleMath {

	private SimpleMath() {}

	/**
	 * Checks if an integer is even
	 * @param value the integer to test
	 * @return true if the value is even
	 */
	public static boolean isEven(int value) {
		return value % 2 == 0;
	}
	/**
	 * Checks if an integer is odd
	 * @param value the integer to test
	 * @return true if the value is odd
	 */
	public static boolean isOdd(int value) {
		return value % 2 == 1 || value % 2 == -1;
	}

	/**
	 * Checks if a value is an integer multiple of an integer divisor
	 * @param value The value checked
	 * @param base the divisor
	 * @return true if the value is a multiple. false otherwise
	 */
	public static boolean isMultiple(int value, int base) {
		return value % base == 0;
	}

	/**
	 * Checks if a value is between two other values (inclusive)
	 * The bounds may be given in either order
	 * @param bound1 the first bound
	 * @param bound2 the second bound
	 * @param value the value to test
	 * @return True if and only if the value is between the two bounds
	 */
	public static boolean isBetween(float bound1, float bound2, float value) {
		if(bound1 > bound2) {
			float newBound1 = bound2;
			float newBound2 = bound1;
			return isBetween(newBound1, newBound2, value);
		}
		else {
			return value >= bound1 && value <= bound2;
		}
	}


	/**
	 * Checks if a string represents an integer
	 * @param string the string to check
	 * @return true if the string can safely be parsed to an integer
	 */
	public static boolean canParseToInteger(String string) {
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if a string represents a float
	 * @param string the string to check
	 * @return true if the string can safely be parsed to a float
	 */
	public static boolean canParseToFloat(String string) {
		try {
			Float.parseFloat(string);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Computes the greatest common divisor for two integers
	 * @param x the first integer
	 * @param y the second integer
	 * @return their greatest common divisor
	 */
	public static int greatestCommonDivisor(int x, int y){
		x = Math.abs(x);
		y = Math.abs(y);
		if(x<y) {
			int temp = y;
			y = x;
			x = temp;
		}

		return gcdRecursiveCall(x,y);
	}

	private static int gcdRecursiveCall(int x, int y){
		if(y==0) {
			return x;
		}
		return (gcdRecursiveCall(y, x%y));
	}

}
