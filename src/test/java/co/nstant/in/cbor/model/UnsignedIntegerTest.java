package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

import co.nstant.in.cbor.CborException;

public class UnsignedIntegerTest extends AbstractDataItemTest {

    @Test
    public void shouldEncodeAndDecode() throws CborException {
        long maxInteger = 4_294_967_295L;
        for (long i = 0L; i < maxInteger; i += (maxInteger / 100_000L)) {
            shouldEncodeAndDecode(String.valueOf(i), new UnsignedInteger(i));
        }
        shouldEncodeAndDecode(String.valueOf(maxInteger), new UnsignedInteger(maxInteger));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAcceptNegativeValues() {
        new UnsignedInteger(-1);
    }

    @Test
    public void testToString() {
        UnsignedInteger unsignedInteger = new UnsignedInteger(BigInteger.ZERO);
        assertEquals("0", unsignedInteger.toString());
    }

    @Test
    public void testEquals1() {
        UnsignedInteger unsignedInteger = new UnsignedInteger(BigInteger.ZERO);
        assertTrue(unsignedInteger.equals(unsignedInteger));
    }

    @Test
    public void testEquals2() {
        for (int i = 0; i < 256; i++) {
            UnsignedInteger unsignedInteger1 = new UnsignedInteger(BigInteger.valueOf(i));
            UnsignedInteger unsignedInteger2 = new UnsignedInteger(BigInteger.valueOf(i));
            assertTrue(unsignedInteger1.equals(unsignedInteger2));
        }
    }

    @Test
    public void testEquals3() {
        UnsignedInteger unsignedInteger = new UnsignedInteger(BigInteger.ZERO);
        assertFalse(unsignedInteger.equals(new Object()));
    }

    @Test
    public void testHashcode() {
        UnsignedInteger unsignedInteger = new UnsignedInteger(BigInteger.ZERO);
        assertEquals(BigInteger.ZERO.hashCode(), unsignedInteger.getValue().hashCode());
    }

}
