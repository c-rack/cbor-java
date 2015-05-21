package co.nstant.in.cbor.model;

import java.io.ByteArrayOutputStream;

import org.junit.Assert;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;

public abstract class AbstractDataItemTest {

	protected void shouldEncodeAndDecode(String message, DataItem dataItem)
			throws CborException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		CborEncoder encoder = new CborEncoder(byteArrayOutputStream);
		encoder.encode(dataItem);
		byte[] bytes = byteArrayOutputStream.toByteArray();
		DataItem object = CborDecoder.decode(bytes).get(0);
		Assert.assertEquals(message, dataItem, object);
	}

}
