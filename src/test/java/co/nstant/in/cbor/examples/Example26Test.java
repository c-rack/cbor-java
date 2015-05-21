package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractSinglePrecisionFloatTest;

/**
 * 3.4028234663852886e+38 -> 0xfa 7f 7f ff ff
 */
public class Example26Test extends AbstractSinglePrecisionFloatTest {

    public Example26Test() {
        super(Float.parseFloat("3.4028234663852886e+38"), new byte[] {
                        (byte) 0xfa, 0x7f, (byte) 0x7f, (byte) 0xff,
                        (byte) 0xff });
    }

}
