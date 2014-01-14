package co.nstant.in.cbor.model;

import org.junit.Test;

import co.nstant.in.cbor.AbstractDataItemTest;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.NegativeInteger;

public class NegativeIntegerTest extends AbstractDataItemTest {

    @Test
    public void shouldEncodeAndDecode() throws CborException {
        long maxInteger = -4_294_967_295L;
        for (long i = -1L; i >= maxInteger; i += (maxInteger / 100_000L)) {
            shouldEncodeAndDecode(String.valueOf(i), new NegativeInteger(i));
        }
        shouldEncodeAndDecode(String.valueOf(maxInteger), new NegativeInteger(
                        maxInteger));
    }

}
