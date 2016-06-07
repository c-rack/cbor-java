package co.nstant.in.cbor.encoder;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.MajorType;

public class AbstractEncoderTest {

    private class TestEncoder extends AbstractEncoder<Object> {

        public TestEncoder(OutputStream outputStream) {
            super(null, outputStream);
        }

        @Override
        public void encode(Object dataItem) throws CborException {
        }

        public void doEncodeTypeAndLength(long length) throws CborException {
            encodeTypeAndLength(MajorType.ARRAY, length);
        }

    }

    @Test(expected = CborException.class)
    public void shouldThrowCborExceptionOnUnderlyingIoException() throws CborException {
        final int[] counter = new int[1];
        new CborEncoder(new OutputStream() {

            @Override
            public synchronized void write(int b) throws IOException {
                if (++counter[0] == 3) {
                    throw new IOException();
                }
            }

        }).encode(new CborBuilder().startString().add("string").end().build());
    }

    @Test(expected = CborException.class)
    public void shouldThrowCborExceptionOnUnderlyingIoException2() throws CborException {
        new CborEncoder(new OutputStream() {

            @Override
            public synchronized void write(int b) throws IOException {
                throw new IOException();
            }

        }).encode(new CborBuilder().startArray().add(1).end().build());
    }

    @Test
    public void shallEncode32bit() throws CborException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TestEncoder encoder = new TestEncoder(outputStream);
        encoder.doEncodeTypeAndLength(4294967296L);
        byte[] bytes = outputStream.toByteArray();
        assertEquals(9, bytes.length);
        assertEquals((byte) 0x9B, bytes[0]);
        assertEquals((byte) 0x00, bytes[1]);
        assertEquals((byte) 0x00, bytes[2]);
        assertEquals((byte) 0x00, bytes[3]);
        assertEquals((byte) 0x01, bytes[4]);
        assertEquals((byte) 0x00, bytes[5]);
        assertEquals((byte) 0x00, bytes[6]);
        assertEquals((byte) 0x00, bytes[7]);
        assertEquals((byte) 0x00, bytes[8]);
    }

}
