package co.nstant.in.cbor.decoder;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.SimpleValue;
import co.nstant.in.cbor.model.Special;

public class SimpleValueDecoderTest {

    @Test
    public void shouldDecodeBoolean() throws CborException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(byteArrayOutputStream);
        encoder.encode(SimpleValue.TRUE);
        encoder.encode(SimpleValue.FALSE);
        byte[] encodedBytes = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encodedBytes);
        List<DataItem> dataItems = new CborDecoder(byteArrayInputStream).decode();
        int result = 0;
        int position = 1;
        for (DataItem dataItem : dataItems) {
            position++;
            switch (dataItem.getMajorType()) {
            case SPECIAL:
                Special special = (Special) dataItem;
                switch (special.getSpecialType()) {
                case SIMPLE_VALUE:
                    SimpleValue simpleValue = (SimpleValue) special;
                    switch (simpleValue.getSimpleValueType()) {
                    case FALSE:
                        result += position * 2;
                        break;
                    case TRUE:
                        result += position * 3;
                        break;
                    default:
                        break;
                    }
                    break;
                default:
                    break;
                }
                break;
            default:
                break;
            }
        }
        assertEquals(12, result);
    }

}
