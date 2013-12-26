package co.nstant.in.cbor.model;

public abstract class DataItem {

    private final MajorType majorType;

    protected DataItem(MajorType majorType) {
        this.majorType = majorType;
        assertNotNull(majorType, "majorType is null");
    }

    public MajorType getMajorType() {
        return majorType;
    }

    protected void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertNotNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
