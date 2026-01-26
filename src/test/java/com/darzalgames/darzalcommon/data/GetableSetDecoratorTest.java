package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

class GetableSetDecoratorTest {

	@Test
	void get_whenObjectInSet_returnsTheObject() {
		GetableSetDecorator<String> set = GetableSetDecorator.ofHashingType();

		set.add("a");

		assertTrue(set.contains("a"));
		assertEquals("a", set.get("a"));
	}

	@Test
	void get_whenObjectNotInSet_returnsNull() {
		GetableSetDecorator<String> set = GetableSetDecorator.ofHashingType();

		assertFalse(set.contains("a"));
		assertEquals(null, set.get("a"));
	}

	@Test
	void ofTypeNameType_createsGetableSetThatDeterminesUniquenessBasedOnType() {
		GetableSet<Object> set = GetableSetDecorator.ofTypeNameType();

		set.add(5);
		set.add(5.0f);
		set.add("five");
		set.add(4.0f);

		assertEquals(5, set.get(0));
		assertEquals(5.0f, set.get(0f));
		assertEquals("five", set.get("zero"));
	}

	@Test
	void addAll_addsAllElementsToGetableSet() {
		GetableSet<String> set = GetableSetDecorator.ofHashingType();

		set.addAll(List.of("apple", "pear", "apple", "orange"));

		assertEquals(3, set.size());
		assertFalse(set.isEmpty());
		assertTrue(set.containsAll(List.of("orange", "pear", "apple")));
	}

	@Test
	void clear_removesAllElementsFromGetableSet() {
		GetableSet<String> set = GetableSetDecorator.ofHashingType();
		set.addAll(List.of("apple", "pear", "apple", "orange"));

		set.clear();

		assertEquals(0, set.size());
		assertTrue(set.isEmpty());
	}

	@Test
	void iterator_returnsIteratorOverElementsOfSet() {
		GetableSet<String> set = GetableSetDecorator.ofHashingType();
		set.addAll(List.of("apple", "pear", "apple", "orange"));

		Set<String> iteratedElements = new HashSet<>();
		Iterator<String> it = set.iterator();

		assertTrue(it.hasNext());
		iteratedElements.add(it.next());
		assertTrue(it.hasNext());
		iteratedElements.add(it.next());
		assertTrue(it.hasNext());
		iteratedElements.add(it.next());
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> it.next());
		assertEquals(3, iteratedElements.size());
		assertTrue(iteratedElements.contains("apple"));
		assertTrue(iteratedElements.contains("pear"));
		assertTrue(iteratedElements.contains("orange"));
	}

	@Test
	void remove_whenElementsNotInGetableSet_hasNoEffectAndReturnsFalse() {
		GetableSet<String> set = GetableSetDecorator.ofHashingType();
		set.addAll(List.of("apple", "pear", "orange"));

		boolean removed = set.remove("banana");

		assertFalse(removed);
		assertEquals(3, set.size());
	}

	@Test
	void remove_whenElementsInnetableSet_removesElementsAndReturnsTrue() {
		GetableSet<String> set = GetableSetDecorator.ofHashingType();
		set.addAll(List.of("apple", "pear", "orange"));

		boolean removed = set.remove("apple");

		assertTrue(removed);
		assertEquals(2, set.size());
		assertEquals(null, set.get("apple"));
	}

	@Test
	void removeAll_whenSomeElementsArePresentInSet_removesThoseElementsAndReturnsTrue() {
		GetableSet<String> set = GetableSetDecorator.ofHashingType();
		set.addAll(List.of("apple", "pear", "orange"));

		boolean setChanged = set.removeAll(List.of("apple", "pear", "banana"));

		assertTrue(setChanged);
		assertEquals(1, set.size());
		assertEquals("orange", set.get("orange"));
	}

	@Test
	void retainAll_removesElementsNotToBeRetained() {
		GetableSet<String> set = GetableSetDecorator.ofHashingType();
		set.addAll(List.of("apple", "pear", "orange"));

		boolean setChanged = set.retainAll(List.of("apple", "pear", "banana"));

		assertEquals(2, set.size());
		assertTrue(setChanged);
		assertFalse(set.contains("orange"));
	}

	@Test
	void equals_onTwoIdenticalGetableSets_returnsTrue() {
		GetableSet<String> set1 = GetableSetDecorator.ofHashingType();
		set1.addAll(List.of("apple", "pear", "apple", "orange"));

		GetableSet<String> set2 = GetableSetDecorator.ofHashingType();
		set2.addAll(List.of("apple", "pear", "pear", "orange"));

		assertEquals(set1, set2);
		assertEquals(set1.hashCode(), set2.hashCode());
	}

	@Test
	void equals_onTwoDifferentSetTypesWithIdenticalContents_returnsTrue() {
		Set<String> set1 = GetableSetDecorator.ofHashingType();
		set1.addAll(List.of("apple", "pear", "apple", "orange"));
		Set<String> set2 = new HashSet<>(List.of("apple", "pear", "pear", "orange"));

		assertEquals(set1, set2);
		assertEquals(set1.hashCode(), set2.hashCode());
	}

}
