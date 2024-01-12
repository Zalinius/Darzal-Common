package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;

import org.junit.jupiter.api.Test;

class ConsumersTest {
	
	// These tests are a bit silly, but they definitely prove that nothing happens
	// and also that the null consumers don't cause crashes which is nice
	
	@Test
	void nullConsumer_whenUsed_doesNothing() throws Exception {
		Consumer<AtomicInteger> nullConsumer = Consumers.nullConsumer();
		AtomicInteger atomicInteger = new AtomicInteger(0);
		
		nullConsumer.accept(atomicInteger);
		
		assertEquals(0, atomicInteger.get());		
	}
	
	@Test
	void nullIntegerConsumer_whenUsed_doesNothing() throws Exception {
		IntConsumer nullConsumer = Consumers.nullIntegerConsumer();
		int test = 5;
		
		nullConsumer.accept(test);
		
		assertEquals(5, test);		
	}
	
	@Test
	void nullLongConsumer_whenUsed_doesNothing() throws Exception {
		LongConsumer nullConsumer = Consumers.nullLongConsumer();
		long test = -4;
		
		nullConsumer.accept(test);
		
		assertEquals(-4, test);		
	}
	
	@Test
	void nullDoubleConsumer_whenUsed_doesNothing() throws Exception {
		DoubleConsumer nullConsumer = Consumers.nullDoubleConsumer();
		double test = 3;
		
		nullConsumer.accept(test);
		
		assertEquals(3, test);		
	}

}
