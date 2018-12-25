package co.nstant.in.cbor.decoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.AdditionalInformation;

public abstract class AbstractDecoder<T> {

    protected static final int INFINITY = -1;

    protected final InputStream inputStream;
    protected final CborDecoder decoder;
    private int maxPreallocationSize;

    public AbstractDecoder(CborDecoder decoder, InputStream inputStream) {
        this.decoder = decoder;
        this.inputStream = inputStream;
    }

    public abstract T decode(int initialByte) throws CborException;

    protected int nextSymbol() throws CborException {
        try {
            int symbol = inputStream.read();
            if (symbol == -1) {
                throw new IOException("Unexpected end of stream");
            }
            return symbol;
        } catch (IOException ioException) {
            throw new CborException(ioException);
        }
    }

    byte[] decodeBytes(long length) throws CborException {
        if (length > Integer.MAX_VALUE) {
            throw new CborException("Decoding fixed size items is limited to INTMAX");
        }
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(getPreallocationSize(length));
        final int chunkSize = 4096;
        int i = (int) length;
        byte[] buf = new byte[chunkSize];
        while (i > 0) {
            try {
                int read = inputStream.read(buf, 0, i > chunkSize ? chunkSize : i);
                if (read == -1) {
                    throw new IOException("Unexpected end of stream");
                }
                bytes.write(buf, 0, read);
                i -= read;
            } catch (IOException e) {
                throw new CborException(e);
            }
        }
        return bytes.toByteArray();
    }

    protected long getLength(int initialByte) throws CborException {
        switch (AdditionalInformation.ofByte(initialByte)) {
        case DIRECT:
            return initialByte & 31;
        case ONE_BYTE:
            return nextSymbol();
        case TWO_BYTES:
            long twoByteValue = 0;
            twoByteValue |= nextSymbol() << 8;
            twoByteValue |= nextSymbol() << 0;
            return twoByteValue;
        case FOUR_BYTES:
            long fourByteValue = 0L;
            fourByteValue |= (long) nextSymbol() << 24;
            fourByteValue |= (long) nextSymbol() << 16;
            fourByteValue |= (long) nextSymbol() << 8;
            fourByteValue |= (long) nextSymbol() << 0;
            return fourByteValue;
        case EIGHT_BYTES:
            long eightByteValue = 0;
            eightByteValue |= (long) nextSymbol() << 56;
            eightByteValue |= (long) nextSymbol() << 48;
            eightByteValue |= (long) nextSymbol() << 40;
            eightByteValue |= (long) nextSymbol() << 32;
            eightByteValue |= (long) nextSymbol() << 24;
            eightByteValue |= (long) nextSymbol() << 16;
            eightByteValue |= (long) nextSymbol() << 8;
            eightByteValue |= (long) nextSymbol() << 0;
            return eightByteValue;
        case INDEFINITE:
            return INFINITY;
        case RESERVED:
        default:
            throw new CborException("Reserved additional information");
        }
    }

    protected BigInteger getLengthAsBigInteger(int initialByte)
                    throws CborException {
        switch (AdditionalInformation.ofByte(initialByte)) {
        case DIRECT:
            return BigInteger.valueOf(initialByte & 31);
        case ONE_BYTE:
            return BigInteger.valueOf(nextSymbol());
        case TWO_BYTES:
            long twoByteValue = 0;
            twoByteValue |= nextSymbol() << 8;
            twoByteValue |= nextSymbol() << 0;
            return BigInteger.valueOf(twoByteValue);
        case FOUR_BYTES:
            long fourByteValue = 0L;
            fourByteValue |= (long) nextSymbol() << 24;
            fourByteValue |= (long) nextSymbol() << 16;
            fourByteValue |= (long) nextSymbol() << 8;
            fourByteValue |= (long) nextSymbol() << 0;
            return BigInteger.valueOf(fourByteValue);
        case EIGHT_BYTES:
            BigInteger eightByteValue = BigInteger.ZERO;
            eightByteValue = eightByteValue.or(BigInteger.valueOf(nextSymbol())
                            .shiftLeft(56));
            eightByteValue = eightByteValue.or(BigInteger.valueOf(nextSymbol())
                            .shiftLeft(48));
            eightByteValue = eightByteValue.or(BigInteger.valueOf(nextSymbol())
                            .shiftLeft(40));
            eightByteValue = eightByteValue.or(BigInteger.valueOf(nextSymbol())
                            .shiftLeft(32));
            eightByteValue = eightByteValue.or(BigInteger.valueOf(nextSymbol())
                            .shiftLeft(24));
            eightByteValue = eightByteValue.or(BigInteger.valueOf(nextSymbol())
                            .shiftLeft(16));
            eightByteValue = eightByteValue.or(BigInteger.valueOf(nextSymbol())
                            .shiftLeft(8));
            eightByteValue = eightByteValue.or(BigInteger.valueOf(nextSymbol())
                            .shiftLeft(0));
            return eightByteValue;
        case INDEFINITE:
            return BigInteger.valueOf(INFINITY);
        case RESERVED:
        default:
            throw new CborException("Reserved additional information");
        }
    }

    int getPreallocationSize(long length) {
        int len = Math.abs((int) length);
        return maxPreallocationSize > 0 ? Math.min(maxPreallocationSize, len) : len;
    }

    public void setMaxPreallocationSize(int maxPreallocationSize) {
        this.maxPreallocationSize = maxPreallocationSize;
    }
}
