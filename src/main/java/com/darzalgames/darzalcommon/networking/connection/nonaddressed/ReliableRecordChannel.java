package com.darzalgames.darzalcommon.networking.connection.nonaddressed;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;

import com.darzalgames.darzalcommon.networking.data.*;

/**
 * A wrapper class for the Datagram channel's, which provides features useful for real time games:
 * Serialization and deserialization via Java records,
 * Protocol ID,
 * Redundant acknowledgments,
 * No Head of Line Blocking,
 * Congestion Control,
 * 
 * @author Zalinius
 *
 * @param <E> 
 */
public class ReliableRecordChannel<E> {

	private final GameNetworkingProtocolIdentifier masterProtocolID;
	private final DataChannel dataChannel;

	private int localSequenceNumber;
	private int remoteSequenceNumber;
	private PackAckQueue receivedPacketAckQueue;
	private PackAckQueue sentPacketAckQueue;
	private RttTracker rttTracker;

	private final BinaryCongestionStrategy congestionStrategy;

	public ReliableRecordChannel(GameNetworkingProtocolIdentifier masterProtocolID, DataChannel dataChannel) {
		this.masterProtocolID = masterProtocolID;
		this.dataChannel = dataChannel;		

		this.localSequenceNumber = 0;	
		this.remoteSequenceNumber = -1;
		this.rttTracker = new RttTracker();
		this.receivedPacketAckQueue = new PackAckQueue();
		this.sentPacketAckQueue = new PackAckQueue(rttTracker::sentPacketWasAcknowledged);

		this.congestionStrategy = new BinaryCongestionStrategy(rttTracker::getCurrentAverageRTT);
	}

	public void update(double delta) {
		rttTracker.update(delta);
		congestionStrategy.update(delta);
	}

	public boolean isSendingAllowed() {
		return congestionStrategy.isSendingAllowed();
	}

	public void sendData(E recordData) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

		objectOutputStream.writeObject(recordData);
		byte[] payloadBytesToSend = byteArrayOutputStream.toByteArray();
		ByteBuffer headerBytesToSend = new PacketHeader(masterProtocolID.protocolID(), localSequenceNumber, remoteSequenceNumber, receivedPacketAckQueue.createAckFromPackets()).toByteBuffer();
		rttTracker.packetWasSent(localSequenceNumber);
		localSequenceNumber++;

		ByteBuffer byteBufferToSend = ByteBuffer.allocate(PacketHeader.strictByteSize() + payloadBytesToSend.length);
		byteBufferToSend.put(headerBytesToSend);
		byteBufferToSend.put(payloadBytesToSend);
		byteBufferToSend.flip();

		dataChannel.send(byteBufferToSend);
		congestionStrategy.sent();
	}

	public boolean isDataAvailable() throws IOException {
		boolean nextDataHasCorrectProtocolIDAndSequenceNumberFromFuture = false;

		//exhaust packets with incorrectID and that aren't new
		while (!nextDataHasCorrectProtocolIDAndSequenceNumberFromFuture && dataChannel.isDataAvailable()) {
			ByteBuffer potentialData = dataChannel.peek();
			if(potentialData.limit() >= PacketHeader.strictByteSize()) {
				PacketHeader packetHeader = PacketHeader.constructFromByteBuffer(potentialData);
				potentialData.position(0);

				if(packetHeader.protocolID() == masterProtocolID.protocolID()) {
					if(packetHeader.sequenceNumber() > remoteSequenceNumber) {
						nextDataHasCorrectProtocolIDAndSequenceNumberFromFuture = true;
					}
					else {
						dataChannel.receive(); //Receive and discard packet with old sequence number
						//However, we still acknowledge it
						receivedPacketAckQueue.acknowledgePacket(packetHeader.sequenceNumber());
						sentPacketAckQueue.acknowledgePackets(packetHeader.acknowledgement(), packetHeader.acknowledgementBitField());						
					}
				}
				else {
					dataChannel.receive(); //Receive and discard packet with incorrect protocol ID
				}
			}
			else {
				dataChannel.receive(); //Receive and discard packet with fewer bytes than packet header
			}
		}

		return dataChannel.isDataAvailable();
	}

	public E receiveData() throws IOException, ClassNotFoundException {
		if(!isDataAvailable()) {
			throw new NoSuchElementException();
		}

		ByteBuffer receivedData = dataChannel.receive();
		PacketHeader packetHeader = PacketHeader.constructFromByteBuffer(receivedData);
		remoteSequenceNumber = Math.max(remoteSequenceNumber, packetHeader.sequenceNumber());
		receivedPacketAckQueue.acknowledgePacket(packetHeader.sequenceNumber());
		sentPacketAckQueue.acknowledgePackets(packetHeader.acknowledgement(), packetHeader.acknowledgementBitField());
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receivedData.array(), PacketHeader.strictByteSize(), receivedData.limit() - PacketHeader.strictByteSize());

		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		return (E) objectInputStream.readObject();
	}

	/**
	 * @return The next sequence number to send
	 */
	public int getLocalSequenceNumber() {
		return localSequenceNumber;
	}
	/**
	 * @return The most advanced sequence number received
	 */
	public int getRemoteSequenceNumber() {
		return remoteSequenceNumber;
	}

	public double rttTime() {
		return rttTracker.getCurrentAverageRTT();
	}
	public double sentPacketLoss() {
		return sentPacketAckQueue.nonAckPercentage();
	}

}
