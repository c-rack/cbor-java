package co.nstant.in.cbor.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimpleValueTest {

	@Test
	public void testHashcode() {
		for (int i = 0; i < 256; i++) {
			SimpleValue simpleValue = new SimpleValue(i);
			assertTrue(simpleValue.getValue() == simpleValue.hashCode());
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
		assertFalse(simpleValue.equals(null));
		assertFalse(simpleValue.equals(false));
		assertFalse(simpleValue.equals(""));
		assertFalse(simpleValue.equals(1));
		assertFalse(simpleValue.equals(1.1));
	}

}
