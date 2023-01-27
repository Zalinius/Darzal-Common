package com.zalinius.darzalcommon.networking.data;

import java.util.HashMap;
import java.util.Map;

public class RttTracker {
	
	private Map<Integer, Double> packetAgeMap;
	private double time;
	private double exponentialAverageRTT;
	
	private final double rttTimeout;
	
	public RttTracker() {
		this(1.0); //TODO move this to a constant somewhere, maybe make a global one second timeout constant
	}
	
	public RttTracker(double rttTimeout) {
		this.packetAgeMap = new HashMap<>();
		this.time = 0.0;
		this.exponentialAverageRTT = 0.0;

		this.rttTimeout = rttTimeout;
	}
	
	public void update(double delta) {
		time += delta;
		packetAgeMap.entrySet().removeIf(entry -> ageOfPacket(entry.getKey()) > rttTimeout);
	}
	
	public void packetWasSent(int sequence) {
		packetAgeMap.put(sequence, time);
	}
	
	public void sentPacketWasAcknowledged(int sequence) {
		if(packetAgeMap.containsKey(sequence)) {
			double packetRTT = time - packetAgeMap.remove(sequence);
			
			if(exponentialAverageRTT == 0.0) {
				exponentialAverageRTT = packetRTT;
			}
			else {
				exponentialAverageRTT = 0.9*exponentialAverageRTT + 0.1*packetRTT;			
			}			
		}
	}
	
	public double getCurrentAverageRTT() {
		return exponentialAverageRTT;
	}
	
	private double ageOfPacket(int sequence) {
		return time - packetAgeMap.get(sequence);
	}
	


}
