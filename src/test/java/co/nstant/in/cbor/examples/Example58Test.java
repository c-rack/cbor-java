package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractStringTest;

/**
 * "IETF" -> 0x64 49 45 54 46
 */
public class Example58Test extends AbstractStringTest {

    public Example58Test() {
        super("IETF", new byte[] { 0x64, 0x49, 0x45, 0x54, 0x46 });
    }

}
