package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class ListFactoryTest {

	@Test
	void of_onIntegers_createsList() {
		List<Integer> list = ListFactory.of(0,1,2);

		assertEquals(3, list.size());
		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
	}

	@Test
	void of_returnValue_supportsModification() {
		List<Integer> list = ListFactory.of(0,1,2);

		assertDoesNotThrow(() -> list.add(3));
		assertEquals(4, list.size());
		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertEquals(3, list.get(3));
	}

}
