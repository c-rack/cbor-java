package co.nstant.in.cbor.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.HalfPrecisionFloat;

public abstract class AbstractHalfPrecisionFloatTest {

    private final float value;
    private final byte[] encodedValue;

    public AbstractHalfPrecisionFloatTest(float value, byte[] encodedValue) {
        this.value = value;
        this.encodedValue = encodedValue;
    }

    @Test
    public void shouldEncode() throws CborException {
        Assert.assertArrayEquals(encodedValue, new HalfPrecisionFloat(value).encodeToBytes());
    }

    @Test
    public void shouldDecode() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(encodedValue);
        CborDecoder decoder = new CborDecoder(inputStream);
        DataItem dataItem = decoder.decodeNext();
        Assert.assertTrue(dataItem instanceof HalfPrecisionFloat);
        HalfPrecisionFloat halfPrecisionFloat = (HalfPrecisionFloat) dataItem;
        Assert.assertEquals(value, halfPrecisionFloat.getValue(), 0);
    }

}
