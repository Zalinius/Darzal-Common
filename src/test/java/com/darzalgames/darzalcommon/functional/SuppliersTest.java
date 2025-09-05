package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

class SuppliersTest {

	@Test
	void emptyString_get_returnsEmptyStringEvenAfterMultipleCalls() {
		Supplier<String> supplier = Suppliers.emptyString();
		assertEquals("", supplier.get());
		assertEquals("", supplier.get());
		assertEquals("", supplier.get());
	}

}
