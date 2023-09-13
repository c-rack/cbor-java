package co.nstant.in.cbor;

import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.MajorType;

public class CborEncoderTest {

    private class Mock extends DataItem {

        public Mock(MajorType majorType) {
            super(majorType);
        }

    }

    private CborEncoder encoder;

    @Before
    public void setup() {
        encoder = new CborEncoder(new ByteArrayOutputStream());
    }

    @Test(expected = CborException.class)
    public void shouldNotEncodeInvalidMajorType() throws CborException {
        encoder.encode(new Mock(MajorType.INVALID));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectUnsignedIntegerImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.UNSIGNED_INTEGER));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectNegativeIntegerImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.NEGATIVE_INTEGER));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectByteStringImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.BYTE_STRING));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectUnicodeStringImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.UNICODE_STRING));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectArrayImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.ARRAY));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectMapImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.MAP));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectTagImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.TAG));
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectSpecialImplementation() throws CborException {
        encoder.encode(new Mock(MajorType.SPECIAL));
    }

}
