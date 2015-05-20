package co.nstant.in.cbor.model;

import java.util.Objects;

public class Tag extends DataItem {

    private final long value;

    public Tag(long value) {
        super(MajorType.TAG);
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Tag) {
            Tag other = (Tag) object;
            return value == other.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMajorType(), getTag(), value);
    }

    @Override
    public String toString() {
        return String.format("Tag(%d)", value);
    }

}
