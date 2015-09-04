package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.LanguageTaggedString;
import co.nstant.in.cbor.model.UnicodeString;

public class LanguageTaggedStringDecoderTest {

    // Unexpected end of stream, tag without data item
    @Test(expected = CborException.class)
    public void shouldThrowException() throws CborException {
        List<DataItem> items = new CborBuilder().addTag(38).build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(items);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        CborDecoder decoder = new CborDecoder(bais);
        decoder.decode();
    }

    @Test(expected = CborException.class)
    public void testExceptionOnNotAnArray() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(38)
            .add(true)
            .build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(items);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        CborDecoder decoder = new CborDecoder(bais);
        decoder.decode();
    }

    @Test(expected = CborException.class)
    public void testExceptionOnNot2ElementArray() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(38)
            .addArray().add(true).end()
            .build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(items);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        CborDecoder decoder = new CborDecoder(bais);
        decoder.decode();
    }

    @Test(expected = CborException.class)
    public void testExceptionOnNotFirstElementIsString() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(38)
            .addArray().add(true).add(true).end()
            .build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(items);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        CborDecoder decoder = new CborDecoder(bais);
        decoder.decode();
    }

    @Test(expected = CborException.class)
    public void testExceptionOnNotSecondElementIsString() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(38)
            .addArray().add("en").add(true).end()
            .build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(items);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        CborDecoder decoder = new CborDecoder(bais);
        decoder.decode();
    }

    @Test
    public void testDecoding() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(38)
            .addArray().add("en").add("string").end()
            .build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(items);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        CborDecoder decoder = new CborDecoder(bais);
        DataItem item = decoder.decodeNext();
        assertEquals(new LanguageTaggedString(new UnicodeString("en"), new UnicodeString("string")), item);
    }

}
