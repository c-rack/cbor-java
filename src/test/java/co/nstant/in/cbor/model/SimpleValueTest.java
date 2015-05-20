package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

public class SimpleValueTest {

    @Test
    public void testHashcode() {
        Special superClass1 = new Special(SpecialType.SIMPLE_VALUE);
        Special superClass2 = new Special(SpecialType.SIMPLE_VALUE_NEXT_BYTE);
        for (int i = 1; i < 256; i++) {
            SimpleValue simpleValue = new SimpleValue(i);
            if (i <= 23) {
                assertEquals(simpleValue.hashCode(), superClass1.hashCode() ^ Objects.hashCode(i));
            } else {
                assertEquals(simpleValue.hashCode(), superClass2.hashCode() ^ Objects.hashCode(i));
            }
        }
    }

    @Test
    public void testEquals1() {
        for (int i = 0; i < 256; i++) {
            SimpleValue simpleValue1 = new SimpleValue(i);
            SimpleValue simpleValue2 = new SimpleValue(i);
            assertTrue(simpleValue1.equals(simpleValue2));
        }
    }

    @Test
    public void testEquals2() {
        SimpleValue simpleValue = new SimpleValue(0);
        assertFalse(simpleValue.equals(new SimpleValue(1)));
        assertFalse(simpleValue.equals(null));
        assertFalse(simpleValue.equals(false));
        assertFalse(simpleValue.equals(""));
        assertFalse(simpleValue.equals(1));
        assertFalse(simpleValue.equals(1.1));
    }

}
