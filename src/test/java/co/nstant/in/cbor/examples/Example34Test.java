package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractHalfPrecisionFloatTest;

/**
 * -Infinity -> 0xf9fc00
 */
public class Example34Test extends AbstractHalfPrecisionFloatTest {

    public Example34Test() {
        super(Float.NEGATIVE_INFINITY, new byte[] {
                        (byte) 0xf9, (byte) 0xfc, 0x00 });
    }

}
