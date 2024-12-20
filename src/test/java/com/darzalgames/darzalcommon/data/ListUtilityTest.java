package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ListUtilityTest {

	@Test
	void getClosestPairIndex_withOddSizedList_returnsCorrectValues() {
		List<Integer> values = new ArrayList<>();
		values.add(0);
		values.add(1);
		values.add(2);
		values.add(3);
		values.add(4);

		assertEquals(0, ListUtility.getClosestPairIndex(values, 1));
		assertEquals(1, ListUtility.getClosestPairIndex(values, 0));
		assertEquals(2, ListUtility.getClosestPairIndex(values, 2));
		assertEquals(3, ListUtility.getClosestPairIndex(values, 4));
		assertEquals(4, ListUtility.getClosestPairIndex(values, 3));
	}
	
	@Test
	void getClosestPairIndex_withEvenSizedList_returnsCorrectValues() {
		List<Integer> values = new ArrayList<>();
		values.add(0);
		values.add(1);
		values.add(2);
		values.add(3);

		assertEquals(0, ListUtility.getClosestPairIndex(values, 1));
		assertEquals(1, ListUtility.getClosestPairIndex(values, 0));
		assertEquals(2, ListUtility.getClosestPairIndex(values, 3));
		assertEquals(3, ListUtility.getClosestPairIndex(values, 2));
	}
	
	@Test
	void getClosestPairIndex_withOneSizedList_returnsSelf() {
		List<Integer> values = new ArrayList<>();
		values.add(2);

		assertEquals(0, ListUtility.getClosestPairIndex(values, 0));
		assertEquals(2, values.get(ListUtility.getClosestPairIndex(values, 0)));
	}
	
	@Test
	void getClosestPairIndex_withTwoSizedList_returnsCorrectValues() {
		List<Integer> values = new ArrayList<>();
		values.add(0);
		values.add(1);

		assertEquals(0, ListUtility.getClosestPairIndex(values, 1));
		assertEquals(1, ListUtility.getClosestPairIndex(values, 0));
	}
	
	@Test
	void getClosestPairIndex_withThreeSizedList_returnsCorrectValues() {
		List<Integer> values = new ArrayList<>();
		values.add(0);
		values.add(1);
		values.add(2);

		assertEquals(1, ListUtility.getClosestPairIndex(values, 1));
		assertEquals(0, ListUtility.getClosestPairIndex(values, 2));
		assertEquals(2, ListUtility.getClosestPairIndex(values, 0));
	}
	
	@Test
	void getClosestPairIndex_withZeroSizedList_returnsNegativeOne() {
		List<Integer> values = new ArrayList<>();

		assertEquals(-1, ListUtility.getClosestPairIndex(values, 0));
	}
}
