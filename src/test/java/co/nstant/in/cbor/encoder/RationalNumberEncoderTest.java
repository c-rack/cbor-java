package co.nstant.in.cbor.encoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.RationalNumber;
import co.nstant.in.cbor.model.Tag;
import co.nstant.in.cbor.model.UnsignedInteger;

public class RationalNumberEncoderTest {

    private static final UnsignedInteger ONE = new UnsignedInteger(1);
    private static final UnsignedInteger TWO = new UnsignedInteger(2);

    @Test
    public void shouldEncode() throws CborException {
        byte[] bytes = new RationalNumber(ONE, TWO).encodeToBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        CborDecoder decoder = new CborDecoder(inputStream);

        Array expected = new Array();
        expected.setTag(11);
        expected.add(ONE);
        expected.add(TWO);

        Object object = decoder.decodeNext();
        assertTrue(object instanceof Array);
        Array decoded = (Array) object;

        assertEquals(new Tag(30), decoded.getTag());
        assertEquals(2, decoded.getDataItems().size());
        assertEquals(ONE, decoded.getDataItems().get(0));
        assertEquals(TWO, decoded.getDataItems().get(1));
    }

}
