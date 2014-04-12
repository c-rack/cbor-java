package co.nstant.in.cbor.model;

import org.junit.Test;

public class DataItemTest {

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException() {
        new DataItem(null);
    }

}
