package com.darzalgames.darzalcommon.string;

import java.util.*;

/**
 * Goofy and unusual string operations
 */
public class StringThings {

	private StringThings() {}

	/**
	 * Convert a string to be in Title Case, using whitespace as a delimeter
	 * @param original the string to convert
	 * @return a new string in Title Case
	 */
	public static String toTitleCase(String original) {
		StringBuilder titleCase = new StringBuilder();
		char lastChar = ' ';
		for (int i = 0; i < original.length(); i++) {
			char currentChar = original.charAt(i);
			if (Character.isWhitespace(lastChar)) {
				titleCase.append(Character.toUpperCase(currentChar));
			} else {
				titleCase.append(Character.toLowerCase(currentChar));
			}
			lastChar = currentChar;
		}

		return titleCase.toString();
	}

	/**
	 * Shuffles the characters in a String
	 * @param original the input String
	 * @return A new String with randomly shuffled characters
	 */
	public static String shuffleCharacters(String original) {
		return shuffleCharacters(original, new Random());
	}

	/**
	 * Shuffles the characters in a String
	 * @param original the input String
	 * @param random   the source of randomness to shuffle the String
	 * @return A new String with randomly shuffled characters
	 */
	public static String shuffleCharacters(String original, Random random) {
		char[] chars = original.toCharArray();
		List<Character> charsList = new ArrayList<>();
		for (int i = 0; i < chars.length; i++) {
			charsList.add(chars[i]);
		}
		Collections.shuffle(charsList, random);

		for (int i = 0; i < chars.length; i++) {
			chars[i] = charsList.get(i);
		}

		return new String(chars);
	}

}
