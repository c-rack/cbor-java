package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class MapTest {

    @Test
    public void testRemove() {
        UnicodeString key = new UnicodeString("key");
        UnicodeString value = new UnicodeString("value");
        Map map = new Map();
        map.put(key, value);
        assertEquals(1, map.getValues().size());
        map.remove(key);
        assertEquals(0, map.getValues().size());
    }

    @Test
    public void testEquals() {
        Map map1 = new Map();
        assertEquals(map1, map1);
        assertNotEquals(map1, new Object());
    }

    @Test
    public void testHashcode() {
        Map map1 = new Map();
        Map map2 = new Map();
        assertEquals(map1.hashCode(), map2.hashCode());
        map1.put(new UnicodeString("key"), new UnicodeString("value"));
        assertNotEquals(map1.hashCode(), map2.hashCode());
    }

    @Test
    public void testToString() {
        Map map = new Map();
        assertEquals("{  }", map.toString());
        map.put(new UnicodeString("key1"), new UnicodeString("value1"));
        assertEquals("{ key1: value1 }", map.toString());
        map.put(new UnicodeString("key2"), new UnicodeString("value2"));
        assertEquals("{ key1: value1, key2: value2 }", map.toString());
        map.setChunked(true);
        assertEquals("{_ key1: value1, key2: value2 }", map.toString());
    }

}
