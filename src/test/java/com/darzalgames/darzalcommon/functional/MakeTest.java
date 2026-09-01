package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class MakeTest {

	@Test
	void xTimes_with0_createsEmptyList() {
		List<String> list = Make.xTimes(0, () -> "");

		assertTrue(list.isEmpty());
	}

	@Test
	void xTimes_with3_createsListWith3Strings() {
		List<String> list = Make.xTimes(3, () -> "");

		assertEquals(3, list.size());
		assertEquals("", list.get(0));
		assertEquals("", list.get(1));
		assertEquals("", list.get(2));
	}

	@Test
	void xTimesWithI_with0_createsEmptyList() {
		List<String> list = Make.xTimesWithI(0, i -> "");

		assertTrue(list.isEmpty());
	}

	@Test
	void xTimesWithI_with3_createsListWithStringsMadeFromIndex() {
		List<String> list = Make.xTimesWithI(3, Integer::toString);

		assertEquals(3, list.size());
		assertEquals("0", list.get(0));
		assertEquals("1", list.get(1));
		assertEquals("2", list.get(2));
	}

}
