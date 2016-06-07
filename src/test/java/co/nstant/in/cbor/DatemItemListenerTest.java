package co.nstant.in.cbor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.UnsignedInteger;

public class DatemItemListenerTest {

    @Test
    public void shouldDecodeZero() throws CborException {
    	final int[] dataItems = new int[1];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        new CborEncoder(outputStream).encode(new CborBuilder().add(1234).build());
        new CborDecoder(new ByteArrayInputStream(outputStream.toByteArray())).decode(new DataItemListener() {

            @Override
            public void onDataItem(DataItem dataItem) {
                synchronized (dataItems) {
                	++dataItems[0];	
				}
                assertTrue(dataItem instanceof UnsignedInteger);
            }

        });
        assertEquals(1, dataItems[0]);
    }

}
