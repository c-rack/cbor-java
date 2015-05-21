package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractStringTest;

/**
 * "\u6c34" -> 0x63e6b0b4
 */
public class Example61Test extends AbstractStringTest {

    public Example61Test() {
        super("\u6c34", new byte[] { 0x63, (byte) 0xe6, (byte) 0xb0,
                        (byte) 0xb4 });
    }

}
