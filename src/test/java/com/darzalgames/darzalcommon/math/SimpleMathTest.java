package com.darzalgames.darzalcommon.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		
	//isMultiple int
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
		
		@Test
		void clamp_belowMinimum_returnsMinimum(){
			int value = 1;
			int minimum = 2;
			int maximum = 6;
			
			int result = SimpleMath.clamp(value, minimum, maximum);
			
			assertEquals(minimum, result);
		}
		
		@Test
		void clamp_atMinimum_returnsMinimum(){
			int value = 2;
			int minimum = 2;
			int maximum = 6;
			
			int result = SimpleMath.clamp(value, minimum, maximum);
			
			assertEquals(minimum, result);
		}
		
		@Test
		void clamp_aboveMaximum_returnsMaximum(){
			int value = 8;
			int minimum = 2;
			int maximum = 6;
			
			int result = SimpleMath.clamp(value, minimum, maximum);
			
			assertEquals(maximum, result);
		}
		
		@Test
		void clamp_atMaximum_returnsMaximum(){
			int value = 6;
			int minimum = 2;
			int maximum = 6;
			
			int result = SimpleMath.clamp(value, minimum, maximum);
			
			assertEquals(maximum, result);
		}

		@Test
		void clamp_withinRange_returnsValue(){
			int value = 4;
			int minimum = 2;
			int maximum = 6;
			
			int result = SimpleMath.clamp(value, minimum, maximum);
			
			assertEquals(value, result);
		}

}
