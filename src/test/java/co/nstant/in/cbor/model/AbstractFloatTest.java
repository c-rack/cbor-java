package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Objects;

import org.junit.Test;

public class AbstractFloatTest {

    @Test
    public void testEquals() {
        AbstractFloat a = new AbstractFloat(SpecialType.IEEE_754_SINGLE_PRECISION_FLOAT, 0.0f);
        AbstractFloat b = new AbstractFloat(SpecialType.IEEE_754_SINGLE_PRECISION_FLOAT, 0.3f);
        assertEquals(a, a);
        assertEquals(b, b);
        assertFalse(a.equals(b));
        assertFalse(a.equals(null));
        assertFalse(a.equals("test"));
    }

    @Test
    public void testHashcode() {
        Special superClass = new Special(SpecialType.IEEE_754_SINGLE_PRECISION_FLOAT);
        AbstractFloat f = new AbstractFloat(SpecialType.IEEE_754_SINGLE_PRECISION_FLOAT, 0.0f);
        assertEquals(superClass.hashCode() ^ Objects.hashCode(0.0f), f.hashCode());
    }

}
