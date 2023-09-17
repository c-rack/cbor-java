package co.nstant.in.cbor.model;

import org.junit.Assert;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;

public abstract class AbstractDataItemTest {

    protected void shouldEncodeAndDecode(String message, DataItem dataItem) throws CborException {
        byte[] bytes = dataItem.encodeToBytes();
        DataItem object = CborDecoder.decode(bytes).get(0);
        Assert.assertEquals(message, dataItem, object);
    }

}
