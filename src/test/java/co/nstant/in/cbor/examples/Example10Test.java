package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractNumberTest;

/**
 * 1000000000000 -> 0x1b 00 00 00 e8 d4 a5 10 00
 */
public class Example10Test extends AbstractNumberTest {

    public Example10Test() {
        super(1000000000000L, new byte[] { 0x1b, 0x00, 0x00, 0x00,
                        (byte) 0xe8, (byte) 0xd4, (byte) 0xa5, 0x10, 0x00 });
    }

}
