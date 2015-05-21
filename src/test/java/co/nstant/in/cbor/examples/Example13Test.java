package co.nstant.in.cbor.examples;

import java.math.BigInteger;

import co.nstant.in.cbor.model.AbstractNumberTest;

/**
 * -18446744073709551616 -> 0x3bffffffffffffffff
 */
public class Example13Test extends AbstractNumberTest {

    public Example13Test() {
        super(new BigInteger("-18446744073709551616"),
                        new byte[] { (byte) 0x3b, (byte) 0xff, (byte) 0xff,
                                        (byte) 0xff, (byte) 0xff, (byte) 0xff,
                                        (byte) 0xff, (byte) 0xff, (byte) 0xff });
    }

}
