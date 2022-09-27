package com.zalinius.darzalcommon.networking.data;

public record GameNetworkingProtocolIdentifier(
		String gameName,
		int version
) {
	public int protocolID() {
		return hashCode();
	}
	
	public static final int PROTOCOL_ID_SIZE = Integer.BYTES; 
}
