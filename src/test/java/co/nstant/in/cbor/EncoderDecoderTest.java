package co.nstant.in.cbor;

import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.NegativeInteger;
import co.nstant.in.cbor.model.UnsignedInteger;

public class EncoderDecoderTest {

	@Test
	public void test() throws CborException {
		UnsignedInteger a = new UnsignedInteger(1);
		NegativeInteger x = new NegativeInteger(-2);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		CborEncoder encoder = new CborEncoder(byteArrayOutputStream);
		encoder.encode(a);
		encoder.encode(x);
		byte[] bytes = byteArrayOutputStream.toByteArray();
		DataItem object = CborDecoder.decode(bytes).get(0);
		Assert.assertEquals(a, object);
	}

	@Test
	public void testTagging() throws CborException {
		UnsignedInteger a = new UnsignedInteger(1);
		NegativeInteger x = new NegativeInteger(-2);

		a.setTag(1);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		CborEncoder encoder = new CborEncoder(byteArrayOutputStream);
		encoder.encode(a);
		encoder.encode(x);
		byte[] bytes = byteArrayOutputStream.toByteArray();
		DataItem object = CborDecoder.decode(bytes).get(0);
		Assert.assertEquals(a, object);
		Assert.assertTrue(object.hasTag());
		Assert.assertEquals(1L, object.getTag().getValue());
	}

}
