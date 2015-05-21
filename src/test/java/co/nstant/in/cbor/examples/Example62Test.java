package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractStringTest;

/**
 * "\ud800\udd51" -> 0x64f0908591
 */
public class Example62Test extends AbstractStringTest {

    public Example62Test() {
        super("\ud800\udd51", new byte[] { 0x64, (byte) 0xf0, (byte) 0x90,
                        (byte) 0x85, (byte) 0x91 });
    }

}
