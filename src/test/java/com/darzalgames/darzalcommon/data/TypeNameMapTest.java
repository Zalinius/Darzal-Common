package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

class TypeNameMapTest {

	@Test
	void constructor_constructsEmptyMap() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
	}

	@Test
	void put_onEmptyMap_putsValueAndReturnsNull() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		String previousValue = map.put(5, "five");

		assertNull(previousValue);
		assertEquals("five", map.get(5));
		assertEquals(1, map.size());
		assertFalse(map.isEmpty());
	}

	@Test
	void put_multipleValueOfDifferentTypes_noKeyCollisions() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		String previousValue1 = map.put(5, "five");
		String previousValue2 = map.put(5.0, "five.oh");

		assertNull(previousValue1);
		assertNull(previousValue2);
		assertEquals("five", map.get(5));
		assertEquals("five.oh", map.get(5.0));
		assertEquals(2, map.size());
	}

	@Test
	void put_multipleValuesOfSameType_replacesValue() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		String previousValue1 = map.put(5, "five");
		String previousValue2 = map.put(6, "six");

		assertNull(previousValue1);
		assertEquals("five", previousValue2);
		assertEquals("six", map.get(5));
		assertEquals("six", map.get(6));
		assertEquals(1, map.size());
	}

	@Test
	void get_forKeysOfSameType_returnsSameValue() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		map.put(5, "five");
		map.put(5.0, "five.oh");

		assertEquals("five", map.get(-28));
		assertEquals("five", map.get(5));
		assertEquals("five", map.get(Integer.MAX_VALUE));
		assertEquals("five.oh", map.get(5.0));
		assertEquals("five.oh", map.get(-25.0));
		assertEquals("five.oh", map.get(Double.NaN));
	}

	@Test
	void containsKey_forKeysOfSameType_returnsTrue() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		map.put(5, "five");

		assertTrue(map.containsKey(4));
		assertTrue(map.containsKey(5));
		assertTrue(map.containsKey(7));
		assertFalse(map.containsKey(4.0));
		assertFalse(map.containsKey(5.0));
		assertFalse(map.containsKey(6.0));
	}

	@Test
	void containsValue_returnsTrueOnlyIfValueMatches() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		map.put(5, "five");

		assertTrue(map.containsValue("five"));
		assertFalse(map.containsValue("four"));
		assertFalse(map.containsValue("six"));
		assertFalse(map.containsValue(null));
	}

	@Test
	void clear_emptiesMap() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		map.put(5, "five");
		map.clear();

		assertTrue(map.isEmpty());
		assertEquals(0, map.size());
		assertFalse(map.containsKey(5));
	}

	@Test
	void remove_forKeysOfSameType_removesValue() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		map.put(5, "five");
		map.put(5.0, "five.oh");
		String value = map.remove(4);

		assertEquals("five", value);
		assertFalse(map.containsKey(5));
	}

	@Test
	void values_containsInsertedValues() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		map.put(4, "four");
		map.put(5, "five");
		map.put(5.0, "five.oh");
		Collection<String> values = map.values();

		assertEquals(2, values.size());
		assertTrue(values.contains("five"));
		assertTrue(values.contains("five.oh"));
	}

	@Test
	void toString_containsInsertedKeysTypesAndInsertedValues() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		map.put(4, "four");
		map.put(5.0, "five.oh");
		String toStringString = map.toString();

		assertFalse(toStringString.contains("4"));
		assertFalse(toStringString.contains("5.0"));
		assertTrue(toStringString.contains("Integer"));
		assertTrue(toStringString.contains("four"));
		assertTrue(toStringString.contains("Double"));
		assertTrue(toStringString.contains("five.oh"));
	}

	@Test
	void keySet_withPuts_returnsOriginalKeys() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		map.put(4, "four");
		map.put(5.0, "five.oh");
		Set<Number> keySet = map.keySet();

		assertEquals(2, keySet.size());
		assertTrue(keySet.contains(4));
		assertTrue(keySet.contains(5.0));
	}

	@Test
	void keySet_withPutsRemovesAndCollisions_returnsNewestKeys() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		map.put(4, "four");
		map.put(5.0, "five.oh");
		map.put(5, "five");
		map.put(6.0, "six.oh");
		map.put(7f, "sevenFloat");
		map.remove(8f);
		Set<Number> keySet = map.keySet();

		assertEquals(2, keySet.size());
		assertTrue(keySet.contains(5));
		assertTrue(keySet.contains(6.0));
	}

	@Test
	void entries_withPutsRemovesAndCollisions_returnsNewestEntryPairs() {
		TypeNameMap<Number, String> map = new TypeNameMap<>();

		map.put(4, "four");
		map.put(5.0, "five.oh");
		map.put(5, "five");
		map.put(6.0, "six.oh");
		map.put(7f, "sevenFloat");
		map.remove(8f);

		Set<Entry<Number, String>> entrySet = map.entrySet();

		assertEquals(2, entrySet.size());
		assertTrue(entrySet.contains(new AbstractMap.SimpleEntry<>(5, "five")));
		assertTrue(entrySet.contains(new AbstractMap.SimpleEntry<>(6.0, "six.oh")));
	}

	@Test
	void putAll_withNormalMapHavingTypeCollisions_createsSmallerTypeMap() {
		Map<Number, String> map = new HashMap<>();
		map.put(4, "four");
		map.put(5.0, "five.oh");
		map.put(5, "five");
		map.put(6.0, "six.oh");
		map.put(7f, "sevenFloat");
		TypeNameMap<Number, String> typeMap = new TypeNameMap<>();

		typeMap.putAll(map);

		assertEquals(3, typeMap.size());
	}

}