package co.nstant.in.cbor.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class DataItemTest {

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException() {
        new DataItem(null);
    }

	@Test
	public void testSetTag_Long() {
		DataItem di = new DataItem(MajorType.UNSIGNED_INTEGER);
		di.setTag(1);
		assertNotNull(di.getTag());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetTag_Long_negative() {
		DataItem di = new DataItem(MajorType.UNSIGNED_INTEGER);
		di.setTag(-1);
	}

	@Test
	public void testSetTag_Tag() {
		DataItem di = new DataItem(MajorType.UNSIGNED_INTEGER);
		di.setTag(new Tag(1));
		assertNotNull(di.getTag());
	}

	@Test(expected = NullPointerException.class)
	public void testSetTag_Tag_null() {
		DataItem di = new DataItem(MajorType.UNSIGNED_INTEGER);
		di.setTag(null);
	}

	@Test
	public void testGetTag() {
		DataItem di = new DataItem(MajorType.UNSIGNED_INTEGER);
		di.setTag(new Tag(1));

		Tag t = di.getTag();
		assertEquals(1L, t.getValue());
	}

	@Test
	public void testHasTag() {
		DataItem di = new DataItem(MajorType.UNSIGNED_INTEGER);
		assertFalse(di.hasTag());

		di.setTag(new Tag(1));
		assertTrue(di.hasTag());
	}

	@Test
	public void testRemoveTag() {
		DataItem di = new DataItem(MajorType.UNSIGNED_INTEGER);
		di.setTag(new Tag(1));
		assertNotNull(di.getTag());

		di.removeTag();
		assertNull(di.getTag());
	}

}
