package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractSinglePrecisionFloatTest;

/**
 * Infinity -> 0xfa 7f 80 00 00
 */
public class Example35Test extends AbstractSinglePrecisionFloatTest {

    public Example35Test() {
        super(Float.POSITIVE_INFINITY, new byte[] {
                        (byte) 0xfa, 0x7f, (byte) 0x80, 0x00, 0x00 });
    }

}
