package com.darzalgames.darzalcommon.functional;

import java.util.function.Supplier;

/**
 * A class for convenience suppliers
 */
public class Suppliers {
	private Suppliers() {}

	/**
	 * An empty string supplier
	 * @return A supplier which supplies the empty string.
	 */
	public static Supplier<String> emptyString() {
		return () -> "";
	}

}
