package co.nstant.in.cbor.encoder;

import org.junit.Test;

import co.nstant.in.cbor.model.Special;
import co.nstant.in.cbor.model.SpecialType;

public class SpecialEncoderTest {

    private class Mock extends Special {

        public Mock(SpecialType specialType) {
            super(specialType);
        }

    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectDoublePrecisionFloatImplementation() {
        new Mock(SpecialType.IEEE_754_DOUBLE_PRECISION_FLOAT).encodeToBytes();
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectHalfPrecisionFloatImplementation() {
         new Mock(SpecialType.IEEE_754_HALF_PRECISION_FLOAT).encodeToBytes();
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectSinglePrecisionFloatImplementation() {
        new Mock(SpecialType.IEEE_754_SINGLE_PRECISION_FLOAT).encodeToBytes();
    }

    @Test(expected = ClassCastException.class)
    public void shouldExpectSimpleValueImplementation() {
        new Mock(SpecialType.SIMPLE_VALUE_NEXT_BYTE).encodeToBytes();
    }

}
