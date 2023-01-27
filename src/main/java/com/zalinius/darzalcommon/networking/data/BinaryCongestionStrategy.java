package com.zalinius.darzalcommon.networking.data;

import java.util.function.Supplier;

public class BinaryCongestionStrategy {

	private final Supplier<Double> rttSupplier;
	private final double badRttThreshold;

	private double timeSinceLastSend;
	private double targetSendFrequency;
	
	private double timeInFastSend;
	private double timeInSlowSendWithGoodRtt;
	private double timeOfGoodRttNeededToSpeedUp;

	public static final double FAST_SEND_FREQUENCY = 30.0;
	public static final double SLOW_SEND_FREQUENCY = 10.0;

	public BinaryCongestionStrategy(Supplier<Double> rttSupplier) {
		this.rttSupplier = rttSupplier;
		this.badRttThreshold = 0.25;

		this.timeSinceLastSend = 0;
		this.targetSendFrequency = FAST_SEND_FREQUENCY;

		this.timeInFastSend = 0.0;
		this.timeInSlowSendWithGoodRtt = 0.0;
		this.timeOfGoodRttNeededToSpeedUp = 1.0;
	}

	public void update(double delta) {
		timeSinceLastSend += delta;
		checkCongestion(delta);
	}

	public boolean isSendingAllowed() {
		return timeSinceLastSend > timeBetweenSends();
	}
	public void sent() {
		timeSinceLastSend -= timeBetweenSends();
	}
	

	private double timeBetweenSends() {
		return 1.0/targetSendFrequency;
	}

	private void checkCongestion(double delta) {
		if(targetSendFrequency == FAST_SEND_FREQUENCY) {
			if(rttSupplier.get() > badRttThreshold) {
				switchToSlowSend();
			}
			else {
				timeInFastSend += delta;
				if(hasJustPassedAThresholdMultipleOfTimeInFastMode(delta)) {
					timeOfGoodRttNeededToSpeedUp = Math.max(0.5*timeOfGoodRttNeededToSpeedUp, 1.0);
				}
			}
		}
		else {
			if(rttSupplier.get() < badRttThreshold) {
				timeInSlowSendWithGoodRtt += delta;
				if(timeInSlowSendWithGoodRtt > timeOfGoodRttNeededToSpeedUp) {
					switchToFastSend();
				}
			}
			else {
				timeInSlowSendWithGoodRtt = 0.0;								
			}
		}
	}
	
	private void switchToFastSend() {
		targetSendFrequency = FAST_SEND_FREQUENCY;
		timeInFastSend = 0.0;
	}
	
	private void switchToSlowSend() {
		targetSendFrequency = SLOW_SEND_FREQUENCY;
		timeInSlowSendWithGoodRtt = 0.0;
		
		if(timeInFastSend < 10.0) {
			timeOfGoodRttNeededToSpeedUp = Math.min(2*timeOfGoodRttNeededToSpeedUp, 60.0);
		}
	}
	
	private boolean hasJustPassedAThresholdMultipleOfTimeInFastMode(double delta) {
		return timeInFastSend % 10.0 < delta;
	}

}
