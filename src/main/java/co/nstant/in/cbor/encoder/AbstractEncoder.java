package co.nstant.in.cbor.encoder;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.AdditionalInformation;
import co.nstant.in.cbor.model.ByteString;
import co.nstant.in.cbor.model.MajorType;
import co.nstant.in.cbor.model.Tag;

public abstract class AbstractEncoder<T> {

	private final OutputStream outputStream;
	protected final CborEncoder encoder;

	public AbstractEncoder(CborEncoder encoder, OutputStream outputStream) {
		this.encoder = encoder;
		this.outputStream = outputStream;
	}

	public abstract void encode(T dataItem) throws CborException;

	protected void encodeTypeChunked(MajorType majorType) throws CborException {
		int symbol = majorType.getValue() << 5;
		symbol |= AdditionalInformation.INDEFINITE.getValue();
		try {
			outputStream.write(symbol);
		} catch (IOException ioException) {
			throw new CborException(ioException);
		}
	}

	protected void encodeTypeAndLength(MajorType majorType, long length) throws CborException {
		int symbol = majorType.getValue() << 5;
		if (length <= 23L) {
			write((int) (symbol | length));
		} else if (length <= 255L) {
			symbol |= AdditionalInformation.ONE_BYTE.getValue();
			write(symbol);
			write((int) length);
		} else if (length <= 65535L) {
			symbol |= AdditionalInformation.TWO_BYTES.getValue();
			write(symbol);
			write((int) (length >> 8));
			write((int) (length & 0xFF));
		} else if (length <= 4294967295L) {
			symbol |= AdditionalInformation.FOUR_BYTES.getValue();
			write(symbol);
			write((int) ((length >> 24) & 0xFF));
			write((int) ((length >> 16) & 0xFF));
			write((int) ((length >> 8) & 0xFF));
			write((int) (length & 0xFF));
		} else {
			symbol |= AdditionalInformation.EIGHT_BYTES.getValue();
			write(symbol);
			write((int) ((length >> 56) & 0xFF));
			write((int) ((length >> 48) & 0xFF));
			write((int) ((length >> 40) & 0xFF));
			write((int) ((length >> 32) & 0xFF));
			write((int) ((length >> 24) & 0xFF));
			write((int) ((length >> 16) & 0xFF));
			write((int) ((length >> 8) & 0xFF));
			write((int) (length & 0xFF));
		}
	}

	protected void encodeTypeAndLength(MajorType majorType, BigInteger length) throws CborException {
		boolean negative = majorType == MajorType.NEGATIVE_INTEGER;
		int symbol = majorType.getValue() << 5;
		if (length.compareTo(BigInteger.valueOf(24)) == -1) {
			write(symbol | length.intValue());
		} else if (length.compareTo(BigInteger.valueOf(256)) == -1) {
			symbol |= AdditionalInformation.ONE_BYTE.getValue();
			write(symbol);
			write(length.intValue());
		} else if (length.compareTo(BigInteger.valueOf(65536L)) == -1) {
			symbol |= AdditionalInformation.TWO_BYTES.getValue();
			write(symbol);
			long twoByteValue = length.longValue();
			write((int) (twoByteValue >> 8));
			write((int) (twoByteValue & 0xFF));
		} else if (length.compareTo(BigInteger.valueOf(4294967296L)) == -1) {
			symbol |= AdditionalInformation.FOUR_BYTES.getValue();
			write(symbol);
			long fourByteValue = length.longValue();
			write((int) ((fourByteValue >> 24) & 0xFF));
			write((int) ((fourByteValue >> 16) & 0xFF));
			write((int) ((fourByteValue >> 8) & 0xFF));
			write((int) (fourByteValue & 0xFF));
		} else if (length.compareTo(new BigInteger("18446744073709551616")) == -1) {
			symbol |= AdditionalInformation.EIGHT_BYTES.getValue();
			write(symbol);
			BigInteger mask = BigInteger.valueOf(0xFF);
			write(length.shiftRight(56).and(mask).intValue());
			write(length.shiftRight(48).and(mask).intValue());
			write(length.shiftRight(40).and(mask).intValue());
			write(length.shiftRight(32).and(mask).intValue());
			write(length.shiftRight(24).and(mask).intValue());
			write(length.shiftRight(16).and(mask).intValue());
			write(length.shiftRight(8).and(mask).intValue());
			write(length.and(mask).intValue());
		} else {
			if (negative) {
				encoder.encode(new Tag(3));
			} else {
				encoder.encode(new Tag(2));
			}
			encoder.encode(new ByteString(length.toByteArray()));
		}
	}

	protected void write(int b) throws CborException {
		try {
			outputStream.write(b);
		} catch (IOException ioException) {
			throw new CborException(ioException);
		}
	}

	protected void write(byte[] bytes) throws CborException {
		try {
			outputStream.write(bytes);
		} catch (IOException ioException) {
			throw new CborException(ioException);
		}
	}

}
