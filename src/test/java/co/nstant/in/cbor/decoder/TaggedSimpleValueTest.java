package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.SimpleValue;

public class TaggedSimpleValueTest {

    private static byte[] encode(DataItem dataItem) throws CborException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new CborEncoder(byteArrayOutputStream).encode(dataItem);
        return byteArrayOutputStream.toByteArray();
    }

    @Test
    public void decodingATaggedSimpleValueShouldNotTagTheSharedConstants() throws CborException {
        // 1234(false), 1234(true), 1234(null), 1234(undefined)
        for (int simple = 0xf4; simple <= 0xf7; simple++) {
            DataItem tagged = CborDecoder.decode(new byte[] { (byte) 0xd9, 0x04, (byte) 0xd2, (byte) simple }).get(0);
            assertTrue(tagged.hasTag());
            assertEquals(1234, tagged.getTag().getValue());

            DataItem plain = CborDecoder.decode(new byte[] { (byte) simple }).get(0);
            assertFalse(plain.hasTag());
            assertArrayEquals(new byte[] { (byte) simple }, encode(plain));
        }
        assertFalse(SimpleValue.FALSE.hasTag());
        assertFalse(SimpleValue.TRUE.hasTag());
        assertFalse(SimpleValue.NULL.hasTag());
        assertFalse(SimpleValue.UNDEFINED.hasTag());
        assertArrayEquals(new byte[] { (byte) 0xf5 }, encode(SimpleValue.TRUE));
    }

    @Test
    public void taggingABuiltBooleanShouldNotTagTheSharedConstants() throws CborException {
        List<DataItem> dataItems = new CborBuilder().add(true).tagged(1234).add(false).tagged(5678).build();
        assertEquals(1234, dataItems.get(0).getTag().getValue());
        assertEquals(5678, dataItems.get(1).getTag().getValue());
        assertFalse(SimpleValue.TRUE.hasTag());
        assertFalse(SimpleValue.FALSE.hasTag());
        assertFalse(new CborBuilder().add(true).build().get(0).hasTag());
    }

}
