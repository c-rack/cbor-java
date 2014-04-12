package co.nstant.in.cbor.model;

import org.junit.Assert;
import org.junit.Test;

public class SpecialTypeTest {

    @Test
    public void shouldDetectUnallocated() {
        Assert.assertTrue(SpecialType.ofByte(28).equals(SpecialType.UNALLOCATED));
        Assert.assertTrue(SpecialType.ofByte(29).equals(SpecialType.UNALLOCATED));
        Assert.assertTrue(SpecialType.ofByte(30).equals(SpecialType.UNALLOCATED));
    }

}
