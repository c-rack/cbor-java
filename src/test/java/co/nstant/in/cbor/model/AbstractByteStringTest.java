package co.nstant.in.cbor.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.ByteString;
import co.nstant.in.cbor.model.DataItem;

public abstract class AbstractByteStringTest {

    private final byte[] value;
    private final byte[] encodedValue;

    public AbstractByteStringTest(byte[] value, byte[] encodedValue) {
        this.value = value;
        this.encodedValue = encodedValue;
    }

    @Test
    public void shouldEncode() {
        Assert.assertArrayEquals(encodedValue, new ByteString(value).encodeToBytes());
    }

    @Test
    public void shouldDecode() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(encodedValue);
        CborDecoder decoder = new CborDecoder(inputStream);
        DataItem dataItem = decoder.decodeNext();
        Assert.assertTrue(dataItem instanceof ByteString);
        ByteString byteString = (ByteString) dataItem;
        Assert.assertArrayEquals(value, byteString.getBytes());
    }

}
