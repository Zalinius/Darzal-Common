package com.darzalgames.darzalcommon.string;

import java.text.DecimalFormat;

/**
 * Helper class for formatting numbers
 */
public class NumberFormatHelper {

	private NumberFormatHelper() {}

	/**
	 * Formats a float as an Integer percentage <br>
	 * For example, 0.821f would be formatted as 82% <br>
	 * The output will be rounded, i.e. 0.0168f would be formatted as 2%
	 * @param percentage the input float value
	 * @return A String representing the original float as an integer percentage
	 */
	public static String formatAsIntegerPercentage(float percentage) {
		DecimalFormat decimalFormat = new DecimalFormat("##0%");
		return decimalFormat.format(percentage);
	}

}
