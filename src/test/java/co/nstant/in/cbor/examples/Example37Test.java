package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractSinglePrecisionFloatTest;

/**
 * -Infinity -> 0xfa ff 80 00 00
 */
public class Example37Test extends AbstractSinglePrecisionFloatTest {

    public Example37Test() {
        super(Float.NEGATIVE_INFINITY, new byte[] {
                        (byte) 0xfa, (byte) 0xff, (byte) 0x80, 0x00, 0x00 });
    }

}
