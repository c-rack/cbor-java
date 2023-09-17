package co.nstant.in.cbor;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.MajorType;

public class CborEncoderTest {

    private class Mock extends DataItem {

        public Mock(MajorType majorType) {
            super(majorType);
        }

    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectUnsignedIntegerImplementation() {
        new Mock(MajorType.UNSIGNED_INTEGER).encodeToBytes();
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectNegativeIntegerImplementation() {
        new Mock(MajorType.NEGATIVE_INTEGER).encodeToBytes();
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectByteStringImplementation() {
        new Mock(MajorType.BYTE_STRING).encodeToBytes();
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectUnicodeStringImplementation() {
        new Mock(MajorType.UNICODE_STRING).encodeToBytes();
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectArrayImplementation() {
        new Mock(MajorType.ARRAY).encodeToBytes();
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectMapImplementation() {
        new Mock(MajorType.MAP).encodeToBytes();
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectTagImplementation() {
        new Mock(MajorType.TAG).encodeToBytes();
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectSpecialImplementation() {
        new Mock(MajorType.SPECIAL).encodeToBytes();
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
    public void shallEncode32bit() {
        byte[] bytes = CborEncoder.encodeToBytes(new CborBuilder().addTag(4294967296L).build());
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
