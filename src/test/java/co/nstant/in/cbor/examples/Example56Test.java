package co.nstant.in.cbor.examples;

import co.nstant.in.cbor.model.AbstractStringTest;


/**
 * "" -> 0x60
 */
public class Example56Test extends AbstractStringTest {

    public Example56Test() {
        super("", new byte[] { 0x60 });
    }

}
