package co.nstant.in.cbor.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import co.nstant.in.cbor.model.*;
import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;

public class MapBuilderTest {

    @Test
    public void testMapBuilder() {
        List<DataItem> dataItems = new CborBuilder()
                .addMap()
                    .put(new UnicodeString("key"), new UnicodeString("value"))
                    .put(1, true)
                    .put(2, "value".getBytes())
                    .put(3, 1.0d)
                    .put(4, 1.0f)
                    .put(5, 1L)
                    .put(6, "value")
                    .put("7", true)
                    .put("8", "value".getBytes())
                    .put("9", 1.0d)
                    .put("10", 1.0f)
                    .put("11", 1L)
                    .put("12", "value")
                    .putMap(13)
                    .end()
                    .putMap("14").end()
                    .putMap(new UnsignedInteger(15)).end()
                    .putArray(16).end()
                    .putArray("17").end()
                    .putArray(new UnsignedInteger(18)).end()
                    .addKey(19).value(true)
                    .addKey(20).value("value".getBytes())
                    .addKey(21).value(1.0d)
                    .addKey(22).value(1.0f)
                    .addKey(23).value(1L)
                    .addKey(24).value("value")
                    .addKey("25").value(true)
                    .addKey("26").value("value".getBytes())
                    .addKey("27").value(1.0d)
                    .addKey("28").value(1.0f)
                    .addKey("29").value(1L)
                    .addKey("30").value("value")
                    .addKey("31").value(SimpleValue.NULL)
                .end()
                .startMap()
                    .startArray(1).end()
                    .startArray(new UnsignedInteger(2)).end()
                .end()
                .build();
        assertEquals(2, dataItems.size());
        assertTrue(dataItems.get(0) instanceof Map);
        Map map = (Map) dataItems.get(0);
        assertEquals(32, map.getKeys().size());
    }

    @Test
    public void startMapInMap() {
        CborBuilder builder = new CborBuilder();
        List<DataItem> dataItems = builder.addMap().startMap(new ByteString(new byte[] { 0x01 })).put(1, 2).end()
            .startMap(1).end().startMap("asdf").end().end().build();
        Map rootMap = (Map) dataItems.get(0);
        DataItem keys[] = new DataItem[3];
        rootMap.getKeys().toArray(keys);
        assertEquals(keys[0], new ByteString(new byte[] { 0x01 }));
        assertEquals(keys[1], new UnsignedInteger(1));
        assertEquals(keys[2], new UnicodeString("asdf"));

        assertTrue(rootMap.get(keys[0]) instanceof Map);
        assertTrue(rootMap.get(keys[1]) instanceof Map);
        assertTrue(rootMap.get(keys[2]) instanceof Map);
    }

}
