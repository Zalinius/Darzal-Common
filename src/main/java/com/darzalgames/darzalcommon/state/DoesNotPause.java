package com.darzalgames.darzalcommon.state;

/**
 * An interface for objects that should keep acting while a game is paused
 */
public interface DoesNotPause {
	/**
	 * @param delta The time delta normally passed to act, in seconds
	 */
	public void actWhilePaused(float delta);
}
