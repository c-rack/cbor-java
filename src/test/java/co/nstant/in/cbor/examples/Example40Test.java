package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractDoublePrecisionFloatTest;

/**
 * -Infinity -> 0xfb ff f0 00 00 00 00 00 00
 */
public class Example40Test extends AbstractDoublePrecisionFloatTest {

    public Example40Test() {
        super(Double.NEGATIVE_INFINITY, new byte[] {
                        (byte) 0xfb, (byte) 0xff, (byte) 0xf0, 0x00, 0x00,
                        0x00, 0x00, 0x00, 0x00 });
    }

}
