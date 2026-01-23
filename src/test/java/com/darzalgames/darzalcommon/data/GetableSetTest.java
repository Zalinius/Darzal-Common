package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GetableSetTest {

	@Test
	void get_whenObjectInSet_returnsTheObject() {
		GetableSetDecorator<String> set = GetableSetDecorator.ofHashingType();

		set.add("a");

		assertEquals("a", set.get("a"));
	}

	@Test
	void get_whenObjectNotInSet_returnsNull() {
		GetableSetDecorator<String> set = GetableSetDecorator.ofHashingType();

		assertEquals(null, set.get("a"));
	}

}
