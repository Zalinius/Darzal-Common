package com.darzalgames.darzalcommon.networking.client;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.darzalgames.darzalcommon.networking.connection.nonaddressed.AddressedDataChannel;
import com.darzalgames.darzalcommon.networking.connection.nonaddressed.ReliableRecordChannel;
import com.darzalgames.darzalcommon.networking.data.GameNetworkingProtocolIdentifier;
import com.darzalgames.darzalcommon.networking.server.GameServer;

public class GameClient<E> {

	private ReliableRecordChannel<E> channel;
	private double timeoutTimer;
	private boolean connected;
	private Consumer<E> networkDataConsumer;
	private double timer;
	private Supplier<E> networkDataSupplier;

	public GameClient(SocketAddress serverAddress, GameNetworkingProtocolIdentifier protocolIdentifier, Supplier<E> networkDataSupplier, Consumer<E> networkDataConsumer) throws IOException {
		this.networkDataSupplier = networkDataSupplier;
		this.networkDataConsumer = networkDataConsumer;
		this.channel = new ReliableRecordChannel<>(protocolIdentifier, new AddressedDataChannel(serverAddress));
		this.timeoutTimer = 0;
		this.connected = true;
		System.out.println("Starting up Client");

		timer = 0;
	}


	public void update(double delta) throws IOException {
		channel.update(delta);
		//SEND DATA
		if(connected && channel.isSendingAllowed()) {
			channel.sendData(networkDataSupplier.get());					
		}
		timer += delta;
		if(timer > 1) {
			System.out.print("FPS: " + (int)(1.0/delta)+"\n");
			System.out.print("Ping:" + (int)(channel.rttTime()*1000)+"ms\n");
			System.out.print("Packet Loss: " + (int)(channel.sentPacketLoss()*100)+"%\n\n");
			timer -= 1;
		}

		//RECEIVE DATA
		if(connected) {
			try {
				while (channel.isDataAvailable()) {
					E data = channel.receiveData();
					networkDataConsumer.accept(data);
					timeoutTimer = 0;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			checkTimeout(delta);
		}
	}

	private void checkTimeout(double delta) {
		this.timeoutTimer += delta;
		if(timeoutTimer > GameServer.CONNECTION_TIMEOUT) {
			connected = false;
			timeoutTimer = 0;
			System.out.println("CLIENT - server timed out :(");
		}		
	}

}
