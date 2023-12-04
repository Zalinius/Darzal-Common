package com.darzalgames.darzalcommon.testutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import org.junit.jupiter.api.Test;

public class TwoWayFakeDatagramChannelTest {
	
	@Test
	void twoWayFakeDatagramChannel_receiveWithoutSending_returnsNull() throws Exception {
		TwoWayFakeDatagramChannel twoWayFakeDatagramChannel = new TwoWayFakeDatagramChannel();
		DatagramChannel receivingFakeDatagramChannel = twoWayFakeDatagramChannel.getBToAChannel();
		ByteBuffer byteBufferToReceive = ByteBuffer.allocate(4);
		
		SocketAddress sender = receivingFakeDatagramChannel.receive(byteBufferToReceive);
		byteBufferToReceive.flip();
		
		assertNull(sender);
	}

	
	@Test
	void twoWayFakeDatagramChannel_sendAndReceiveByteBuffer_sendsAndReceives() throws Exception {
		TwoWayFakeDatagramChannel twoWayFakeDatagramChannel = new TwoWayFakeDatagramChannel();
		DatagramChannel sendingFakeDatagramChannel = twoWayFakeDatagramChannel.getAToBChannel();
		DatagramChannel receivingFakeDatagramChannel = twoWayFakeDatagramChannel.getBToAChannel();
		ByteBuffer byteBufferToSend = ByteBuffer.wrap(new byte[] {0, 1, 2, 3});
		ByteBuffer byteBufferToReceive = ByteBuffer.allocate(4);
		
		sendingFakeDatagramChannel.send(byteBufferToSend, new InetSocketAddress(twoWayFakeDatagramChannel.getbListeningPort()));		
		SocketAddress sender = receivingFakeDatagramChannel.receive(byteBufferToReceive);
		byteBufferToReceive.flip();
		
		assertEquals(0, byteBufferToReceive.get());
		assertEquals(1, byteBufferToReceive.get());
		assertEquals(2, byteBufferToReceive.get());
		assertEquals(3, byteBufferToReceive.get());	
		assertNotNull(sender);
	}

	@Test
	void fakeDatagramChannel_sendAndReceiveByteBufferWithSocketAddress_sendsAndReceivesAll() throws Exception {
		TwoWayFakeDatagramChannel twoWayFakeDatagramChannel = new TwoWayFakeDatagramChannel(7000, 7002);
		DatagramChannel sendingFakeDatagramChannel = twoWayFakeDatagramChannel.getAToBChannel();
		DatagramChannel receivingFakeDatagramChannel = twoWayFakeDatagramChannel.getBToAChannel();
		ByteBuffer byteBufferToSend = ByteBuffer.wrap(new byte[] {0, 1, 2, 3});
		ByteBuffer byteBufferToReceive = ByteBuffer.allocate(4);
		SocketAddress destination = new InetSocketAddress(7002);
		
		sendingFakeDatagramChannel.send(byteBufferToSend, destination);		
		SocketAddress sender = receivingFakeDatagramChannel.receive(byteBufferToReceive);
		byteBufferToReceive.flip();
		
		assertEquals(0, byteBufferToReceive.get());
		assertEquals(1, byteBufferToReceive.get());
		assertEquals(2, byteBufferToReceive.get());
		assertEquals(3, byteBufferToReceive.get());	
		
		assertEquals(destination, sender);
	}

	@Test
	void fakeDatagramChannel_sendAndReceiveWrongPort_sendsButReceivesNull() throws Exception {
		TwoWayFakeDatagramChannel twoWayFakeDatagramChannel = new TwoWayFakeDatagramChannel(7000, 7002);
		DatagramChannel sendingFakeDatagramChannel = twoWayFakeDatagramChannel.getAToBChannel();
		DatagramChannel receivingFakeDatagramChannel = twoWayFakeDatagramChannel.getBToAChannel();
		ByteBuffer byteBufferToSend = ByteBuffer.wrap(new byte[] {0, 1, 2, 3});
		ByteBuffer byteBufferToReceive = ByteBuffer.allocate(4);
		SocketAddress destination = new InetSocketAddress(4000);
		
		sendingFakeDatagramChannel.send(byteBufferToSend, destination);		
		SocketAddress sender = receivingFakeDatagramChannel.receive(byteBufferToReceive);
		byteBufferToReceive.flip();
				
		assertNull(sender);
	}

}
