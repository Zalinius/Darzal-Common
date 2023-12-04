package com.darzalgames.darzalcommon.networking.data;

import java.nio.ByteBuffer;

public record PacketHeader (
		int protocolID,
		int sequenceNumber,
		int acknowledgement,
		int acknowledgementBitField)
implements Comparable<PacketHeader>
{
	public ByteBuffer toByteBuffer() {
		ByteBuffer byteBuffer = ByteBuffer.allocate(strictByteSize());
		byteBuffer.putInt(protocolID);
		byteBuffer.putInt(sequenceNumber);
		byteBuffer.putInt(acknowledgement);
		byteBuffer.putInt(acknowledgementBitField);
		byteBuffer.flip();
		return byteBuffer;
	}

	public static int strictByteSize() {
		return 4*Integer.BYTES;
	}

	public static PacketHeader constructFromByteBuffer(ByteBuffer byteBuffer) {
		if(byteBuffer.limit() < strictByteSize()) {
			throw new IllegalArgumentException("Supplied bytebuffer is " + byteBuffer.limit() + ", should be " + strictByteSize());
		}
		return new PacketHeader(
				byteBuffer.getInt(),
				byteBuffer.getInt(),
				byteBuffer.getInt(),
				byteBuffer.getInt());
	}

	
	@Override
	public int compareTo(PacketHeader other) {
		return this.sequenceNumber - other.sequenceNumber;
	}
}
