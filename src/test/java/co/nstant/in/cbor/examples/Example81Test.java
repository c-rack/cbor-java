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
 * ["a", {_ "b": "c"}] -> 0x826161bf61626163ff
 */
public class Example81Test {

	private static final List<DataItem> VALUE = new CborBuilder().addArray()
			.add("a").startMap().put("b", "c").end().end().build();

	private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0x82, 0x61,
			0x61, (byte) 0xbf, 0x61, 0x62, 0x61, 0x63, (byte) 0xff };

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
