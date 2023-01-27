package com.zalinius.darzalcommon.networking.connection.nonaddressed;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface DataChannel {
	public boolean isDataAvailable() throws IOException ;
	public ByteBuffer peek() throws IOException ;
	public ByteBuffer receive() throws IOException;
	public void send(ByteBuffer data) throws IOException;
}
