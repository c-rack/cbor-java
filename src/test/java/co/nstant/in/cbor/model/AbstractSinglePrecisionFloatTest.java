package co.nstant.in.cbor.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.SinglePrecisionFloat;

public abstract class AbstractSinglePrecisionFloatTest {

    private final float value;
    private final byte[] encodedValue;

    public AbstractSinglePrecisionFloatTest(float value, byte[] encodedValue) {
        this.value = value;
        this.encodedValue = encodedValue;
    }

    @Test
    public void shouldEncode() {
        Assert.assertArrayEquals(encodedValue, new SinglePrecisionFloat(value).encodeToBytes());
    }

    @Test
    public void shouldDecode() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(encodedValue);
        CborDecoder decoder = new CborDecoder(inputStream);
        DataItem dataItem = decoder.decodeNext();
        Assert.assertTrue(dataItem instanceof SinglePrecisionFloat);
        SinglePrecisionFloat singlePrecisionFloat = (SinglePrecisionFloat) dataItem;
        Assert.assertEquals(value, singlePrecisionFloat.getValue(), 0);
    }

}
