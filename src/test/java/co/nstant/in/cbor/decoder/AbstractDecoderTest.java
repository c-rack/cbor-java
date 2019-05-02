package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.UnicodeString;

public class AbstractDecoderTest {

    private class TestableAbstractDecoder extends AbstractDecoder<UnicodeString> {

        public TestableAbstractDecoder(InputStream inputStream) {
            super(null, inputStream);
        }

        public void callNextSymbol() throws CborException {
            nextSymbol();
        }

        public byte[] callNextSymbols(int amount) throws CborException {
            return nextSymbols(amount);
        }

        public long callGetLength(int initialByte) throws CborException {
            return getLength(initialByte);
        }

        public BigInteger callGetLengthAsBigInteger(int initialByte) throws CborException {
            return getLengthAsBigInteger(initialByte);
        }

        @Override
        public UnicodeString decode(int initialByte) throws CborException {
            return null;
        }

    }

    @Test(expected = CborException.class)
    public void shouldThrowExceptionOnUnexpectedEndOfStream() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});
        new TestableAbstractDecoder(inputStream).callNextSymbol();
        fail();
    }

    @Test(expected = CborException.class)
    public void shouldThrowExceptionOnUnexpectedEndOfStream2() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});
        new TestableAbstractDecoder(inputStream).callNextSymbols(1);
        fail();
    }

    @Test
    public void shouldReadFullyFromIncompleteStream() throws CborException {
        byte[] testArray = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
        InputStream inputStream = new ByteArrayInputStream(testArray) {

            @Override
            public synchronized int read(byte[] b, int off, int len) {
                return super.read(b, off, 4);
            }
        };
        TestableAbstractDecoder decoder = new TestableAbstractDecoder(inputStream);
        assertArrayEquals("Symbols array", decoder.callNextSymbols(8), Arrays.copyOf(testArray, 8));
        // Also test unexpected end of stream while we have this "special" stream
        try {
            decoder.callNextSymbols(12);
            fail("Should have failed with unexpected end of stream exception");
        } catch (CborException e) {
            // Expected
        }
    }

    @Test
    public void shouldDecodeInfinityLength() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});
        TestableAbstractDecoder decoder = new TestableAbstractDecoder(inputStream);
        assertEquals(BigInteger.valueOf(-1), decoder.callGetLengthAsBigInteger(31));
    }

    @Test(expected = CborException.class)
    public void shouldThrowExceptionOnReserved1() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});
        new TestableAbstractDecoder(inputStream).callGetLengthAsBigInteger(28);
        fail();
    }

    @Test(expected = CborException.class)
    public void shouldThrowExceptionOnReserved2() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});
        new TestableAbstractDecoder(inputStream).callGetLengthAsBigInteger(29);
        fail();
    }

    @Test(expected = CborException.class)
    public void shouldThrowExceptionOnReserved3() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});
        new TestableAbstractDecoder(inputStream).callGetLengthAsBigInteger(30);
        fail();
    }

    @Test(expected = CborException.class)
    public void shouldThrowExceptionOnReserved4() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});
        new TestableAbstractDecoder(inputStream).callGetLength(28);
        fail();
    }

    @Test(expected = CborException.class)
    public void shouldThrowExceptionOnReserved5() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});
        new TestableAbstractDecoder(inputStream).callGetLength(29);
        fail();
    }

    @Test(expected = CborException.class)
    public void shouldThrowExceptionOnReserved6() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});
        new TestableAbstractDecoder(inputStream).callGetLength(30);
        fail();
    }

    @Test
    public void shouldDecodeEightBytesLengthToLong() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { 1, 1, 1, 1, 1, 1, 1, 1 });
        long value = new TestableAbstractDecoder(inputStream).callGetLength(27);
        assertEquals(72340172838076673L, value);
    }

}
