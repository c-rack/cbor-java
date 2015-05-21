package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractHalfPrecisionFloatTest;

/**
 * 6.103515625e-05 -> 0xf90400
 */
public class Example29Test extends AbstractHalfPrecisionFloatTest {

    public Example29Test() {
        super(Float.parseFloat("6.103515625e-05"), new byte[] {
                        (byte) 0xf9, 0x04, 0x00 });
    }

}
