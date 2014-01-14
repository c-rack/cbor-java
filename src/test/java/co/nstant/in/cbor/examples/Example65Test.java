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
import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.Number;

/**
 * [1, [2, 3], [4, 5]] -> 0x83 01 82 02 03 82 04 05
 */
public class Example65Test {

	private static final List<DataItem> VALUE = new CborBuilder().addArray()
			.add(1).addArray().add(2).add(3).end().addArray().add(4).add(5)
			.end().end().build();

	private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0x83, 0x01,
			(byte) 0x82, 0x02, 0x03, (byte) 0x82, 0x04, 0x05 };

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
		DataItem dataItem = decoder.decodeNext();
		Assert.assertTrue(dataItem instanceof Array);
		Array array = (Array) dataItem;
		Assert.assertEquals(3, array.getDataItems().size());

		DataItem dataItem1 = array.getDataItems().get(0);
		DataItem dataItem2 = array.getDataItems().get(1);
		DataItem dataItem3 = array.getDataItems().get(2);

		Assert.assertTrue(dataItem1 instanceof Number);
		Assert.assertTrue(dataItem2 instanceof Array);
		Assert.assertTrue(dataItem3 instanceof Array);

		Number number = (Number) dataItem1;
		Array array1 = (Array) dataItem2;
		Array array2 = (Array) dataItem3;

		Assert.assertEquals(1, number.getValue().intValue());
		Assert.assertEquals(2, array1.getDataItems().size());
		Assert.assertEquals(2, array2.getDataItems().size());

		DataItem array1item1 = array1.getDataItems().get(0);
		DataItem array1item2 = array1.getDataItems().get(1);

		Assert.assertTrue(array1item1 instanceof Number);
		Assert.assertTrue(array1item2 instanceof Number);

		Number array1number1 = (Number) array1item1;
		Number array1number2 = (Number) array1item2;

		Assert.assertEquals(2, array1number1.getValue().intValue());
		Assert.assertEquals(3, array1number2.getValue().intValue());

		DataItem array2item1 = array2.getDataItems().get(0);
		DataItem array2item2 = array2.getDataItems().get(1);

		Assert.assertTrue(array2item1 instanceof Number);
		Assert.assertTrue(array2item2 instanceof Number);

		Number array2number1 = (Number) array2item1;
		Number array2number2 = (Number) array2item2;

		Assert.assertEquals(4, array2number1.getValue().intValue());
		Assert.assertEquals(5, array2number2.getValue().intValue());
	}

}
