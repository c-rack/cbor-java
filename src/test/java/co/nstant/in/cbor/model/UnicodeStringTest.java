package co.nstant.in.cbor.model;

import org.junit.Assert;
import org.junit.Test;

public class UnicodeStringTest {

    @Test
    public void toStringShouldPrintNull() {
        Assert.assertEquals("null", new UnicodeString(null).toString());
    }

    @Test
    public void shouldEqualsSameObject() {
        UnicodeString unicodeString = new UnicodeString("string");
        Assert.assertTrue(unicodeString.equals(unicodeString));
    }

    @Test
    public void shouldEqualsBothNull() {
        UnicodeString unicodeString1 = new UnicodeString(null);
        UnicodeString unicodeString2 = new UnicodeString(null);
        Assert.assertTrue(unicodeString1.equals(unicodeString2));
        Assert.assertTrue(unicodeString2.equals(unicodeString1));
    }

    @Test
    public void shouldEqualsSameValue() {
        UnicodeString unicodeString1 = new UnicodeString("string");
        UnicodeString unicodeString2 = new UnicodeString("string");
        Assert.assertTrue(unicodeString1.equals(unicodeString2));
        Assert.assertTrue(unicodeString2.equals(unicodeString1));
    }

    @Test
    public void shouldHashNull() {
        Assert.assertEquals(0, new UnicodeString(null).hashCode());
    }

    @Test
    public void shouldNotEqualOtherObjects() {
        UnicodeString unicodeString = new UnicodeString("string");
        Assert.assertFalse(unicodeString.equals(null));
        Assert.assertFalse(unicodeString.equals(1));
        Assert.assertFalse(unicodeString.equals("string"));
    }

    @Test
    public void shouldNotEquals() {
        UnicodeString unicodeString1 = new UnicodeString(null);
        UnicodeString unicodeString2 = new UnicodeString("");
        Assert.assertFalse(unicodeString1.equals(unicodeString2));
    }

}
