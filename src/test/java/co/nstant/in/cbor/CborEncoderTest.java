package co.nstant.in.cbor;

import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.MajorType;

public class CborEncoderTest {

    private class InvalidDataItem extends DataItem {

        public InvalidDataItem() {
            super(MajorType.INVALID);
        }

    }

    private CborEncoder encoder;

    @Before
    public void setup() {
        encoder = new CborEncoder(new ByteArrayOutputStream());
    }

    @Test(expected = CborException.class)
    public void shouldNotEncodeInvalidMajorType() throws CborException {
        encoder.encode(new InvalidDataItem());
    }

}
