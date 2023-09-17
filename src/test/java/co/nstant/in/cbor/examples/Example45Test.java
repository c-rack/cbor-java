package co.nstant.in.cbor.examples;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.SimpleValue;

/**
 * simple(16) -> 0xf0
 */
public class Example45Test {

    private static final SimpleValue VALUE = new SimpleValue(16);
    private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0xf0 };

    @Test
    public void shouldEncode() {
        Assert.assertArrayEquals(ENCODED_VALUE, VALUE.encodeToBytes());
    }

    @Test
    public void shouldDecode() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(ENCODED_VALUE);
        CborDecoder decoder = new CborDecoder(inputStream);
        DataItem dataItem = decoder.decodeNext();
        Assert.assertTrue(dataItem instanceof SimpleValue);
        SimpleValue simpleValue = (SimpleValue) dataItem;
        Assert.assertEquals(VALUE, simpleValue);
    }

}
