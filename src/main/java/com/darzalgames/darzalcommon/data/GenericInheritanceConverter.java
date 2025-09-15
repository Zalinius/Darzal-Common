package com.darzalgames.darzalcommon.data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Conversion functions for upcasting generic collections
 */
public class GenericInheritanceConverter {

	private GenericInheritanceConverter() {}

	/**
	 * Converts a list supplier to be it's parent type in a typesafe manner
	 * @param <T>               The child type
	 * @param <U>               The parent type
	 * @param supplierToConvert the list supplier to convert
	 * @return a new Arraylist supplier with the parent type as it's static type
	 */
	public static <T, U extends T> Supplier<List<T>> convertSupplierOfList(Supplier<List<U>> supplierToConvert) {
		List<U> original = supplierToConvert.get();
		return () -> convertList(original);
	}

	/**
	 * Converts a list to be it's parent type in a typesafe manner
	 * @param <T>           The child type
	 * @param <U>           The parent type
	 * @param listToConvert the list to convert
	 * @return a new Arraylist with the parent type as it's static type
	 */
	public static <T, U extends T> List<T> convertList(List<U> listToConvert) {
		return new ArrayList<>(listToConvert);
	}
}
