package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;

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
