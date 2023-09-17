package co.nstant.in.cbor.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.DoublePrecisionFloat;

public abstract class AbstractDoublePrecisionFloatTest {

    private final double value;
    private final byte[] encodedValue;

    public AbstractDoublePrecisionFloatTest(double value, byte[] encodedValue) {
        this.value = value;
        this.encodedValue = encodedValue;
    }

    @Test
    public void shouldEncode() {
        List<DataItem> dataItems = new CborBuilder().add(value).build();
        Assert.assertArrayEquals(encodedValue, dataItems.get(0).encodeToBytes());
    }

    @Test
    public void shouldDecode() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(encodedValue);
        CborDecoder decoder = new CborDecoder(inputStream);
        DataItem dataItem = decoder.decodeNext();
        Assert.assertTrue(dataItem instanceof DoublePrecisionFloat);
        DoublePrecisionFloat doublePrecisionFloat = (DoublePrecisionFloat) dataItem;
        Assert.assertEquals(value, doublePrecisionFloat.getValue(), 0);
    }

}
