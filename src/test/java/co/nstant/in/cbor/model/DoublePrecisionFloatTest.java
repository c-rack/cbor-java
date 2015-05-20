package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class DoublePrecisionFloatTest {

    @Test
    public void testEquals() {
        DoublePrecisionFloat a = new DoublePrecisionFloat(1.234);
        DoublePrecisionFloat b = new DoublePrecisionFloat(0.333);
        assertEquals(a, a);
        assertEquals(b, b);
    }

    @Test
    public void testNotEquals() {
        DoublePrecisionFloat doublePrecisionFloat = new DoublePrecisionFloat(1.234);
        assertFalse(doublePrecisionFloat.equals(null));
        assertFalse(doublePrecisionFloat.equals(new DoublePrecisionFloat(1.2345)));
    }

    @Test
    public void testHashcode() {
        DoublePrecisionFloat doublePrecisionFloat = new DoublePrecisionFloat(1.234);
        assertEquals(Double.valueOf(1.234).hashCode(), Double.valueOf(doublePrecisionFloat.getValue()).hashCode());
        assertEquals(Double.valueOf(1.234).hashCode(), doublePrecisionFloat.hashCode());
    }

    @Test
    public void testToString() {
        DoublePrecisionFloat doublePrecisionFloat = new DoublePrecisionFloat(1.234);
        assertEquals("1.234", doublePrecisionFloat.toString());
    }

}
