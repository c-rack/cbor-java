package co.nstant.in.cbor.model;

import org.junit.Test;

import co.nstant.in.cbor.CborException;

public class NegativeIntegerTest extends AbstractDataItemTest {

    @Test
    public void shouldEncodeAndDecode() throws CborException {
        long maxInteger = -4_294_967_295L;
        for (long i = -1L; i >= maxInteger; i += (maxInteger / 100_000L)) {
            shouldEncodeAndDecode(String.valueOf(i), new NegativeInteger(i));
        }
        shouldEncodeAndDecode(String.valueOf(maxInteger), new NegativeInteger(maxInteger));

        // Test for issue #1: Creation of 64-bit NegativeInteger >= -4294967296L fails
        shouldEncodeAndDecode("Long.MIN_VALUE", new NegativeInteger(Long.MIN_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAcceptPositiveValues() {
        new NegativeInteger(0);
    }

}
