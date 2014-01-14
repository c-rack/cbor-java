package co.nstant.in.cbor.model;

import org.junit.Test;

import co.nstant.in.cbor.AbstractDataItemTest;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.UnsignedInteger;

public class UnsignedIntegerTest extends AbstractDataItemTest {

    @Test
    public void shouldEncodeAndDecode() throws CborException {
        long maxInteger = 4_294_967_295L;
        for (long i = 0L; i < maxInteger; i += (maxInteger / 100_000L)) {
            shouldEncodeAndDecode(String.valueOf(i), new UnsignedInteger(i));
        }
        shouldEncodeAndDecode(String.valueOf(maxInteger), new UnsignedInteger(
                        maxInteger));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAcceptNegativeValues() {
        new UnsignedInteger(-1);
    }

}
