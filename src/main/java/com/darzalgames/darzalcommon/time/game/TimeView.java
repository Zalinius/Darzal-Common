package com.darzalgames.darzalcommon.time.game;


public interface TimeView {
	public void register(TimeListener listener);
	public Time getCurrentTime();
}
