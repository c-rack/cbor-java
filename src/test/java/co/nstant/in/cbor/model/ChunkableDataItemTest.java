package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ChunkableDataItemTest {

    private class TestDataItem extends ChunkableDataItem {

        protected TestDataItem() {
            super(MajorType.INVALID);
        }

    }

    @Test
    public void testEquals() {
        TestDataItem item1 = new TestDataItem();
        TestDataItem item2 = new TestDataItem();
        assertEquals(item1, item2);
        item1.setChunked(true);
        item2.setChunked(false);
        assertFalse(item1.equals(item2));
        assertFalse(item1.equals(null));
    }

}
