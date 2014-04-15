package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SpecialTest {

	@Test
	public void testToString() {
		Special special = Special.BREAK;
		assertEquals("BREAK", special.toString());
	}

}
