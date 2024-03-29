package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.ByteString;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.MajorType;

public class ByteStringDecoderTest {

    @Test
    public void shouldDecodeChunkedByteString() throws CborException {
        byte[] encodedBytes = CborEncoder.encodeToBytes(new CborBuilder().startByteString()
            .add(new byte[] { '\0' }).add(new byte[] { 0x10 }).add(new byte[] { 0x13 }).end().build());
        ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);
        CborDecoder decoder = new CborDecoder(bais);
        List<DataItem> dataItems = decoder.decode();
        assertNotNull(dataItems);
        assertEquals(1, dataItems.size());
    }

    @Test
    public void shouldDecodeByteString1K() throws CborException {
        byte[] encodedBytes = CborEncoder.encodeToBytes(new CborBuilder().add(new byte[1024]).build());
        ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);
        CborDecoder decoder = new CborDecoder(bais);
        List<DataItem> dataItems = decoder.decode();
        assertNotNull(dataItems);
        assertEquals(1, dataItems.size());
    }

    @Test
    public void shouldDecodeByteString1M() throws CborException {
        byte[] encodedBytes = CborEncoder.encodeToBytes(new CborBuilder().add(new byte[1024 * 1024]).build());
        ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);
        CborDecoder decoder = new CborDecoder(bais);
        List<DataItem> dataItems = decoder.decode();
        assertNotNull(dataItems);
        assertEquals(1, dataItems.size());
    }

    @Test(expected = CborException.class)
    public void shouldThrowOnIncompleteByteString() throws CborException {
        byte[] bytes = new byte[] { 0x42, 0x20 };
        CborDecoder.decode(bytes);
    }

    @Test(expected = CborException.class)
    public void shouldTrowOnMissingBreak() throws CborException {
        byte[] bytes = new byte[] { 0x5f, 0x41, 0x20 };
        CborDecoder.decode(bytes);
    }

    public void decodingExample() throws CborException {
        byte bytes[] = { 0, 1, 2, 3 };
        // Encode
        byte[] encodedBytes = new ByteString(bytes).encodeToBytes();
        // Decode
        ByteArrayInputStream inputStream = new ByteArrayInputStream(encodedBytes);
        CborDecoder decoder = new CborDecoder(inputStream);
        DataItem dataItem = decoder.decodeNext();
        assertEquals(MajorType.BYTE_STRING, dataItem.getMajorType());
        ByteString byteString = (ByteString) dataItem;
        byte[] decodedBytes = byteString.getBytes();
        // Verify
        assertEquals(bytes.length, decodedBytes.length);
        assertEquals(bytes[0], decodedBytes[0]);
        assertEquals(bytes[1], decodedBytes[1]);
        assertEquals(bytes[2], decodedBytes[2]);
        assertEquals(bytes[3], decodedBytes[3]);
    }

}
