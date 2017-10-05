package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.RationalNumber;
import co.nstant.in.cbor.model.Tag;
import co.nstant.in.cbor.model.UnsignedInteger;

public class CborDecoderTest {

    @Test(expected = CborException.class)
    public void shouldThrowCborException() throws CborException {
        CborDecoder cborDecoder = new CborDecoder(new InputStream() {

            @Override
            public int read() throws IOException {
                throw new IOException();
            }

        });
        cborDecoder.decodeNext();
    }

    @Test(expected = CborException.class)
    public void shouldThrowCborException2() throws CborException {
        CborDecoder cborDecoder = new CborDecoder(new InputStream() {

            @Override
            public int read() throws IOException {
                return (8 << 5); // invalid major type
            }

        });
        cborDecoder.decodeNext();
    }

    @Test
    public void shouldSetAutoDecodeInfinitiveMaps() {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { 0, 1, 2 });
        CborDecoder cborDecoder = new CborDecoder(inputStream);
        assertTrue(cborDecoder.isAutoDecodeInfinitiveMaps());
        cborDecoder.setAutoDecodeInfinitiveMaps(false);
        assertFalse(cborDecoder.isAutoDecodeInfinitiveMaps());
    }

    @Test
    public void shouldSetAutoDecodeRationalNumbers() {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { 0, 1, 2 });
        CborDecoder cborDecoder = new CborDecoder(inputStream);
        assertTrue(cborDecoder.isAutoDecodeRationalNumbers());
        cborDecoder.setAutoDecodeRationalNumbers(false);
        assertFalse(cborDecoder.isAutoDecodeRationalNumbers());
    }

    @Test
    public void shouldSetAutoDecodeLanguageTaggedStrings() {
        InputStream inputStream = new ByteArrayInputStream(new byte[] { 0, 1, 2 });
        CborDecoder cborDecoder = new CborDecoder(inputStream);
        assertTrue(cborDecoder.isAutoDecodeLanguageTaggedStrings());
        cborDecoder.setAutoDecodeLanguageTaggedStrings(false);
        assertFalse(cborDecoder.isAutoDecodeLanguageTaggedStrings());
    }

    @Test(expected = CborException.class)
    public void shouldThrowOnRationalNumberDecode1() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(30)
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
    public void shouldThrowOnRationalNumberDecode2() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(30)
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
    public void shouldThrowOnRationalNumberDecode3() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(30)
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
    public void shouldThrowOnRationalNumberDecode4() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(30)
            .addArray().add(1).add(true).end()
            .build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(items);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        CborDecoder decoder = new CborDecoder(bais);
        decoder.decode();
    }

    @Test
    public void shouldDecodeRationalNumber() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(30)
            .addArray().add(1).add(2).end()
            .build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(items);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        CborDecoder decoder = new CborDecoder(bais);
        assertEquals(new RationalNumber(new UnsignedInteger(1), new UnsignedInteger(2)), decoder.decodeNext());
    }

    @Test
    public void shouldDecodeTaggedTags() throws CborException {
        DataItem decoded = CborDecoder.decode(new byte[] {(byte) 0xC1, (byte) 0xC2, 0x02}).get(0);

        Tag outer = new Tag(1);
        Tag inner = new Tag(2);
        UnsignedInteger expected = new UnsignedInteger(2);
        inner.setTag(outer);
        expected.setTag(inner);

        assertEquals(expected, decoded);
    }

    @Test
    public void shouldDecodeTaggedRationalNumber() throws CborException {
        List<DataItem> items = new CborBuilder()
            .addTag(1)
            .addTag(30)
            .addArray().add(1).add(2).end()
            .build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(baos);
        encoder.encode(items);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        CborDecoder decoder = new CborDecoder(bais);

        RationalNumber expected = new RationalNumber(new UnsignedInteger(1), new UnsignedInteger(2));
        expected.getTag().setTag(new Tag(1));
        assertEquals(expected, decoder.decodeNext());
    }
}
