package com.zalinius.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TupleTest {
	
	@Test
	void accessors_bothValues_works() throws Exception {
		Tuple<Integer, Double> tuple = new Tuple<>(4, 2.7);
		
		int firstResult = tuple.e;
		double secondResult = tuple.f;
		
		assertEquals(4, firstResult);
		assertEquals(2.7, secondResult);
	}

}
