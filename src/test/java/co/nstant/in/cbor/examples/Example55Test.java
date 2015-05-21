package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractByteStringTest;

/**
 * h'01020304' -> 0x4401020304
 */
public class Example55Test extends AbstractByteStringTest {

    public Example55Test() {
        super(new byte[] { 0x01, 0x02, 0x03, 0x04 },
                        new byte[] { 0x44, 0x01, 0x02, 0x03, 0x04 });
    }
}
