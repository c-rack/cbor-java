package co.nstant.in.cbor.decoder;

import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;

public class ArrayDecoderTest {
    @Test(expected = CborException.class)
    public void shouldThrowOnIncompleteArray() throws CborException {
        byte[] bytes = new byte[] {(byte) 0x82, 0x01 };
        CborDecoder.decode(bytes);
    }

    @Test(expected = CborException.class)
    public void shouldThrowInIncompleteIndefiniteLengthArray() throws CborException {
        byte[] bytes = new byte[] {(byte) 0x9f, 0x01, 0x02};
        CborDecoder.decode(bytes);
    }
}
