package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractSinglePrecisionFloatTest;

/**
 * 100000.0 -> 0xfa47c35000
 */
public class Example25Test extends AbstractSinglePrecisionFloatTest {

    public Example25Test() {
        super(100000.0f, new byte[] { (byte) 0xfa, 0x47, (byte) 0xc3, 0x50,
                        0x00 });
    }

}
