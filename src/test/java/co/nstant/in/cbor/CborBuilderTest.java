package co.nstant.in.cbor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CborBuilderTest {

    @Test
    public void shouldResetDataItems() {
        CborBuilder builder = new CborBuilder();
        builder.add(true);
        builder.add(1.0f);
        assertEquals(2, builder.build().size());
        builder.reset();
        assertEquals(0, builder.build().size());
    }

}
