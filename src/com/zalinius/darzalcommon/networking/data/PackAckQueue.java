package com.zalinius.darzalcommon.networking.data;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

public class PackAckQueue {
	
	private final SortedSet<Integer> packetSequences; //Has max size SEQUENCE_RANGE +1
	private final Collection<Consumer<Integer>> ackListeners;
	public static final int SEQUENCE_RANGE = 32;

	public PackAckQueue(Consumer<Integer>...ackListeners) {
		this.packetSequences = new TreeSet<>();
		this.ackListeners = Arrays.asList(ackListeners);
	}
	
	public void acknowledgePacket(int packetSequence) {
		packetSequences.add(packetSequence);
		ackListeners.forEach(ackListener -> ackListener.accept(packetSequence));
		removeOldSequences();
	}

	public void acknowledgePackets(int packetSequence, int packetAckField) {
		acknowledgePacket(packetSequence);
		BitSet bitSet = intToBitSet(packetAckField);
		for (int i = 0; i < bitSet.length(); i++) {
			if(bitSet.get(i)) {
				acknowledgePacket(packetSequence - (i+1));
			}
		}

	}

	private void removeOldSequences() {
		while (isFirstSequenceTooOld()) {
			packetSequences.remove(packetSequences.first());
		}
	}
	
	private boolean isFirstSequenceTooOld() {
		return packetSequences.last() - packetSequences.first() > SEQUENCE_RANGE;
	}
	
	public int createAckFromPackets() {
		if(packetSequences.isEmpty() || packetSequences.size() == 1) {
			return 0;
		}
		
		BitSet bitSet = new BitSet(SEQUENCE_RANGE);
		int currentSequenceNumber = packetSequences.last();
		
		for (Iterator<Integer> it = packetSequences.iterator(); it.hasNext();) {
			int sequenceNumber = it.next();
			if(sequenceNumber != currentSequenceNumber) {
				int ackBitPosition = currentSequenceNumber - sequenceNumber - 1;
				bitSet.set(ackBitPosition);
			}
		}
		return (int) bitSet.toLongArray()[0];
	}
	
	public static BitSet intToBitSet(int n) {
		BitSet source = BitSet.valueOf(new long[] {n});
		
		BitSet result = new BitSet(SEQUENCE_RANGE);
		for (int i = 0; i < SEQUENCE_RANGE; i++) {
			if(source.length() > i && source.get(i)) {
				result.set(i);
			}
		}
		
		return result;
	}
	
	public double nonAckPercentage() {
		return 1.0 - packetSequences.size()/(SEQUENCE_RANGE+1.0);
	}
		

}
