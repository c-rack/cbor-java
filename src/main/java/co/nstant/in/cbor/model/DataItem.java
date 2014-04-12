package co.nstant.in.cbor.model;

import java.util.Objects;

public class DataItem {

    private final MajorType majorType;

    protected DataItem(MajorType majorType) {
        this.majorType = majorType;
        Objects.requireNonNull(majorType, "majorType is null");
    }

    public MajorType getMajorType() {
        return majorType;
    }

    protected void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

}
