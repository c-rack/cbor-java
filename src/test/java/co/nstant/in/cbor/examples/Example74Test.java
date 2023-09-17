package co.nstant.in.cbor.examples;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.SimpleValue;

/**
 * [_ ] -> 0x9fff
 */
public class Example74Test {

    private static final List<DataItem> VALUE = new CborBuilder().add(new Array().setChunked(true))
        .add(SimpleValue.BREAK).build();

    private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0x9f, (byte) 0xff };

    @Test
    public void shouldEncode() {
        Assert.assertArrayEquals(ENCODED_VALUE, CborEncoder.encodeToBytes(VALUE));
    }

    @Test
    public void shouldDecode() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(ENCODED_VALUE);
        CborDecoder decoder = new CborDecoder(inputStream);
        decoder.setAutoDecodeInfinitiveArrays(false);
        List<DataItem> dataItems = decoder.decode();
        Assert.assertArrayEquals(VALUE.toArray(), dataItems.toArray());
    }

}
