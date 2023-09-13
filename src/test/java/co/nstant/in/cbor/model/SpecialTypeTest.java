package co.nstant.in.cbor.model;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborException;

public class SpecialTypeTest {

    @Test(expected = CborException.class)
    public void shouldDetectUnallocated28() throws CborException {
        SpecialType.ofByte(28);
    }

    @Test(expected = CborException.class)
    public void shouldDetectUnallocated29() throws CborException {
        SpecialType.ofByte(29);
    }

    @Test(expected = CborException.class)
    public void shouldDetectUnallocated30() throws CborException {
        SpecialType.ofByte(30);
    }

}
