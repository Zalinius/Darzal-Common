package com.darzalgames.darzalcommon.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SimpleMathTest {

	@Test
	void isEven_onEvenInt_returnsTrue(){
		int input = 6;

		boolean result = SimpleMath.isEven(input);
		assertTrue(result);
	}

	@Test
	void isEven_onOddInt_returnsFalse(){
		int input = 5;

		boolean result = SimpleMath.isEven(input);

		assertFalse(result);
	}

	@Test
	void isEven_onZero_returnsTrue(){
		int input = 0;

		boolean result = SimpleMath.isEven(input);

		assertTrue(result);
	}

	@Test
	void isEven_onNegativeEvenInt_returnsTrue(){
		int input = -4;

		boolean result = SimpleMath.isEven(input);

		assertTrue(result);
	}

	@Test
	void isEven_onNegativeOddInt_returnsFalse(){
		int input = -3;

		boolean result = SimpleMath.isEven(input);

		assertFalse(result);
	}


	//isOdd int
	@Test
	void isOdd_onEvenInt_returnsFalse(){
		int input = 6;

		boolean result = SimpleMath.isOdd(input);

		assertFalse(result);
	}

	@Test
	void isOdd_onOddInt_returnsTrue(){
		int input = 5;

		boolean result = SimpleMath.isOdd(input);

		assertTrue(result);
	}

	@Test
	void isOdd_onZero_returnsFalse(){
		int input = 0;

		boolean result = SimpleMath.isOdd(input);

		assertFalse(result);
	}

	@Test
	void isOdd_onNegativeEvenInt_returnsFalse(){
		int input = -4;

		boolean result = SimpleMath.isOdd(input);

		assertFalse(result);
	}

	@Test
	void isOdd_onNegativeOddInt_returnsTrue(){
		int input = -3;

		boolean result = SimpleMath.isOdd(input);

		assertTrue(result);
	}

	@Test
	void isBetween_onLeftBound_returnsTrue(){
		int left = 0;
		int right = 5;
		int input = 0;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertTrue(result);
	}

	@Test
	void isBetween_onRightBound_returnsTrue(){
		int left = 0;
		int right = 5;
		int input = 5;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertTrue(result);
	}

	@Test
	void isBetween_withinBounds_returnsTrue(){
		int left = 0;
		int right = 5;
		int input = 2;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertTrue(result);
	}

	@Test
	void isBetween_outsideLeftOfBounds_returnsFalse(){
		int left = 0;
		int right = 5;
		int input = -3;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertFalse(result);
	}

	@Test
	void isBetween_outsideRightOfBounds_returnsFalse(){
		int left = 0;
		int right = 5;
		int input = 8;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertFalse(result);
	}

	@Test
	void isBetween_withinReversedBounds_returnsTrue(){
		int left = 5;
		int right = 0;
		int input = 2;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertTrue(result);
	}

	@Test
	void isBetween_outsideReversedBounds_returnsFalse(){
		int left = 5;
		int right = 0;
		int input = -3;

		boolean result = SimpleMath.isBetween(left, right, input);

		assertFalse(result);
	}

	@Test
	void isMultiple_onMultipleOddInts_returnsTrue(){
		int value = 9;
		int base = 3;

		boolean result = SimpleMath.isMultiple(value, base);

		assertTrue(result);
	}

	@Test
	void isMultiple_onMultipleEvenInts_returnsTrue(){
		int value = 8;
		int base = 4;

		boolean result = SimpleMath.isMultiple(value, base);

		assertTrue(result);
	}

	@Test
	void isMultiple_onNonMultipleOddInts_returnsFalse(){
		int value = 11;
		int base = 3;

		boolean result = SimpleMath.isMultiple(value, base);

		assertFalse(result);
	}

	@Test
	void isMultiple_onNonMultipleEvenInts_returnsFalse(){
		int value = 6;
		int base = 4;

		boolean result = SimpleMath.isMultiple(value, base);

		assertFalse(result);
	}

	//isMultiple float
	@Test
	void isMultiple_onMultipleOddFloat_returnsTrue(){
		float value = 9.3f;
		int base = 3;

		boolean result = SimpleMath.isMultiple(value, base);

		assertTrue(result);
	}

	@Test
	void isMultiple_onMultipleEvenFloat_returnsTrue(){
		float value = 8.7f;
		int base = 4;

		boolean result = SimpleMath.isMultiple(value, base);

		assertTrue(result);
	}

	@Test
	void isMultiple_onNonMultipleOddFloat_returnsFalse(){
		float value = 11.7f;
		int base = 3;

		boolean result = SimpleMath.isMultiple(value, base);

		assertFalse(result);
	}

	@Test
	void isMultiple_onNonMultipleEvenFloat_returnsFalse(){
		float value = 6.8f;
		int base = 4;

		boolean result = SimpleMath.isMultiple(value, base);

		assertFalse(result);
	}

}
