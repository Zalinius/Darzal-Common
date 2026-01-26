package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TypeNameSetTest {

	@Test
	void add_elementsOfSameType_retainsOnlyFirstElement() {
		TypeNameSet<String> set = new TypeNameSet<>();

		set.add("a");
		set.add("b");
		set.add("c");

		assertEquals(1, set.size());
		assertEquals("a", set.iterator().next());
	}

	@Test
	void contains_whenElementOfATypeAdded_containsAllElementsOfThatType() {
		TypeNameSet<String> set = new TypeNameSet<>();

		set.add("a");

		assertTrue(set.contains("a"));
		assertTrue(set.contains("b"));
		assertTrue(set.contains("c"));
	}

}
