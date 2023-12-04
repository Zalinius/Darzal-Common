package com.darzalgames.darzalcommon.networking.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

import com.darzalgames.darzalcommon.networking.connection.addressed.BufferedDatagramChannel;

public class ConnectionBuilder {

	private ConnectionBuilder() {}

	/**
	 * @return a BufferedDatagramChannel (UDP) with the specified host and port.
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static BufferedDatagramChannel buildBufferedDatagramChannel(SocketAddress address) throws IOException {
		return new BufferedDatagramChannel(buildDatagramChannel(address));
	}

	/**
	 * @return a BufferedDatagramChannel (UDP) on the local machine, with the specified port and the wildcard host.
	 * @throws IOException
	 */
	public static BufferedDatagramChannel buildBufferedDatagramChannel(int port) throws IOException {
		SocketAddress socketAddress = new InetSocketAddress(port);
		return buildBufferedDatagramChannel(socketAddress);
	}

	/**
	 * @return a BufferedDatagramChannel (UDP) on the local machine, with a randomly assigned port.
	 * @throws IOException
	 */
	public static BufferedDatagramChannel buildBufferedDatagramChannel() throws IOException {
		return buildBufferedDatagramChannel(null);
	}

	@SuppressWarnings("resource")
	private static DatagramChannel buildDatagramChannel(SocketAddress address) throws IOException  {
		DatagramChannel datagramChannel = DatagramChannel.open();
		datagramChannel.bind(address);
		datagramChannel.configureBlocking(false);
		return datagramChannel;
	}

}
