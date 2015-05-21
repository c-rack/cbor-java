package co.nstant.in.cbor.decoder;

import org.junit.Test;

import co.nstant.in.cbor.CborException;

public class SpecialDecoderTest {

    @Test(expected = CborException.class)
    public void shouldThrowExceptionOnUnallocated() throws CborException {
        SpecialDecoder decoder = new SpecialDecoder(null, null);
        decoder.decode(28);
    }

}
