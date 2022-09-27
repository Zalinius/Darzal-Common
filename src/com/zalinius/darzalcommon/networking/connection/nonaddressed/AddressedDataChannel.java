package com.zalinius.darzalcommon.networking.connection.nonaddressed;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

import com.zalinius.darzalcommon.networking.connection.ConnectionBuilder;
import com.zalinius.darzalcommon.networking.connection.addressed.BufferedDatagramChannel;
import com.zalinius.darzalcommon.networking.connection.addressed.DatagramParcel;

public class AddressedDataChannel implements DataChannel{

	private final SocketAddress destinationAddress;
	private final BufferedDatagramChannel innerChannel;

	public AddressedDataChannel(SocketAddress destinationAddress) throws IOException {
		this(destinationAddress, ConnectionBuilder.buildBufferedDatagramChannel());
	}

	public AddressedDataChannel(SocketAddress destinationAddress, BufferedDatagramChannel innerChannel) {
		this.destinationAddress = destinationAddress;
		this.innerChannel = innerChannel;
	}

	@Override
	public void send(ByteBuffer src) throws IOException {
		innerChannel.send(new DatagramParcel(destinationAddress, src));
	}

	@Override
	public ByteBuffer receive() throws IOException {
		return innerChannel.receive().byteBuffer();

	}

	@Override
	public ByteBuffer peek() throws IOException {
		return innerChannel.peek().byteBuffer();
	}

	@Override
	public boolean isDataAvailable() throws IOException {
		return innerChannel.isPacketAvailable();
	}

	public SocketAddress getDestinationAddress() {
		return destinationAddress;
	}
}
