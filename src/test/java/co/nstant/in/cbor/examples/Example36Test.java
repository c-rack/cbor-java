package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractSinglePrecisionFloatTest;

/**
 * NaN -> 0xfa7fc00000
 */
public class Example36Test extends AbstractSinglePrecisionFloatTest {

    public Example36Test() {
        super(Float.NaN, new byte[] {
                        (byte) 0xfa, 0x7f, (byte) 0xc0, 0x00, 0x00 });
    }

}
