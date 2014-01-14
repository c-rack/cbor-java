package co.nstant.in.cbor.examples;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.Number;

/**
 * [1, 2, 3] -> 0x83010203
 */
public class Example64Test {

	private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0x83, 0x01,
			0x02, 0x03 };

	@Test
	public void shouldEncode() throws CborException {
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		CborEncoder encoder = new CborEncoder(byteOutputStream);
		encoder.encode(new CborBuilder().addArray().add(1).add(2).add(3).end()
				.build());
		Assert.assertArrayEquals(ENCODED_VALUE, byteOutputStream.toByteArray());
	}

	@Test
	public void shouldDecode() throws CborException {
		InputStream inputStream = new ByteArrayInputStream(ENCODED_VALUE);
		CborDecoder decoder = new CborDecoder(inputStream);
		DataItem dataItem = decoder.decodeNext();
		Assert.assertTrue(dataItem instanceof Array);
		Array array = (Array) dataItem;
		Assert.assertEquals(3, array.getDataItems().size());
		Assert.assertEquals(1, ((Number) array.getDataItems().get(0))
				.getValue().intValue());
		Assert.assertEquals(2, ((Number) array.getDataItems().get(1))
				.getValue().intValue());
		Assert.assertEquals(3, ((Number) array.getDataItems().get(2))
				.getValue().intValue());
	}

}
