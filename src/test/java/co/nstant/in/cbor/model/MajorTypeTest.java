package co.nstant.in.cbor.model;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborException;

public class MajorTypeTest {

    @Test
    public void shouldParseUnsignedInteger() throws CborException {
        Assert.assertEquals(MajorType.UNSIGNED_INTEGER, MajorType.ofByte(0b000_00000));
    }

    @Test
    public void shouldParseNegativeInteger() throws CborException {
        Assert.assertEquals(MajorType.NEGATIVE_INTEGER, MajorType.ofByte(0b001_00000));
    }

    @Test
    public void shouldParseByteString() throws CborException {
        Assert.assertEquals(MajorType.BYTE_STRING, MajorType.ofByte(0b010_00000));
    }

    @Test
    public void shouldParseUnicodeString() throws CborException {
        Assert.assertEquals(MajorType.UNICODE_STRING, MajorType.ofByte(0b011_00000));
    }

    @Test
    public void shouldParseArray() throws CborException {
        Assert.assertEquals(MajorType.ARRAY, MajorType.ofByte(0b100_00000));
    }

    @Test
    public void shouldParseMap() throws CborException {
        Assert.assertEquals(MajorType.MAP, MajorType.ofByte(0b101_00000));
    }

    @Test
    public void shouldParseTag() throws CborException {
        Assert.assertEquals(MajorType.TAG, MajorType.ofByte(0b110_00000));
    }

    @Test
    public void shouldParseSpecial() throws CborException {
        Assert.assertEquals(MajorType.SPECIAL, MajorType.ofByte(0b111_00000));
    }

    @Test(expected = CborException.class)
    public void shouldReturnThrowOnInvalidByteValue() throws CborException {
        MajorType.ofByte(0xffffffff);
    }

}
