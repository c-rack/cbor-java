package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractHalfPrecisionFloatTest;

/**
 * Infinity -> 0xf97c00
 */
public class Example32Test extends AbstractHalfPrecisionFloatTest {

    public Example32Test() {
        super(Float.POSITIVE_INFINITY, new byte[] {
                        (byte) 0xf9, 0x7c, 0x00 });
    }

}
