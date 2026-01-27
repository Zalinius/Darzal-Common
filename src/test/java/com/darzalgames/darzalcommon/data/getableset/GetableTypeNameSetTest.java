package com.darzalgames.darzalcommon.data.getableset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class GetableTypeNameSetTest {

	@Test
	void add_withObjectsOfSameTypes_retainsOnlyObjectsOfUniqueTypes() {
		GetableSet<Object> set = new GetableTypeNameSet<>();

		set.add(5);
		set.add(5.0f);
		set.add("five");
		set.add(4.0f);

		assertEquals(5, set.get(0));
		assertEquals(5.0f, set.get(0f));
		assertEquals("five", set.get("zero"));
	}

	@Test
	void addAll_onTypeNameTypeWithNoNewElementsToAdd_leavesSetUnchangedAndReturnsFalse() {
		GetableSet<Object> set = new GetableTypeNameSet<>();
		set.add("kiwi");

		boolean setChanged = set.addAll(List.of("apple", "pear", "apple", "orange"));

		assertFalse(setChanged);
		assertEquals(1, set.size());
		assertFalse(set.isEmpty());
		assertTrue(set.contains("kiwi"));
	}

}
