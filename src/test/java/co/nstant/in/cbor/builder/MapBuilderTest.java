package co.nstant.in.cbor.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.Map;
import co.nstant.in.cbor.model.UnicodeString;
import co.nstant.in.cbor.model.UnsignedInteger;

public class MapBuilderTest {

    @Test
    public void testMapBuilder() {
        CborBuilder builder = new CborBuilder();
        List<DataItem> dataItems = builder.addMap()
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
            .putMap("14")
            .end()
            .putMap(new UnsignedInteger(15))
            .end()
            .putArray(16)
            .end()
            .putArray("17")
            .end()
            .putArray(new UnsignedInteger(18))
            .end()
            .end()
            .startMap()
            .startArray(1).end()
            .startArray(new UnsignedInteger(2)).end()
            .end()
            .build();
        assertEquals(2, dataItems.size());
        assertTrue(dataItems.get(0) instanceof Map);
        Map map = (Map) dataItems.get(0);
        assertEquals(19, map.getKeys().size());
    }

}
