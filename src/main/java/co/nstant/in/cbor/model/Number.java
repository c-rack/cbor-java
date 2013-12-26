package co.nstant.in.cbor.model;

import java.math.BigInteger;

public abstract class Number extends DataItem {

    private final BigInteger value;

    protected Number(MajorType majorType, BigInteger value) {
        super(majorType);
        this.value = value;
    }

    public BigInteger getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Number) {
            Number other = (Number) object;
            return value.equals(other.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
