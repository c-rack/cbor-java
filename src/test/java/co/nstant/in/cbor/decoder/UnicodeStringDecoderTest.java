package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;

public class UnicodeStringDecoderTest {

    @Test
    public void shouldDecodeChunkedUnicodeString() throws CborException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(new CborBuilder()
            .startString()
            .add("foo")
            .add("bar")
            .end()
            .build());
        byte[] encodedBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);
        CborDecoder decoder = new CborDecoder(bais);
        List<DataItem> dataItems = decoder.decode();
        assertNotNull(dataItems);
        assertEquals(1, dataItems.size());
    }

    @Test(expected = CborException.class)
    public void shouldThrowOnIncompleteString() throws CborException {
        byte[] bytes = new byte[] {0x62, 0x61};
        CborDecoder.decode(bytes);
    }

    @Test(expected = CborException.class)
    public void shouldThrowOnMissingBreak() throws CborException {
        byte[] bytes = new byte[] {0x7f, 0x61, 0x61};
        CborDecoder.decode(bytes);
    }

}
