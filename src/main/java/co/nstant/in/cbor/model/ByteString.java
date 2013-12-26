package co.nstant.in.cbor.model;

import java.util.Arrays;
import java.util.Objects;

public class ByteString extends ChunkableDataItem {

	private final byte[] bytes;

	public ByteString(byte[] bytes) {
		super(MajorType.BYTE_STRING);
		Objects.requireNonNull(bytes);
		this.bytes = bytes;
	}

	public byte[] getBytes() {
		return bytes;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof ByteString) {
			ByteString other = (ByteString) object;
			return Arrays.equals(bytes, other.bytes);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return bytes.hashCode();
	}

}
