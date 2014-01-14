package co.nstant.in.cbor.examples;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;

/**
 * [1, [2, 3], [_ 4,5]] -> 0x83 01 82 02 03 9f 04 05 ff
 */
public class Example77Test {

	private static final List<DataItem> VALUE = new CborBuilder().addArray()
			.add(1).addArray().add(2).add(3).end().startArray().add(4).add(5)
			.end().end().build();

	private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0x83, 0x01,
			(byte) 0x82, 0x02, 0x03, (byte) 0x9f, 0x04, 0x05, (byte) 0xff };

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
