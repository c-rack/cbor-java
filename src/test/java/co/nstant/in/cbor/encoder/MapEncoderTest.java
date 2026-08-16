package co.nstant.in.cbor.encoder;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.ByteString;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.Map;
import co.nstant.in.cbor.model.UnicodeString;

public class MapEncoderTest {

    @Test
    public void shouldEncodeMap() {
        List<DataItem> dataItems = new CborBuilder().addMap().put(1, true).put(".", true).put(3, true).put("..", true)
            .put(2, true).put("...", true).end().build();
        CborEncoder.encodeToBytes(dataItems);
    }

    @Test
    public void shouldSortCanonicalKeysByUnsignedByteValue() throws CborException {
        // Two equal-length byte-string keys, h'01' and h'ff'. Canonical order
        // is by unsigned byte value, so h'01' must be emitted before h'ff'.
        // A signed byte comparison would order 0xff (-1) before 0x01.
        Map map = new Map();
        map.put(new ByteString(new byte[] { (byte) 0xff }), new UnicodeString("B"));
        map.put(new ByteString(new byte[] { 0x01 }), new UnicodeString("A"));
        byte[] bytes = CborEncoder.encodeToBytes(map);
        assertArrayEquals(new byte[] {
            (byte) 0xa2,
            0x41, 0x01, 0x61, 0x41,         // h'01' : "A"
            0x41, (byte) 0xff, 0x61, 0x42   // h'ff' : "B"
        }, bytes);
    }

}
