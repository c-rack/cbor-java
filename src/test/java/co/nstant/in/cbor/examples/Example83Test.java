package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class Example83Test {

    private final List<DataItem> VALUE =
            new CborBuilder()
                    .add("foo").tagged(300).tagged(301).tagged(302)
                    .add("bar").tagged(400)
                    .build();

    private final byte[] ENCODED_VALUE =
            new byte[]{(byte) 0xD9, 0x01, 0x2E, (byte) 0xD9, 0x01, 0x2D, (byte) 0xD9, 0x01, 0x2C,
                    0x63, 0x66, 0x6F, 0x6F, (byte) 0xD9, 0x01, (byte) 0x90, 0x63, 0x62, 0x61, 0x72};

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
