package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class Example83Test {

    private final List<DataItem> VALUE =
            new CborBuilder().addArray()
                    .add("foo").tagged(300).tagged(301).tagged(302)
                    .add("bar").tagged(400)
                    .addMap()
                        .put("a", "b")
                        .addKey("c").tagged(401).value("d").tagged(402)
                    .end().tagged(403)
                    .addArray()
                        .add("c").tagged(503)
                    .end().tagged(504)
                    .end()
                    .build();

    private final byte[] ENCODED_VALUE =
            new byte[]{(byte) 0x84, (byte) 0xD9, 0x01, 0x2E, (byte) 0xD9, 0x01, 0x2D, (byte) 0xD9,
                    0x01, 0x2C, 0x63, 0x66, 0x6F, 0x6F, (byte) 0xD9, 0x01, (byte) 0x90, 0x63, 0x62,
                    0x61, 0x72, (byte) 0xD9, 0x01, (byte) 0x93, (byte) 0xA2, 0x61, 0x61, 0x61, 0x62,
                    (byte) 0xD9, 0x01, (byte) 0x91, 0x61, 0x63, (byte) 0xD9, 0x01, (byte) 0x92,
                    0x61, 0x64, (byte) 0xD9, 0x01, (byte) 0xF8, (byte) 0x81, (byte) 0xD9, 0x01,
                    (byte) 0xF7, 0x61, 0x63};

    @Test
    public void shouldEncode() {
        Assert.assertArrayEquals(ENCODED_VALUE, CborEncoder.encodeToBytes(VALUE));
    }

    @Test
    public void shouldDecode() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(ENCODED_VALUE);
        CborDecoder decoder = new CborDecoder(inputStream);
        List<DataItem> dataItems = decoder.decode();
        Assert.assertArrayEquals(VALUE.toArray(), dataItems.toArray());
    }
}
