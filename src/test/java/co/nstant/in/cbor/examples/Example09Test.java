package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractNumberTest;

/**
 * 1000000 -> 0x1a000f4240
 */
public class Example09Test extends AbstractNumberTest {

    public Example09Test() {
        super(1000000, new byte[] { 0x1a, 0x00, 0x0f, 0x42, 0x40 });
    }

}
