package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

}
