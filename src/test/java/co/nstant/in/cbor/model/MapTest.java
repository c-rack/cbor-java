package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;

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

    @Test
    public void testItemOrderIsPreserved() throws CborException {
        List<DataItem> input = new CborBuilder().addMap().put(1, "v1").put(0, "v2").end().build();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(byteArrayOutputStream);
        encoder.nonCanonical().encode(input);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        CborDecoder decoder = new CborDecoder(byteArrayInputStream);
        List<DataItem> output = decoder.decode();
        assertEquals(input, output);
        DataItem dataItem = output.get(0);
        assertEquals(MajorType.MAP, dataItem.getMajorType());
        Map map = (Map) dataItem;
        Collection<DataItem> values = map.getValues();
        Iterator<DataItem> iterator = values.iterator();
        assertEquals(new UnicodeString("v1"), iterator.next());
        assertEquals(new UnicodeString("v2"), iterator.next());
    }

}
