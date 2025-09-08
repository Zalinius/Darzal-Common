package com.darzalgames.darzalcommon.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

class GenericInheritanceConverterTest {

	@Test
	void convertList_compilesAndConvertsToParentType() {
		List<Integer> ints = Arrays.asList(1, 2, 3);

		List<Number> numbers = GenericInheritanceConverter.convertList(ints);

		assertEquals(ints, numbers);
	}

	@Test
	void convertListSupplier_compilesAndConvertsToParentType() {
		Supplier<List<Integer>> ints = () -> Arrays.asList(1, 2, 3);

		Supplier<List<Number>> numbers = GenericInheritanceConverter.convertSupplierOfList(ints);

		assertEquals(ints.get(), numbers.get());
	}

}
