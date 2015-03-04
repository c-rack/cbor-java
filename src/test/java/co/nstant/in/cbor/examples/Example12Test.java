package co.nstant.in.cbor.examples;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.ByteString;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.Tag;
import co.nstant.in.cbor.model.UnsignedInteger;

/**
 * 18446744073709551616 -> 0xc249010000000000000000
 */
public class Example12Test {

	@Test
	public void shouldEncode() throws CborException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		CborEncoder encoder = new CborEncoder(byteArrayOutputStream);
		encoder.encode(new UnsignedInteger(new BigInteger(
				"18446744073709551616")));
		Assert.assertArrayEquals(new byte[] { (byte) 0xc2, 0x49, 0x01, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
				byteArrayOutputStream.toByteArray());
	}

	@Test
	public void shouldDecode() throws CborException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				new byte[] { (byte) 0xc2, 0x49, 0x01, 0x00, 0x00, 0x00, 0x00,
						0x00, 0x00, 0x00, 0x00 });
		CborDecoder decoder = new CborDecoder(byteArrayInputStream);
		DataItem b = decoder.decodeNext();

		Assert.assertTrue(b.hasTag());
		Tag tag = b.getTag();
		Assert.assertEquals(2, tag.getValue());

		Assert.assertTrue(b instanceof ByteString);
		ByteString byteString = (ByteString) b;
		Assert.assertArrayEquals(new byte[] { (byte) 0x01, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x00 }, byteString.getBytes());
	}

}
