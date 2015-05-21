package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractDoublePrecisionFloatTest;

/**
 * Example 22: 1.1 -> 0xfb 3f f1 99 99 99 99 99 9a
 */
public class Example22Test extends AbstractDoublePrecisionFloatTest {

    public Example22Test() {
        super(1.1d, new byte[] { (byte) 0xfb, 0x3f, (byte) 0xf1, (byte) 0x99,
                        (byte) 0x99, (byte) 0x99, (byte) 0x99, (byte) 0x99,
                        (byte) 0x9a });
    }

}
