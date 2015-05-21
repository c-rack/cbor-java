package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractHalfPrecisionFloatTest;

/**
 * 0.0 -> 0xf90000
 */
public class Example19Test extends AbstractHalfPrecisionFloatTest {

    public Example19Test() {
        super(0.0f, new byte[] { (byte) 0xf9, 0x00, 0x00 });
    }

}
