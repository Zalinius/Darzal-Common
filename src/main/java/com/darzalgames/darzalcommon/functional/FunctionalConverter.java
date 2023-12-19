package com.darzalgames.darzalcommon.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class FunctionalConverter {
	
	private FunctionalConverter() {}

	public static <T, U extends T> Supplier<List<T>> convertSupplierOfList(Supplier<List<U>> supplierToConvert) {
		List<U> original = supplierToConvert.get();
		return () -> convertList(original);
	}
	
	public static <T, U extends T>  List<T> convertList(List<U> listToConvert) {
		List<T> convertedList = new ArrayList<>();
		for (U entry : listToConvert) {
			convertedList.add(entry);
		}
		return convertedList;
	}
}
