package co.nstant.in.cbor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.UnsignedInteger;

public class DatemItemListenerTest {

    @Test
    public void shouldDecodeZero() throws CborException {
        final AtomicInteger dataItems = new AtomicInteger(0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        new CborEncoder(outputStream).encode(new CborBuilder().add(1234).build());
        new CborDecoder(new ByteArrayInputStream(outputStream.toByteArray())).decode(new DataItemListener() {

            @Override
            public void onDataItem(DataItem dataItem) {
                dataItems.incrementAndGet();
                assertTrue(dataItem instanceof UnsignedInteger);
            }

        });
        assertEquals(1, dataItems.intValue());
    }

}
