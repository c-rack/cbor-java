package co.nstant.in.cbor;

import org.junit.Test;

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

}
