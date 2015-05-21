package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractHalfPrecisionFloatTest;

/**
 * Example 20: -0.0 -> 0xf98000
 */
public class Example20Test extends AbstractHalfPrecisionFloatTest {

    public Example20Test() {
        super(-0.0f, new byte[] { (byte) 0xf9, (byte) 0x80, 0x00 });
    }

}
