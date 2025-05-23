package com.darzalgames.darzalcommon.math;

public class SimpleMath {
	
	private SimpleMath() {}

	/**
	 * @param value
	 * @return
	 */
	public static boolean isEven(int value) {
		return value % 2 == 0;
	}
	public static boolean isOdd(int value) {
		return value % 2 == 1 || value % 2 == -1;
	}
	
	public static boolean isMultiple(int value, int base) {
		return value % base == 0;
	}
	public static boolean isMultiple(float value, int base) {
		return isMultiple((int) value, base);
	}
	
	public static boolean canParseToInteger(String string) {
		try {
			Integer.parseInt(string);
		} catch (@SuppressWarnings("unused") NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean canParseToFloat(String string) {
		try {
			Float.parseFloat(string);
		} catch (@SuppressWarnings("unused") NumberFormatException e) {
			return false;
		}
		return true;
	}

}
