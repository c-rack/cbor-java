package co.nstant.in.cbor.encoder;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.ByteString;
import co.nstant.in.cbor.model.DataItem;

public class ByteStringEncoderTest {

    @Test
    public void shouldEncodeNullString() throws CborException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<DataItem> dataItems = new CborBuilder()
            .add((ByteString) null)
            .build();
        new CborEncoder(byteArrayOutputStream).encode(dataItems);
    }

    @Test
    public void shouldEncodeChunkedString() throws CborException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<DataItem> dataItems = new CborBuilder()
            .add(new ByteString(null))
            .add(new ByteString("test".getBytes()))
            .startByteString("test".getBytes())
            .end()
            .build();
        new CborEncoder(byteArrayOutputStream).encode(dataItems);
    }

}
