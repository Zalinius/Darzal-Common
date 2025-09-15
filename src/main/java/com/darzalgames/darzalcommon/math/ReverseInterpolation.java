package com.darzalgames.darzalcommon.math;

/**
 * A collection of reverse interpolation functions
 * They take a value within a [start,end] range, and return an interpolant [0,1] representing the values progress through the range.
 * Put another way, reverse interpolation functions undo the effects of an interpolation function, and return the original interpolant.
 */
public class ReverseInterpolation {

	private ReverseInterpolation() {}

	/**
	 * Reversed Linear Interpolation
	 * @param start The start value of the interpolation range
	 * @param end   The end value of the interpolation range
	 * @param value The value within the interpolation range
	 * @return The interpolant corresponding to the value's interpolation within the range
	 */
	public static float linear(float start, float end, float value) {
		return (value - start) / (end - start);
	}

}
