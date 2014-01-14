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
 * [1, 2, 3, 4, 5, 6,7, 8, 9, 10, 11, 12,13, 14, 15, 16, 17,18, 19, 20, 21, 22,
 * 23, 24, 25] -> 0x98 190102030405060708090a0b0c0d0e0f101112131415161718181819
 */
public class Example66Test {

    private static final List<DataItem> VALUE = new CborBuilder()
                    .addArray()
                    .add(1).add(2).add(3).add(4).add(5)
                    .add(6).add(7).add(8).add(9).add(10)
                    .add(11).add(12).add(13).add(14).add(15)
                    .add(16).add(17).add(18).add(19).add(20)
                    .add(21).add(22).add(23).add(24).add(25)
                    .end()
                    .build();

    private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0x98,
                    0x19, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
                    0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13,
                    0x14, 0x15, 0x16, 0x17, 0x18, 0x18, 0x18, 0x19
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
