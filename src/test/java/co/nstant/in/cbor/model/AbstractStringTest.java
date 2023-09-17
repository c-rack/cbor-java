package co.nstant.in.cbor.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.UnicodeString;

public abstract class AbstractStringTest {

    private final String value;
    private final byte[] encodedValue;

    public AbstractStringTest(String value, byte[] encodedValue) {
        this.value = value;
        this.encodedValue = encodedValue;
    }

    @Test
    public void shouldEncode() {
        Assert.assertArrayEquals(encodedValue, new UnicodeString(value).encodeToBytes());
    }

    @Test
    public void shouldDecode() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(encodedValue);
        CborDecoder decoder = new CborDecoder(inputStream);
        DataItem dataItem = decoder.decodeNext();
        Assert.assertTrue(dataItem instanceof UnicodeString);
        UnicodeString unicodeString = (UnicodeString) dataItem;
        Assert.assertEquals(value, unicodeString.toString());
    }

}
