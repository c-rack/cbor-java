package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import co.nstant.in.cbor.CborException;

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

    @Test
    public void shouldEquals() {
        byte[] bytes = "string".getBytes();
        ByteString byteString = new ByteString(bytes);
        assertTrue(byteString.equals(byteString));
    }

    @Test
    public void shouldNotEquals() {
        byte[] bytes = "string".getBytes();
        ByteString byteString = new ByteString(bytes);
        assertFalse(byteString.equals(new Object()));
    }

    @Test
    public void shouldHashcode() {
        ChunkableDataItem superClass = new ChunkableDataItem(MajorType.BYTE_STRING);
        byte[] bytes = "string".getBytes();
        ByteString byteString = new ByteString(bytes);
        assertEquals(byteString.hashCode(), superClass.hashCode() ^ Arrays.hashCode(bytes));
    }

    @Test
    public void shouldNotClone() {
        byte[] bytes = "see issue #18".getBytes();
        ByteString byteString = new ByteString(bytes);
        assertEquals(byteString.getBytes(), bytes);
    }

}
