package com.zalinius.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

class ConsumersTest {
	
	@Test
	void nullConsumer_whenUsed_doesNothing() throws Exception {
		Consumer<AtomicInteger> nullConsumer = Consumers.nullConsumer();
		AtomicInteger atomicInteger = new AtomicInteger(0);
		
		nullConsumer.accept(atomicInteger);
		
		assertEquals(0, atomicInteger.get());		
	}

}
