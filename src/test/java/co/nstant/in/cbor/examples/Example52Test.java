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
import co.nstant.in.cbor.model.ByteString;
import co.nstant.in.cbor.model.DataItem;

/**
 * 24(h'6449455446') -> 0xd818456449455446
 */
public class Example52Test {

	private final List<DataItem> VALUE;

	private final byte[] ENCODED_VALUE = new byte[] { (byte) 0xd8, 0x18,
			0x45, 0x64, 0x49, 0x45, 0x54, 0x46 };

	public Example52Test() {
		DataItem di = new ByteString(new byte[] { 0x64, 0x49, 0x45, 0x54, 0x46 });
		di.setTag(24);

		VALUE = new CborBuilder().add(di).build();
	}

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
