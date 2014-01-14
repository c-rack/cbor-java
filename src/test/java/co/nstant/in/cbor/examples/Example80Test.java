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
 * {_ "a": 1, "b": [_ 2, 3]} -> 0xbf61610161629f0203ffff
 */
public class Example80Test {

	private static final List<DataItem> VALUE = new CborBuilder().startMap()
			.put("a", 1).put("b", 2).startArray("b").add(2).add(3).end().end()
			.build();

	private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0xbf, 0x61,
			0x61, 0x01, 0x61, 0x62, (byte) 0x9f, 0x02, 0x03, (byte) 0xff,
			(byte) 0xff };

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
