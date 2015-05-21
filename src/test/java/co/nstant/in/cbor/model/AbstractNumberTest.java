package co.nstant.in.cbor.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.Number;

public abstract class AbstractNumberTest {

    private final BigInteger value;
    private final byte[] encodedValue;

    public AbstractNumberTest(long value, byte[] encodedValue) {
        this.value = BigInteger.valueOf(value);
        this.encodedValue = encodedValue;
    }

    public AbstractNumberTest(BigInteger value, byte[] encodedValue) {
        this.value = value;
        this.encodedValue = encodedValue;
    }

    @Test
    public void shouldEncode() throws CborException {
        List<DataItem> dataItems = new CborBuilder().add(value).build();
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(byteOutputStream);
        encoder.encode(dataItems.get(0));
        Assert.assertArrayEquals(encodedValue, byteOutputStream.toByteArray());
    }

    @Test
    public void shouldDecode() throws CborException {
        InputStream inputStream = new ByteArrayInputStream(encodedValue);
        CborDecoder decoder = new CborDecoder(inputStream);
        DataItem dataItem = decoder.decodeNext();
        Assert.assertTrue(dataItem instanceof Number);
        Number number = (Number) dataItem;
        Assert.assertEquals(value, number.getValue());
    }

}
