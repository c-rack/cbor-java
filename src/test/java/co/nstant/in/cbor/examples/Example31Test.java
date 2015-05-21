package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractDoublePrecisionFloatTest;

/**
 * -4.1 -> 0xfb c0 10 66 66 66 66 66 66
 */
public class Example31Test extends AbstractDoublePrecisionFloatTest {

    public Example31Test() {
        super(Double.parseDouble("-4.1"), new byte[] {
                        (byte) 0xfb, (byte) 0xc0, 0x10, 0x66, 0x66, 0x66, 0x66,
                        0x66, 0x66 });
    }

}
