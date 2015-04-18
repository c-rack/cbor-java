package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.UnsignedInteger;

public class UnsignedIntegerDecoderTest {

    @Test
    public void shouldDecodeBigNumbers() throws CborException {
        BigInteger value = BigInteger.ONE;
        for (int i = 1; i < 64; i++) {
            value = value.shiftLeft(1);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CborEncoder encoder = new CborEncoder(baos);
            encoder.encode(new CborBuilder().add(value).build());
            byte[] encodedBytes = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);
            CborDecoder decoder = new CborDecoder(bais);
            List<DataItem> dataItems = decoder.decode();
            assertNotNull(dataItems);
            assertEquals(1, dataItems.size());
            DataItem dataItem = dataItems.get(0);
            assertTrue(dataItem instanceof UnsignedInteger);
            assertEquals(value, ((UnsignedInteger) dataItem).getValue());
        }
    }

}
