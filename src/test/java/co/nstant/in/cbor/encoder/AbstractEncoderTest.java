package co.nstant.in.cbor.encoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;

public class AbstractEncoderTest {

    @Test(expected = CborException.class)
    public void shouldThrowCborExceptionOnUnderlyingIoException() throws CborException {
        final AtomicInteger counter = new AtomicInteger();
        new CborEncoder(new OutputStream() {

            @Override
            public synchronized void write(int b) throws IOException {
                if (counter.incrementAndGet() == 3) {
                    throw new IOException();
                }
            }

        }).encode(new CborBuilder().startString().add("string").end().build());
    }

}
