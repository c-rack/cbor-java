package co.nstant.in.cbor.encoder;

import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.model.DataItem;

public class UnicodeStringEncoderTest {

    @Test
    public void shouldEncodeNullString() {
        List<DataItem> dataItems = new CborBuilder().add((String) null).build();
        CborEncoder.encodeToBytes(dataItems);
    }

    @Test
    public void shouldEncodeChunkedString() {
        List<DataItem> dataItems = new CborBuilder().startString("test").end().build();
        CborEncoder.encodeToBytes(dataItems);
    }

}
