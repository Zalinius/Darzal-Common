package com.darzalgames.darzalcommon.networking.connection.addressed;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

public record DatagramParcel(
		SocketAddress socketAddress,
		ByteBuffer byteBuffer)
{}
