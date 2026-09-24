package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class StreamFactoryTest {

	@Test
	void of_onEmptyIterator_returnsEmptyStream() {
		List<String> list = List.of();
		Iterator<String> it = list.iterator();

		Stream<String> stream = StreamFactory.of(it);

		assertEquals(0, stream.count());
	}

	@Test
	void of_onIterator_returnsStreamOfOrderedValues() {
		List<String> list = List.of("apple", "banana", "cherry");
		Iterator<String> it = list.iterator();

		Stream<String> stream = StreamFactory.of(it);

		List<String> streamedList = stream.toList();
		assertEquals(3, streamedList.size());
		assertEquals("apple", streamedList.get(0));
		assertEquals("banana", streamedList.get(1));
		assertEquals("cherry", streamedList.get(2));
	}

}
