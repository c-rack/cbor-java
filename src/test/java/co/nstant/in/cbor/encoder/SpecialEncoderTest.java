package co.nstant.in.cbor.encoder;

import java.io.ByteArrayOutputStream;

import org.junit.Test;

import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.Special;
import co.nstant.in.cbor.model.SpecialType;

public class SpecialEncoderTest {

    private class Mock extends Special {

        public Mock(SpecialType specialType) {
            super(specialType);
        }

    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectDoublePrecisionFloatImplementation() throws CborException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Special dataItem = new Mock(SpecialType.IEEE_754_DOUBLE_PRECISION_FLOAT);
        new CborEncoder(byteArrayOutputStream).encode(dataItem);
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectHalfPrecisionFloatImplementation() throws CborException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Special dataItem = new Mock(SpecialType.IEEE_754_HALF_PRECISION_FLOAT);
        new CborEncoder(byteArrayOutputStream).encode(dataItem);
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectSinglePrecisionFloatImplementation() throws CborException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Special dataItem = new Mock(SpecialType.IEEE_754_SINGLE_PRECISION_FLOAT);
        new CborEncoder(byteArrayOutputStream).encode(dataItem);
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectSimpleValueImplementation() throws CborException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Special dataItem = new Mock(SpecialType.SIMPLE_VALUE_NEXT_BYTE);
        new CborEncoder(byteArrayOutputStream).encode(dataItem);
    }

}
