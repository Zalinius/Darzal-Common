package com.darzalgames.darzalcommon.networking.connection.addressed;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.channels.DatagramChannel;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.testutils.TwoWayFakeDatagramChannel;

class BufferedDatagramChannelTest {
	
	@Test
	void receive_whenNothingSent_throwsException() throws Exception {
		TwoWayFakeDatagramChannel twoWayFakeDatagramChannel = new TwoWayFakeDatagramChannel();
		DatagramChannel fakeDatagramChannel = twoWayFakeDatagramChannel.getAToBChannel();
		BufferedDatagramChannel simpleDatagramChannel = new BufferedDatagramChannel(fakeDatagramChannel);
		
		assertThrows(NoSuchElementException.class, () -> simpleDatagramChannel.receive());
	}

	@Test
	void isPacketAvailable_whenNothingSent_returnsFalse() throws Exception {
		TwoWayFakeDatagramChannel twoWayFakeDatagramChannel = new TwoWayFakeDatagramChannel();
		DatagramChannel fakeDatagramChannel = twoWayFakeDatagramChannel.getAToBChannel();
		BufferedDatagramChannel simpleDatagramChannel = new BufferedDatagramChannel(fakeDatagramChannel);

		boolean result = simpleDatagramChannel.isPacketAvailable();
		
		assertFalse(result);
	}

}
