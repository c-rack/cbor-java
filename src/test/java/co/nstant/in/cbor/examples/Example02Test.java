package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractNumberTest;

/**
 * 0 -> 0x00
 */
public class Example02Test extends AbstractNumberTest {

    public Example02Test() {
        super(1, new byte[] { 0x01 });
    }

}
