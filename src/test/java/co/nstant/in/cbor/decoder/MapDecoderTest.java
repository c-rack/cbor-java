package co.nstant.in.cbor.decoder;

import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;

public class MapDecoderTest {
    @Test(expected = CborException.class)
    public void shouldThrowOnMissingKeyInMap() throws CborException {
        byte[] bytes = new byte[] {(byte) 0xa2, 0x01, 0x02};
        CborDecoder.decode(bytes);
    }

    @Test(expected = CborException.class)
    public void shouldThrowOnMissingValueInMap() throws CborException {
        byte[] bytes = new byte[] {(byte) 0xa2, 0x01, 0x02, 0x03};
        CborDecoder.decode(bytes);
    }

    @Test(expected = CborException.class)
    public void shouldThrowOnIncompleteIndefiniteLengthMap() throws CborException {
        byte[] bytes = new byte[] {(byte) 0xbf, 0x61, 0x01};
        CborDecoder.decode(bytes);
    }
}
