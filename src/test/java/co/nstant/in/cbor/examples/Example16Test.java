package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractNumberTest;

/**
 * -10 -> 0x29
 */
public class Example16Test extends AbstractNumberTest {

    public Example16Test() {
        super(-10, new byte[] { 0x29 });
    }

}
