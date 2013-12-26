package co.nstant.in.cbor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;

/**
 * 1(1363896240.5) -> 0xc1fb41d452d9ec200000
 */
public class Example50Test {

	private static final List<DataItem> VALUE = new CborBuilder().addTag(1)
			.add(1363896240.5).build();

	private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0xc1,
			(byte) 0xfb, 0x41, (byte) 0xd4, 0x52, (byte) 0xd9, (byte) 0xec,
			0x20, 0x00, 0x00 };

	@Test
	public void shouldEncode() throws CborException {
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		CborEncoder encoder = new CborEncoder(byteOutputStream);
		encoder.encode(VALUE);
		Assert.assertArrayEquals(ENCODED_VALUE, byteOutputStream.toByteArray());
	}

	@Test
	public void shouldDecode() throws CborException {
		InputStream inputStream = new ByteArrayInputStream(ENCODED_VALUE);
		CborDecoder decoder = new CborDecoder(inputStream);
		List<DataItem> dataItems = decoder.decode();
		Assert.assertArrayEquals(VALUE.toArray(), dataItems.toArray());
	}

}
