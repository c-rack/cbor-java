package co.nstant.in.cbor.builder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.decoder.HalfPrecisionFloatDecoder;
import co.nstant.in.cbor.encoder.HalfPrecisionFloatEncoder;
import co.nstant.in.cbor.model.ByteString;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.DoublePrecisionFloat;
import co.nstant.in.cbor.model.HalfPrecisionFloat;
import co.nstant.in.cbor.model.NegativeInteger;
import co.nstant.in.cbor.model.SimpleValue;
import co.nstant.in.cbor.model.SinglePrecisionFloat;
import co.nstant.in.cbor.model.Tag;
import co.nstant.in.cbor.model.UnicodeString;
import co.nstant.in.cbor.model.UnsignedInteger;

public abstract class AbstractBuilder<T> {

    private final T parent;

    public AbstractBuilder(T parent) {
        this.parent = parent;
    }

    protected T getParent() {
        return parent;
    }

    protected void addChunk(DataItem dataItem) {
        throw new NotImplementedException();
    }

    protected DataItem convert(long value) {
        if (value >= 0) {
            return new UnsignedInteger(value);
        } else {
            return new NegativeInteger(value);
        }
    }

    protected DataItem convert(BigInteger value) {
        if (value.signum() == -1) {
            return new NegativeInteger(value);
        } else {
            return new UnsignedInteger(value);
        }
    }

    protected DataItem convert(boolean value) {
        if (value) {
            return SimpleValue.TRUE;
        } else {
            return SimpleValue.FALSE;
        }
    }

    protected DataItem convert(byte[] bytes) {
        return new ByteString(bytes);
    }

    protected DataItem convert(String string) {
        return new UnicodeString(string);
    }

    protected DataItem convert(float value) {
        if (isHalfPrecisionEnough(value)) {
            return new HalfPrecisionFloat(value);
        } else {
            return new SinglePrecisionFloat(value);
        }
    }

    protected DataItem convert(double value) {
        return new DoublePrecisionFloat(value);
    }

    protected Tag tag(long value) {
        return new Tag(value);
    }

    private boolean isHalfPrecisionEnough(float value) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HalfPrecisionFloatEncoder encoder = new HalfPrecisionFloatEncoder(
                null, outputStream);
            encoder.encode(new HalfPrecisionFloat(value));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(
                outputStream.toByteArray());
            HalfPrecisionFloatDecoder decoder = new HalfPrecisionFloatDecoder(
                null, inputStream);
            HalfPrecisionFloat halfPrecisionFloat = decoder.decode(0);
            return value == halfPrecisionFloat.getValue();
        } catch (CborException cborException) {
            return false;
        }
    }

}
