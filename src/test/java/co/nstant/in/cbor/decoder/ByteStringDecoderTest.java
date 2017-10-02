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

public class ByteStringDecoderTest {

    @Test
    public void shouldDecodeChunkedByteString() throws CborException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(new CborBuilder()
            .startByteString()
            .add(new byte[] { '\0' })
            .add(new byte[] { 0x10 })
            .add(new byte[] { 0x13 })
            .end()
            .build());
        byte[] encodedBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);
        CborDecoder decoder = new CborDecoder(bais);
        List<DataItem> dataItems = decoder.decode();
        assertNotNull(dataItems);
        assertEquals(1, dataItems.size());
    }

    @Test
    public void shouldDecodeByteString1K() throws CborException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(new CborBuilder().add(new byte[1024]).build());
        byte[] encodedBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);
        CborDecoder decoder = new CborDecoder(bais);
        List<DataItem> dataItems = decoder.decode();
        assertNotNull(dataItems);
        assertEquals(1, dataItems.size());
    }

    @Test
    public void shouldDecodeByteString1M() throws CborException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(new CborBuilder().add(new byte[1024 * 1024]).build());
        byte[] encodedBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);
        CborDecoder decoder = new CborDecoder(bais);
        List<DataItem> dataItems = decoder.decode();
        assertNotNull(dataItems);
        assertEquals(1, dataItems.size());
    }

    @Test(expected = CborException.class)
    public void shouldThrowOnIncompleteByteString() throws CborException {
        byte[] bytes = new byte[] {0x42, 0x20};
        CborDecoder.decode(bytes);
    }

    @Test(expected = CborException.class)
    public void shouldTrowOnMissingBreak() throws CborException {
        byte[] bytes = new byte[] {0x5f, 0x41, 0x20};
        CborDecoder.decode(bytes);
    }
}
