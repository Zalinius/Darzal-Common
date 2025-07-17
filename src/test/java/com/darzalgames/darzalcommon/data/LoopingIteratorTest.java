package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.functional.Do;

class LoopingIteratorTest {

	@Test
	void constructor_withEmptyIterable_throwsIllegalArgumentException() {
		List<Integer> emptyList = List.of();

		assertThrows(IllegalArgumentException.class, () -> new LoopingIterator<>(emptyList));
	}

	@Test
	void hasNext_alwaysTrue() {
		Iterator<Integer> loopingIterator = new LoopingIterator<>(List.of(1, 2, 3));

		Do.xTimes(10, () -> {
			loopingIterator.next();
			assertTrue(loopingIterator.hasNext());
		});
	}

	@Test
	void next_loopsThroughValues() {
		Iterator<Integer> loopingIterator = new LoopingIterator<>(List.of(1, 2, 3));

		assertEquals(1, loopingIterator.next());
		assertEquals(2, loopingIterator.next());
		assertEquals(3, loopingIterator.next());
		assertEquals(1, loopingIterator.next());
		assertEquals(2, loopingIterator.next());
		assertEquals(3, loopingIterator.next());
		assertEquals(1, loopingIterator.next());
		assertEquals(2, loopingIterator.next());
		assertEquals(3, loopingIterator.next());
	}

}
