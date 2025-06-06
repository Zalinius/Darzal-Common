package com.darzalgames.darzalcommon.strings;

/**
 * A variety of string utils in the style of apache-commons
 */
public class StringUtils {

	private StringUtils() {}

	/**
	 * Checks if a string is blank
	 * @param string A string to test
	 * @return True if the string only contains whitespace characters, false otherwise
	 */
	public static boolean isBlank(String string) {
		return string.chars().allMatch(Character::isWhitespace);
	}

}
