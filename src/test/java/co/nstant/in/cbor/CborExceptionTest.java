package co.nstant.in.cbor;

import org.junit.Assert;
import org.junit.Test;

public class CborExceptionTest {

    @Test
    public void shouldHaveMessage() {
        CborException cborException = new CborException("message");
        Assert.assertEquals("message", cborException.getMessage());
    }

    @Test
    public void shouldHaveThrowable() {
        Throwable throwable = new Throwable();
        CborException cborException = new CborException(throwable);
        Assert.assertEquals(throwable, cborException.getCause());
    }

    @Test
    public void shouldHaveMessageAndThrowable() {
        Throwable throwable = new Throwable();
        CborException cborException = new CborException("message", throwable);
        Assert.assertEquals("message", cborException.getMessage());
        Assert.assertEquals(throwable, cborException.getCause());
    }

}
