package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractDoublePrecisionFloatTest;

/**
 * 1.0e+300 -> 0xfb 7e 37 e4 3c 88 00 75 9c
 */
public class Example27Test extends AbstractDoublePrecisionFloatTest {

    public Example27Test() {
        super(Double.parseDouble("1.0e+300"), new byte[] {
                        (byte) 0xfb, 0x7e, 0x37, (byte) 0xe4, 0x3c,
                        (byte) 0x88, 0x00, 0x75, (byte) 0x9c });
    }

}
