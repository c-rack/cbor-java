package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractNumberTest;

/**
 * 24 -> 0x1818
 */
public class Example05Test extends AbstractNumberTest {

    public Example05Test() {
        super(24, new byte[] { 0x18, 0x18 });
    }

}
