package com.darzalgames.darzalcommon.strings;

public class StringUtils {
	
	/**
	 * @param string A string to test
	 * @return True if the string only contains whitespace characters, false otherwise
	 */
	public static boolean isBlank(String string) {
		return string.chars().allMatch(Character::isWhitespace);
	}

}
