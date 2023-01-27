package com.zalinius.darzalcommon.testutils;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;

public class TwoWayFakeDatagramChannel {
	
	private final int aListeningPort;
	private final int bListeningPort;
	
	private final Queue<FakePacket> aToBPacketQueue;
	private final Queue<FakePacket> bToApacketQueue;
	
	private record FakePacket(SocketAddress socketAddress, ByteBuffer byteBuffer) {}

	public TwoWayFakeDatagramChannel() {
		this(7000, 7001);
	}
	
	public int getaListeningPort() {
		return aListeningPort;
	}
	public int getbListeningPort() {
		return bListeningPort;
	}

	public TwoWayFakeDatagramChannel(int aListeningPort, int bListeningPort) {
		this.aListeningPort = aListeningPort;
		this.bListeningPort = bListeningPort;
		this.aToBPacketQueue = new ArrayDeque<>();
		this.bToApacketQueue = new ArrayDeque<>();
	}
	
	public FakeDatagramChannel getAToBChannel() {
		return new FakeDatagramChannel(aToBPacketQueue, bToApacketQueue, bListeningPort);
	}
	
	public FakeDatagramChannel getBToAChannel() {
		return new FakeDatagramChannel(bToApacketQueue, aToBPacketQueue, aListeningPort);
	}
	

	public static class FakeDatagramChannel extends DatagramChannel{

		private final Queue<FakePacket> sendingQueue;
		private final Queue<FakePacket> receivingQueue;
		private final int listeningPort;
		
		protected FakeDatagramChannel(Queue<FakePacket> sendingQueue, Queue<FakePacket> receivingQueue, int listeningPort) {
			super(null);
			this.sendingQueue = sendingQueue;
			this.receivingQueue = receivingQueue;
			this.listeningPort = listeningPort;
		}
		
		public void dropNextReceivedPacket() {
			receivingQueue.poll();
		}

		@Override
		public MembershipKey join(InetAddress group, NetworkInterface interf) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public MembershipKey join(InetAddress group, NetworkInterface interf, InetAddress source) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public <T> T getOption(SocketOption<T> name) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public Set<SocketOption<?>> supportedOptions() {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public DatagramChannel bind(SocketAddress local) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public <T> DatagramChannel setOption(SocketOption<T> name, T value) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public DatagramSocket socket() {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public boolean isConnected() {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public DatagramChannel connect(SocketAddress remote) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public DatagramChannel disconnect() throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public SocketAddress getRemoteAddress() throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public SocketAddress receive(ByteBuffer dst) throws IOException {
			if(receivingQueue.isEmpty()) {
				return null;
			}
			else {
				dst.clear();
				FakePacket fakePacket = receivingQueue.poll();
				dst.put(fakePacket.byteBuffer());
				return fakePacket.socketAddress();
			}
		}

		@Override
		public int send(ByteBuffer src, SocketAddress target) throws IOException {
			if(target == null) {
				throw new NullPointerException();
			}
			if(((InetSocketAddress)target).getPort() == listeningPort) {
				sendingQueue.add(new FakePacket(target, src));
			}
			return src.limit();				
		}

		@Override
		public int read(ByteBuffer dst) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public int write(ByteBuffer src) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		public SocketAddress getLocalAddress() throws IOException {
			throw new RuntimeException("Not Implemented");
		}

		@Override
		protected void implCloseSelectableChannel() throws IOException {
			//do nothing, there's nothing to close
		}

		@Override
		protected void implConfigureBlocking(boolean block) throws IOException {
			throw new RuntimeException("Not Implemented");
		}

	}

	
}
