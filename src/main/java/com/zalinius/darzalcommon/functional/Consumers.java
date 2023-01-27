package com.zalinius.darzalcommon.functional;

import java.util.function.Consumer;

public class Consumers {
	private Consumers() {}
	
	public static <E> Consumer<E> nullConsumer(){
		return e -> {};
	}
}
