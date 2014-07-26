package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class ArrayTest {

    @Test
    public void testEquals() {
        Array array = new Array();
        assertEquals(array, array);
        assertNotEquals(array, null);
    }

    @Test
    public void testHashcode() {
        Array array1 = new Array().add(new UnicodeString("string"));
        Array array2 = new Array().add(new UnicodeString("string"));
        assertEquals(array1.hashCode(), array2.hashCode());
    }

    @Test
    public void testToString() {
        Array array = new Array().add(new UnicodeString("a"));
        assertEquals("[a]", array.toString());
        array.setChunked(true);
        assertEquals("[_ a]", array.toString());
        array.add(new UnicodeString("b"));
        assertEquals("[_ a, b]", array.toString());
        array.setChunked(false);
        assertEquals("[a, b]", array.toString());
    }

}
