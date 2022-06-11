package com.zalinius.darzalcommon.functional;

import java.util.stream.IntStream;

public class Do {
	
	public static void xTimes(int x, Runnable action) {
		IntStream.range(0, x).forEach(i -> action.run());
	}
}
