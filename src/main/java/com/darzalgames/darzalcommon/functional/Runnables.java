package com.darzalgames.darzalcommon.functional;

/**
 * A class for convenience runnables
 */
public class Runnables {
	private Runnables() {}

	/**
	 * Creates a null-effect runnable
	 * @return A runnable which has no effect when run.
	 */
	public static Runnable nullRunnable() {
		return () -> {};
	}

}
