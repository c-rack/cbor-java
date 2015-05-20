package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

public class TagTest {

    @Test
    public void testHashcode() {
        DataItem superClass = new DataItem(MajorType.TAG);
        for (long i = 0; i < 256; i++) {
            Tag tag = new Tag(i);
            assertEquals(tag.hashCode(), superClass.hashCode() ^ Objects.hashCode(i));
        }
    }

    @Test
    public void testEquals1() {
        for (int i = 0; i < 256; i++) {
            Tag tag1 = new Tag(i);
            Tag tag2 = new Tag(i);
            assertTrue(tag1.equals(tag2));
        }
    }

    @Test
    public void testEquals2() {
        Tag tag = new Tag(0);
        assertTrue(tag.equals(tag));
    }

    @Test
    public void testNotEquals() {
        Tag tag = new Tag(0);
        assertFalse(tag.equals(new Tag(1)));
        assertFalse(tag.equals(null));
        assertFalse(tag.equals(false));
        assertFalse(tag.equals(""));
        assertFalse(tag.equals(1));
        assertFalse(tag.equals(1.1));
    }

    @Test
    public void testToString() {
        assertEquals("Tag(0)", new Tag(0).toString());
        assertEquals("Tag(123)", new Tag(123).toString());
    }

}
