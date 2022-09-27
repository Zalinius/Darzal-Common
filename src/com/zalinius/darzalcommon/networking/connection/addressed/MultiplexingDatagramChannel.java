package com.zalinius.darzalcommon.networking.connection.addressed;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

import com.zalinius.darzalcommon.networking.connection.nonaddressed.DataChannel;

public class MultiplexingDatagramChannel {
	
	private final Map<SocketAddress, Queue<DatagramParcel>> receivingQueues;
	private final BufferedDatagramChannel innerChannel;
	
	public MultiplexingDatagramChannel(BufferedDatagramChannel innerChannel) {
		this.receivingQueues = new HashMap<>();
		this.innerChannel = innerChannel;
	}
	
	public Set<SocketAddress> allConnections(){
		try {
			demuxAllPendingDatagrams();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return receivingQueues.keySet();
	}
	
	public boolean isPacketAvailable(SocketAddress socketAddress) throws IOException {
		demuxAllPendingDatagrams();
		if(receivingQueues.containsKey(socketAddress)) {
			return !receivingQueues.get(socketAddress).isEmpty();
		}
		else {
			return false;
		}
	}
	
	public DatagramParcel peek(SocketAddress socketAddress) throws IOException {
		if(!isPacketAvailable(socketAddress)) {
			throw new NoSuchElementException();
		}
		else {
			return receivingQueues.get(socketAddress).peek();			
		}
	}

	public DatagramParcel receive(SocketAddress socketAddress) throws IOException {
		if(!isPacketAvailable(socketAddress)) {
			throw new NoSuchElementException();
		}
		else {
			return receivingQueues.get(socketAddress).remove();
		}
	}
	
	public void send(DatagramParcel datagramPackage) throws IOException {
		innerChannel.send(datagramPackage);
	}
	
	
	private void demuxAllPendingDatagrams() throws IOException {
		while (innerChannel.isPacketAvailable()) {
			DatagramParcel datagramParcel = innerChannel.receive();
			SocketAddress address = datagramParcel.socketAddress();
			receivingQueues.putIfAbsent(address, new LinkedList<>());
			
			receivingQueues.get(address).add(datagramParcel);
		}
	}
	
	public DataChannel getDataChannel(SocketAddress socketAddress) {
		if(receivingQueues.containsKey(socketAddress)) {
			return new DataChannel() {
				@Override
				public void send(ByteBuffer src) throws IOException {
					MultiplexingDatagramChannel.this.send(new DatagramParcel(socketAddress, src));
				}
				
				@Override
				public ByteBuffer receive() throws IOException {
					return MultiplexingDatagramChannel.this.receive(socketAddress).byteBuffer();

				}
				
				@Override
				public ByteBuffer peek() throws IOException {
					return MultiplexingDatagramChannel.this.peek(socketAddress).byteBuffer();
				}
				
				@Override
				public boolean isDataAvailable() throws IOException {
					return MultiplexingDatagramChannel.this.isPacketAvailable(socketAddress);
				}
			};
		}
		else {
			throw new NoSuchElementException();
		}
	}

}
