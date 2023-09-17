package co.nstant.in.cbor.builder;

import static org.junit.Assert.assertTrue;

import java.io.OutputStream;

import org.junit.Test;

import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.HalfPrecisionFloat;
import co.nstant.in.cbor.model.SinglePrecisionFloat;

public class AbstractBuilderTest {

    private class TestBuilder extends AbstractBuilder<Object> {

        public TestBuilder() {
            super(null);
        }

        public DataItem testConvert(float value) {
            return convert(value);
        }

        public void testAddChunk() {
            addChunk(null);
        }

    }

    @Test
    public void shouldCallIsHalfPrecisionEnough() {
        TestBuilder builder = new TestBuilder();
        assertTrue(builder.testConvert(0.0f) instanceof HalfPrecisionFloat);
        assertTrue(0.0f == ((HalfPrecisionFloat) builder.testConvert(0.0f)).getValue());
        assertTrue(builder.testConvert(0.3f) instanceof SinglePrecisionFloat);
        assertTrue(0.3f == ((SinglePrecisionFloat) builder.testConvert(0.3f)).getValue());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionOnAddChunk() {
        new TestBuilder().testAddChunk();
    }

}
