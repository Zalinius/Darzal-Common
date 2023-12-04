package com.darzalgames.darzalcommon.functional;

public class Runnables {
	private Runnables(){}
	
	public static Runnable nullRunnable() {
		return () -> {};
	}

}
