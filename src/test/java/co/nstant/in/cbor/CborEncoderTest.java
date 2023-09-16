package co.nstant.in.cbor;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.MajorType;

public class CborEncoderTest {

    private class Mock extends DataItem {

        public Mock(MajorType majorType) {
            super(majorType);
        }

    }

    private CborEncoder encoder;

    @Before
    public void setup() {
        encoder = new CborEncoder(new ByteArrayOutputStream());
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectUnsignedIntegerImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.UNSIGNED_INTEGER));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectNegativeIntegerImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.NEGATIVE_INTEGER));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectByteStringImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.BYTE_STRING));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectUnicodeStringImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.UNICODE_STRING));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectArrayImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.ARRAY));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectMapImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.MAP));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectTagImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.TAG));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectSpecialImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.SPECIAL));
    }

    @Test(expected = CborException.class)
    public void shouldThrowCborExceptionOnUnderlyingIoException() throws CborException {
        new CborEncoder(new OutputStream() {

            private int counter = 0;

            @Override
            public void write(int b) throws IOException {
                if (++counter == 3) {
                    throw new IOException();
                }
            }

        }).encode(new CborBuilder().startString().add("string").end().build());
    }

    @Test(expected = CborException.class)
    public void shouldThrowCborExceptionOnUnderlyingIoException2() throws CborException {
        new CborEncoder(new OutputStream() {

            @Override
            public void write(int b) throws IOException {
                throw new IOException();
            }

        }).encode(new CborBuilder().startArray().add(1).end().build());
    }

    @Test
    public void shallEncode32bit() throws CborException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        new CborEncoder(outputStream).encode(new CborBuilder().addTag(4294967296L).build());
        byte[] bytes = outputStream.toByteArray();
        assertEquals(9, bytes.length);
        assertEquals((byte) 0xdB, bytes[0]);
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
