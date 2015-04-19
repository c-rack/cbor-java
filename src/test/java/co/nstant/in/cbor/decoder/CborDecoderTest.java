package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;

public class CborDecoderTest {

    @Test(expected = CborException.class)
    public void shouldThrowCborException() throws CborException {
        CborDecoder cborDecoder = new CborDecoder(new InputStream() {

            @Override
            public int read() throws IOException {
                throw new IOException();
            }

        });
        cborDecoder.decodeNext();
    }

    @Test(expected = CborException.class)
    public void shouldThrowCborException2() throws CborException {
        CborDecoder cborDecoder = new CborDecoder(new InputStream() {

            @Override
            public int read() throws IOException {
                return (8 << 5); // invalid major type
            }

        });
        cborDecoder.decodeNext();
    }

    @Test
    public void shouldSetAutoDecodeInfinitiveMaps() {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { 0, 1, 2 });
        CborDecoder cborDecoder = new CborDecoder(inputStream);
        assertTrue(cborDecoder.isAutoDecodeInfinitiveMaps());
        cborDecoder.setAutoDecodeInfinitiveMaps(false);
        assertFalse(cborDecoder.isAutoDecodeInfinitiveMaps());
    }

}
