package com.darzalgames.darzalcommon.networking.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.BitSet;

import org.junit.jupiter.api.Test;

class PackAckQueueTest {
	
	@Test
	void packAckQueue_withNoPackets_hasNoAcks() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		
		int ackInt = packAckQueue.createAckFromPackets();
		
		assertEquals(0, ackInt);
	}

	@Test
	void packAckQueue_withASinglePackets_hasNoAcks() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		
		packAckQueue.acknowledgePacket(0);
		int ackInt = packAckQueue.createAckFromPackets();
		
		assertEquals(0, ackInt);
	}

	@Test
	void packAckQueue_withTwoSequentialPackets_hasSingleAckInFirstPosition() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		packAckQueue.acknowledgePacket(0);
		packAckQueue.acknowledgePacket(1);
		int ackInt = packAckQueue.createAckFromPackets();
		
		assertEquals(0B0001, ackInt);
	}

	@Test
	void packAckQueue_withFourSequentialPackets_hasAcksInFirstThreePositions() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		packAckQueue.acknowledgePacket(0);
		packAckQueue.acknowledgePacket(1);
		packAckQueue.acknowledgePacket(2);
		packAckQueue.acknowledgePacket(3);
		int ackInt = packAckQueue.createAckFromPackets();
		
		assertEquals(0B0111, ackInt);
	}

	@Test
	void packAckQueue_withFourNonSequentialPackets_hasAcksInCorrectPositions() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		packAckQueue.acknowledgePacket(0);
		packAckQueue.acknowledgePacket(2);
		packAckQueue.acknowledgePacket(4);
		packAckQueue.acknowledgePacket(7);
		int ackInt = packAckQueue.createAckFromPackets();
		
		//              012_34567
		assertEquals(0B0101_0100, ackInt);
	}

	@Test
	void packAckQueue_withFourNonSequentialPacketsOutOfOrder_hasAcksInCorrectPositions() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		packAckQueue.acknowledgePacket(2);
		packAckQueue.acknowledgePacket(7);
		packAckQueue.acknowledgePacket(4);
		packAckQueue.acknowledgePacket(0);
		int ackInt = packAckQueue.createAckFromPackets();
		
		//              012_34567
		assertEquals(0B0101_0100, ackInt);
	}


	@Test
	void packAckQueue_withPacketsToOldForPackAckQueue_hasAcksInCorrectPositions() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		packAckQueue.acknowledgePacket( 0); //This packet should be too old for the ack queue
		packAckQueue.acknowledgePacket(42);
		packAckQueue.acknowledgePacket(44);
		packAckQueue.acknowledgePacket(47);
		int ackInt = packAckQueue.createAckFromPackets();
		
		//              404142__43444546_47
		assertEquals(0B0_0_0_1__0_1_0_0, ackInt);
	}

	@Test
	void packAckQueue_withAllSequentialPackets_hasAcksInEveryPosition() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		
		for (int i = 0; i < 33; i++) {
			packAckQueue.acknowledgePacket(i);			
		}
		int ackInt = packAckQueue.createAckFromPackets();
		
		assertEquals(0B11111111_11111111_11111111_11111111, ackInt);
	}

	@Test
	void packAckQueue_withAllSequentialPacketsAndManyTooOld_hasAcksInEveryPosition() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		
		for (int i = 0; i < 73; i++) {
			packAckQueue.acknowledgePacket(i);			
		}
		int ackInt = packAckQueue.createAckFromPackets();
		
		assertEquals(0B11111111_11111111_11111111_11111111, ackInt);
	}
	
	@Test
	void acknowlegePackets_withThreeMostRecentAckBitsSets_hasAcksInFirstThreePositions() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		
		packAckQueue.acknowledgePackets(3, 0B0111);
		int ackInt = packAckQueue.createAckFromPackets();
		
		assertEquals(0B0111, ackInt);
	}

	@Test
	void acknowlegePackets_withVariousAckBitsSets_hasAcksInThosePositions() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		
		packAckQueue.acknowledgePackets(3, 0B1101_0111);
		int ackInt = packAckQueue.createAckFromPackets();
		
		assertEquals(0B1101_0111, ackInt);
	}

	@Test
	void acknowlegePackets_withSeriesOfackbitAcks_hasAcksInCorrectPositions() throws Exception {
		PackAckQueue packAckQueue = new PackAckQueue();
		
		packAckQueue.acknowledgePackets(3, 0B0101); //acknowledges sequences 3, 2 and 0
		packAckQueue.acknowledgePackets(8, 0B0011); //acknowledges sequences 8, 7, 6
		int ackInt = packAckQueue.createAckFromPackets();
		
		//             0123_45678
		assertEquals(0B1011_0011, ackInt, "expected " + Integer.toBinaryString(0B1011_0011) + " but was " + Integer.toBinaryString(ackInt));
	}


	
	
	
	
	@Test
	void intToBitSet_onSimpleInt() throws Exception {
		int integer = 0B1;
		
		BitSet bitSet = PackAckQueue.intToBitSet(integer);
		
		assertEquals(true, bitSet.get(0));
	}

	@Test
	void intToBitSet_onComplexInt() throws Exception {
		int integer = 0B1011;
		
		BitSet bitSet = PackAckQueue.intToBitSet(integer);
		
		assertEquals(true, bitSet.get(0));
		assertEquals(true, bitSet.get(1));
		assertEquals(false,bitSet.get(2));
		assertEquals(true, bitSet.get(3));
	}
	

}












