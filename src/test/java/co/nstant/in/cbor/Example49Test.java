package co.nstant.in.cbor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;

/**
 * 1(1363896240) -> 0xc11a514b67b0
 */
public class Example49Test {

	private static final List<DataItem> VALUE = new CborBuilder().addTag(1)
			.add(1363896240).build();

	private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0xc1, 0x1a,
			0x51, 0x4b, 0x67, (byte) 0xb0 };

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
