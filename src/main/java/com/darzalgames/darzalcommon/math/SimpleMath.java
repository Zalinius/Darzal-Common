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

	public static boolean isMultiple(int value, int base) {
		return value % base == 0;
	}
	public static boolean isMultiple(float value, int base) {
		return isMultiple((int) value, base);
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
	 * Checks if a value is between two other values (exclusive)
	 * The bounds may be given in either order
	 * @param bound1 the first bound
	 * @param bound2 the second bound
	 * @param value the value to test
	 * @return True if and only if the value is between the two bounds, and not equal to the bounds
	 */
	public static boolean isBetweenExclusive(float bound1, float bound2, float value) {
		if(bound1 > bound2) {
			float newBound1 = bound2;
			float newBound2 = bound1;
			return isBetween(newBound1, newBound2, value);
		}
		else {
			return value > bound1 && value < bound2;
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

}
