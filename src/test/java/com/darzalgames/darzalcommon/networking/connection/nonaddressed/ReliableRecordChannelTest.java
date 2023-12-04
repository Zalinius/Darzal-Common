package com.darzalgames.darzalcommon.networking.connection.nonaddressed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.InetSocketAddress;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.networking.connection.addressed.BufferedDatagramChannel;
import com.darzalgames.darzalcommon.networking.data.GameNetworkingProtocolIdentifier;
import com.darzalgames.darzalcommon.testutils.SimpleRecord;
import com.darzalgames.darzalcommon.testutils.TwoWayFakeDatagramChannel;
import com.darzalgames.darzalcommon.testutils.TwoWayFakeDatagramChannel.FakeDatagramChannel;

public class ReliableRecordChannelTest {
	
	private ReliableRecordChannel<SimpleRecord> sender;
	private FakeDatagramChannel senderInnerChannel;
	private static final int SENDER_LISTENING_PORT = 7000;
	
	private ReliableRecordChannel<SimpleRecord> receiver;
	private FakeDatagramChannel receiverInnerChannel;
	private static final int RECEIVER_LISTENING_PORT = 7001;
	
	private static final GameNetworkingProtocolIdentifier CORRECT_PROTOCOL = new GameNetworkingProtocolIdentifier("Test Protocol", 1);
	private static final GameNetworkingProtocolIdentifier INCORRECT_PROTOCOL = new GameNetworkingProtocolIdentifier("Incorrect Protocol", -1);
	
	public void setup(GameNetworkingProtocolIdentifier senderProtocol, GameNetworkingProtocolIdentifier listenerProtocol) {
		TwoWayFakeDatagramChannel twoWayFakeDatagramChannel = new TwoWayFakeDatagramChannel(SENDER_LISTENING_PORT, RECEIVER_LISTENING_PORT);

		senderInnerChannel = twoWayFakeDatagramChannel.getAToBChannel();
		BufferedDatagramChannel senderBufferedDatagramChannel = new BufferedDatagramChannel(senderInnerChannel);
		sender = new ReliableRecordChannel<>(senderProtocol, new AddressedDataChannel(new InetSocketAddress(RECEIVER_LISTENING_PORT), senderBufferedDatagramChannel));
		
		receiverInnerChannel = twoWayFakeDatagramChannel.getBToAChannel();
		BufferedDatagramChannel receiverBufferedDatagramChannel = new BufferedDatagramChannel(receiverInnerChannel);
		receiver = new ReliableRecordChannel<>(listenerProtocol, new AddressedDataChannel(new InetSocketAddress(SENDER_LISTENING_PORT), receiverBufferedDatagramChannel));
	}
	
	public void setupWithSameProtocol() {
		setup(CORRECT_PROTOCOL, CORRECT_PROTOCOL);
	}
	
	public void setupWithDifferentProtocol() {
		setup(CORRECT_PROTOCOL, INCORRECT_PROTOCOL);
	}
	
	@Test
	void sendAndReceive_withRecord_serializesAndDeserializesCorrectly() throws Exception {
		setupWithSameProtocol();
		SimpleRecord record = new SimpleRecord(42);
		
		sender.sendData(record);
		boolean dataAvailable = receiver.isDataAvailable(); 
		SimpleRecord simpleRecord = receiver.receiveData();
		
		assertTrue(dataAvailable);
		assertEquals(record, simpleRecord);		
	}
	
	@Test
	void sendAndReceive_withNonMatchingProtocolID_silentlyDiscardsSentData() throws Exception {
		setupWithDifferentProtocol();
		SimpleRecord record = new SimpleRecord(42);
		
		sender.sendData(record);
		boolean dataAvailable = receiver.isDataAvailable(); 
		
		assertFalse(dataAvailable);
	}	
	
	@Test
	void sendAndReceive_threeMessages_incrementsLocalAndRemoteSequenceNumberCorrectly() throws Exception {
		setupWithSameProtocol();
		SimpleRecord recordA = new SimpleRecord(42);
		SimpleRecord recordB = new SimpleRecord(72);
		SimpleRecord recordC = new SimpleRecord(92);
		
		sender.sendData(recordA);
		sender.sendData(recordB);
		sender.sendData(recordC);
		receiver.receiveData();
		receiver.receiveData();
		receiver.receiveData();
		
		assertEquals(3,   sender.getLocalSequenceNumber());		
		assertEquals(-1,  sender.getRemoteSequenceNumber());		
		assertEquals(0, receiver.getLocalSequenceNumber());		
		assertEquals(2, receiver.getRemoteSequenceNumber());		
	}

	@Test
	void sendAndReceive_threeMessagesWithMiddleMessageDropped_incrementsLocalAndRemoteSequenceNumberCorrectly() throws Exception {
		setupWithSameProtocol();
		SimpleRecord recordA = new SimpleRecord(42);
		SimpleRecord recordB = new SimpleRecord(72);
		SimpleRecord recordC = new SimpleRecord(92);
		
		sender.sendData(recordA);
		sender.sendData(recordB);
		sender.sendData(recordC);
		receiver.receiveData();
		receiverInnerChannel.dropNextReceivedPacket();
		receiver.receiveData();
		
		assertEquals(3,   sender.getLocalSequenceNumber());		
		assertEquals(-1,  sender.getRemoteSequenceNumber());		
		assertEquals(0, receiver.getLocalSequenceNumber());		
		assertEquals(2, receiver.getRemoteSequenceNumber());		
	}


}
