package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractDoublePrecisionFloatTest;

/**
 * Infinity -> 0xfb 7f f0 00 00 00 00 00 00
 */
public class Example38Test extends AbstractDoublePrecisionFloatTest {

    public Example38Test() {
        super(Double.POSITIVE_INFINITY, new byte[] {
                        (byte) 0xfb, 0x7f, (byte) 0xf0, 0x00, 0x00, 0x00, 0x00,
                        0x00, 0x00 });
    }

}
