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

	@Test
	void flattenListOfList_onNestedLists_flattensLists() {
		List<List<Integer>> ints = List.of(List.of(0,1,2), List.of(3,4,5));

		List<Integer> flattened = ListFactory.flattenListOfLists(ints);

		assertEquals(6, flattened.size());
		assertEquals(0, flattened.get(0));
		assertEquals(1, flattened.get(1));
		assertEquals(2, flattened.get(2));
		assertEquals(3, flattened.get(3));
		assertEquals(4, flattened.get(4));
		assertEquals(5, flattened.get(5));
	}

	@Test
	void flattenListOfList_onNestedLists_returnsModifiableList() {
		List<List<Integer>> ints = List.of(List.of(0,1,2), List.of(3,4,5));

		List<Integer> flattened = ListFactory.flattenListOfLists(ints);

		assertDoesNotThrow(() -> flattened.add(6));
		assertEquals(7, flattened.size());
		assertEquals(6, flattened.get(6));
	}

}
