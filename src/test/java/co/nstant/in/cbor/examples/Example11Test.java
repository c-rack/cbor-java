package co.nstant.in.cbor.examples;

import java.math.BigInteger;

import co.nstant.in.cbor.model.AbstractNumberTest;

/**
 * 18446744073709551615 -> 0x1bffffffffffffffff
 */
public class Example11Test extends AbstractNumberTest {

    public Example11Test() {
        super(new BigInteger("18446744073709551615"),
                        new byte[] { 0x1b, (byte) 0xff, (byte) 0xff,
                                        (byte) 0xff, (byte) 0xff, (byte) 0xff,
                                        (byte) 0xff, (byte) 0xff, (byte) 0xff });
    }

}
