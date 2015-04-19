package co.nstant.in.cbor.model;

import org.junit.Assert;
import org.junit.Test;

public class MajorTypeTest {

    @Test
    public void shouldParseUnsignedInteger() {
        Assert.assertEquals(MajorType.UNSIGNED_INTEGER,
            MajorType.ofByte(0b000_00000));
    }

    @Test
    public void shouldParseNegativeInteger() {
        Assert.assertEquals(MajorType.NEGATIVE_INTEGER,
            MajorType.ofByte(0b001_00000));
    }

    @Test
    public void shouldParseByteString() {
        Assert.assertEquals(MajorType.BYTE_STRING,
            MajorType.ofByte(0b010_00000));
    }

    @Test
    public void shouldParseUnicodeString() {
        Assert.assertEquals(MajorType.UNICODE_STRING,
            MajorType.ofByte(0b011_00000));
    }

    @Test
    public void shouldParseArray() {
        Assert.assertEquals(MajorType.ARRAY, MajorType.ofByte(0b100_00000));
    }

    @Test
    public void shouldParseMap() {
        Assert.assertEquals(MajorType.MAP, MajorType.ofByte(0b101_00000));
    }

    @Test
    public void shouldParseTag() {
        Assert.assertEquals(MajorType.TAG, MajorType.ofByte(0b110_00000));
    }

    @Test
    public void shouldParseSpecial() {
        Assert.assertEquals(MajorType.SPECIAL, MajorType.ofByte(0b111_00000));
    }

    @Test
    public void shouldReturnInvalidOnInvalidByteValue() {
        Assert.assertEquals(MajorType.INVALID, MajorType.ofByte(0xffffffff));
    }

}
