package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.Map;
import co.nstant.in.cbor.model.UnsignedInteger;

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

    @Test
    public void shouldUseLastOfDuplicateKeysByDefault() throws CborException {
        byte[] bytes = new byte[] {(byte)0xa2, 0x01, 0x01, 0x01, 0x02};
        List<DataItem> decoded = CborDecoder.decode(bytes);
        Map map = (Map)decoded.get(0);
        assertEquals(map.getKeys().size(), 1);
        assertEquals(map.get(new UnsignedInteger(1)), new UnsignedInteger(2));
    }

    @Test(expected = CborException.class)
    public void shouldThrowOnDuplicateKeyIfEnabled() throws CborException {
        byte[] bytes = new byte[] {(byte)0xa2, 0x01, 0x01, 0x01, 0x02};
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        CborDecoder decoder = new CborDecoder(bais);
        decoder.setRejectDuplicateKeys(true);
        decoder.decode();
    }

    @Test(expected = CborException.class)
    public void shouldThrowInDuplicateKeyInIndefiniteLengthMapIfEnabled()
            throws CborException {
        byte[] bytes = new byte[] {(byte)0xbf, 0x01, 0x01, 0x01, 0x02, (byte) 0xff};
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        CborDecoder decoder = new CborDecoder(bais);
        decoder.setRejectDuplicateKeys(true);
        decoder.decode();
    }
}
