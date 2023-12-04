package com.darzalgames.darzalcommon.networking.connection.addressed;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.NoSuchElementException;

/**
 * Convenience wrapper for a DatagramChannel, to simplify non-blocking receiving
 * Designed with a fixed datagram size in mind.
 * @author Zalinius
 */
public class BufferedDatagramChannel{

	private final DatagramChannel datagramChannel;

	private DatagramParcel  datagramPackageBuffer;

	public BufferedDatagramChannel(DatagramChannel datagramChannel) {
		this.datagramChannel = datagramChannel;
		this.datagramPackageBuffer = null;
	}

	public boolean isPacketAvailable() throws IOException {
		if(datagramPackageBuffer != null) {
			return true;
		}

		ByteBuffer receivingBuffer = makeByteBuffer();
		SocketAddress remoteAdd = null;
		remoteAdd = datagramChannel.receive(receivingBuffer);

		if(remoteAdd != null) {
			receivingBuffer.flip();
			datagramPackageBuffer = new DatagramParcel(remoteAdd, receivingBuffer);

			return true;
		}
		else {
			return false;
		}
	}
	
	public DatagramParcel peek() throws IOException {
		if(!isPacketAvailable()) {
			throw new NoSuchElementException();
		}
		else {
			return datagramPackageBuffer;
		}
	}

	public DatagramParcel receive() throws IOException {
		if(!isPacketAvailable()) {
			throw new NoSuchElementException();
		}
		else {
			DatagramParcel datagramPackageToReturn = datagramPackageBuffer;
			datagramPackageBuffer = null;
			return datagramPackageToReturn;
		}
	}

	public void send(DatagramParcel datagramPackage) throws IOException {
		send(datagramPackage.byteBuffer(), datagramPackage.socketAddress());
	}

	public void send(ByteBuffer src, SocketAddress target) throws IOException {
		datagramChannel.send(src, target);
	}



	private ByteBuffer makeByteBuffer() {
		return ByteBuffer.allocate(1024); // TODO figure this out, How do I know what size to put here? Can it be too big for the sending or receiving DatagramChannel?
	}


}
