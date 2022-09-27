package com.zalinius.darzalcommon.networking.server;

import static com.zalinius.darzalcommon.functional.LambdaExceptionUtil.rethrowConsumer;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.zalinius.darzalcommon.networking.connection.ConnectionBuilder;
import com.zalinius.darzalcommon.networking.connection.addressed.MultiplexingDatagramChannel;
import com.zalinius.darzalcommon.networking.connection.nonaddressed.ReliableRecordChannel;
import com.zalinius.darzalcommon.networking.data.GameNetworkingProtocolIdentifier;

public class GameServer<E> {

	private final GameNetworkingProtocolIdentifier gameNetworkingProtocolIdentifier;
	private final MultiplexingDatagramChannel multiplexer;

	private final Map<SocketAddress, ReliableRecordChannel<E>> recordChannels;
	private final Map<SocketAddress, Double> clientTimeouts;
	
	private final Supplier<E> dataSupplier;
	private final Consumer<E> consumer;

	public static final double CONNECTION_TIMEOUT = 4.0;

	public GameServer(GameNetworkingProtocolIdentifier gameNetworkingProtocolIdentifier, int listeningPort, Supplier<E> dataSupplier, Consumer<E> consumer) throws IOException {
		this.gameNetworkingProtocolIdentifier = gameNetworkingProtocolIdentifier;
		this.multiplexer = new MultiplexingDatagramChannel(ConnectionBuilder.buildBufferedDatagramChannel(listeningPort));

		this.recordChannels = new HashMap<>();
		this.clientTimeouts = new HashMap<>();
		
		this.dataSupplier = dataSupplier;
		this.consumer = consumer;
	}
	

	public void update(double delta) throws Exception {
		//CONNECT NEW CLIENTS
		Set<SocketAddress> newClients = new HashSet<>();
		newClients.addAll(multiplexer.allConnections());
		newClients.removeAll(clientTimeouts.keySet());
		newClients.forEach(client -> {
			clientTimeouts.put(client, 0.0);
			recordChannels.put(client, new ReliableRecordChannel<>(gameNetworkingProtocolIdentifier, multiplexer.getDataChannel(client)));
			System.out.println("Client " + client + " connected.");
		});


		//RECEIVE DATA
		recordChannels.keySet().forEach(rethrowConsumer(client -> {
			ReliableRecordChannel<E> channel = recordChannels.get(client);
			while (channel.isDataAvailable()) {
				E data = channel.receiveData();
				consumer.accept(data);
				clientTimeouts.put(client, 0.0);
			}
		}));

		//CHECK TIMEOUTS
		recordChannels.keySet().forEach(client -> {

			double timeoutTimer = clientTimeouts.get(client);
			timeoutTimer += delta;
			if(timeoutTimer > CONNECTION_TIMEOUT) {
				//TODO toss out/disconnect client somehow
				System.out.println("Client " + client + " timed out.");
			}		
		});

		//SEND DATA
		recordChannels.keySet().forEach(rethrowConsumer(client -> {
			ReliableRecordChannel<E> channel = recordChannels.get(client);
			channel.update(delta);
			if (channel.isSendingAllowed()) {
				E data = dataSupplier.get(); //TODO get data for this
				channel.sendData(data);
			}
		}));


	}


}
