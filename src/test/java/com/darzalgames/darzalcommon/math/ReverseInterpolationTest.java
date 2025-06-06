package com.darzalgames.darzalcommon.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ReverseInterpolationTest {

	@Test
	void linear_forStartValue_returnsZero() {
		float start = 2;
		float end = 6;
		float value = 2;

		float interpolant = ReverseInterpolation.linear(start, end, value);

		assertEquals(0, interpolant);
	}

	@Test
	void linear_forEndValue_returnsOne() {
		float start = 2;
		float end = 6;
		float value = 6;

		float interpolant = ReverseInterpolation.linear(start, end, value);

		assertEquals(1, interpolant);
	}

	@Test
	void linear_forValueHalfwayBetween_returnsHalf() {
		float start = 2;
		float end = 6;
		float value = 4;

		float interpolant = ReverseInterpolation.linear(start, end, value);

		assertEquals(0.5f, interpolant);
	}

	@Test
	void linear_forValueQuarterWayThrough_returnsQuarter() {
		float start = 2;
		float end = 6;
		float value = 3;

		float interpolant = ReverseInterpolation.linear(start, end, value);

		assertEquals(0.25f, interpolant);
	}

}
