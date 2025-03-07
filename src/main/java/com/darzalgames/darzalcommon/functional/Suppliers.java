package com.darzalgames.darzalcommon.functional;

import java.util.function.Supplier;

public class Suppliers {
	private Suppliers() {}
	
	/**
	 * @return A supplier which supplies the empty string.
	 */
	public static Supplier<String> emptyString() {
		return () -> "";
	}
	
}
