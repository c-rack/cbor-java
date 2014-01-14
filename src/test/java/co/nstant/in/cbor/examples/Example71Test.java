package co.nstant.in.cbor.examples;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;

/**
 * {"a": "A", "b": "B", "c": "C", "d": "D","e": "E"} ->
 * 0xa56161614161626142616361436164614461656145
 */
public class Example71Test {

    private static final List<DataItem> VALUE = new CborBuilder()
                    .addMap()
                    .put("a", "A")
                    .put("b", "B")
                    .put("c", "C")
                    .put("d", "D")
                    .put("e", "E")
                    .end()
                    .build();

    private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0xa5, 0x61,
                    0x61, 0x61, 0x41, 0x61, 0x62, 0x61, 0x42, 0x61, 0x63, 0x61,
                    0x43, 0x61, 0x64, 0x61, 0x44, 0x61, 0x65, 0x61, 0x45
    };

    @Test
    public void shouldEncode() throws CborException {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(byteOutputStream);
        encoder.encode(VALUE);
        Assert.assertArrayEquals(ENCODED_VALUE, byteOutputStream.toByteArray());
    }

    @Test
    public void shouldDecode() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(ENCODED_VALUE);
        CborDecoder decoder = new CborDecoder(inputStream);
        List<DataItem> dataItems = decoder.decode();
        Assert.assertArrayEquals(VALUE.toArray(), dataItems.toArray());
    }

}
