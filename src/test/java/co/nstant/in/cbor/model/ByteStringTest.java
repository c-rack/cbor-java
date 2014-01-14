package co.nstant.in.cbor.model;

import org.junit.Test;

import co.nstant.in.cbor.AbstractDataItemTest;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.ByteString;
import co.nstant.in.cbor.model.UnicodeString;

public class ByteStringTest extends AbstractDataItemTest {

    @Test
    public void testByteString() throws CborException {
        shouldEncodeAndDecode("1-byte array", new ByteString(
                        new byte[] { (byte) 0x00 }));
    }

    @Test
    public void testUnicodeString() throws CborException {
        shouldEncodeAndDecode("string", new UnicodeString("hello world"));
    }

}
