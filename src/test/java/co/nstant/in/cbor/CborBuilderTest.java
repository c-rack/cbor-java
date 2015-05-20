package co.nstant.in.cbor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.Tag;

public class CborBuilderTest {

    @Test
    public void shouldResetDataItems() {
        CborBuilder builder = new CborBuilder();
        builder.add(true);
        builder.add(1.0f);
        assertEquals(2, builder.build().size());
        builder.reset();
        assertEquals(0, builder.build().size());
    }

    @Test
    public void shouldAddTag() {
        CborBuilder builder = new CborBuilder();
        List<DataItem> dataItems = builder.addTag(1234).build();
        assertEquals(1, dataItems.size());
        assertTrue(dataItems.get(0) instanceof Tag);
        assertEquals(1234, ((Tag) dataItems.get(0)).getValue());
    }

}
