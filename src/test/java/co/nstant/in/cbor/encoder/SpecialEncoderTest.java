package co.nstant.in.cbor.encoder;

import org.junit.Before;
import org.junit.Test;

import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.Special;
import co.nstant.in.cbor.model.SpecialType;

public class SpecialEncoderTest {

    private class Mock extends Special {

        public Mock(SpecialType specialType) {
            super(specialType);
        }

    }

    private SpecialEncoder encoder;

    @Before
    public void setup() {
        encoder = new SpecialEncoder(null, null);
    }

    @Test(expected = CborException.class)
    public void shouldNotEncodeReserved() throws CborException {
        encoder.encode(new Mock(SpecialType.UNALLOCATED));
    }

    @Test(expected = CborException.class)
    public void shouldVerifyImplementation1() throws CborException {
        encoder.encode(new Mock(SpecialType.IEEE_754_DOUBLE_PRECISION_FLOAT));
    }

    @Test(expected = CborException.class)
    public void shouldVerifyImplementation2() throws CborException {
        encoder.encode(new Mock(SpecialType.IEEE_754_HALF_PRECISION_FLOAT));
    }

    @Test(expected = CborException.class)
    public void shouldVerifyImplementation3() throws CborException {
        encoder.encode(new Mock(SpecialType.IEEE_754_SINGLE_PRECISION_FLOAT));
    }

    @Test(expected = CborException.class)
    public void shouldVerifyImplementation4() throws CborException {
        encoder.encode(new Mock(SpecialType.SIMPLE_VALUE_NEXT_BYTE));
    }

}
