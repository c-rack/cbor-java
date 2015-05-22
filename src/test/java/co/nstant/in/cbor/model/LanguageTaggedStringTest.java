package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LanguageTaggedStringTest {

    @Test
    public void shouldInitializeWithStrings() {
        LanguageTaggedString lts = new LanguageTaggedString("en", "Hello");
        assertEquals(38, lts.getTag().getValue());
        assertEquals("en", lts.getLanguage().getString());
        assertEquals("Hello", lts.getString().getString());
    }

}
