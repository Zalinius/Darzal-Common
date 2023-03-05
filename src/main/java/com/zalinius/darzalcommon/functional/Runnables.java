package com.zalinius.darzalcommon.functional;

public class Runnables {
	private Runnables(){}
	
	public static Runnable nullRunnable() {
		return () -> {};
	}

}
