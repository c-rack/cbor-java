package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractDoublePrecisionFloatTest;

/**
 * NaN -> 0xfb 7f f8 00 00 00 00 00 00
 */
public class Example39Test extends AbstractDoublePrecisionFloatTest {

    public Example39Test() {
        super(Double.NaN, new byte[] {
                        (byte) 0xfb, 0x7f, (byte) 0xf8, 0x00, 0x00, 0x00, 0x00,
                        0x00, 0x00 });
    }

}
