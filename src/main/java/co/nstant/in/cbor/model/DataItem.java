package co.nstant.in.cbor.model;

import java.util.Objects;

public class DataItem {

    private final MajorType majorType;
    private Tag tag;

    protected DataItem(MajorType majorType) {
        this.majorType = majorType;
        Objects.requireNonNull(majorType, "majorType is null");
    }

    public MajorType getMajorType() {
        return majorType;
    }

    public void setTag(long tag) {
        if (tag < 0) {
            throw new IllegalArgumentException("tag number must be 0 or greater");
        }

        this.tag = new Tag(tag);
    }

    public void setTag(Tag tag) {
        Objects.requireNonNull(tag, "tag is null");
        this.tag = tag;
    }

    public void removeTag() {
        this.tag = null;
    }

    public Tag getTag() {
        return this.tag;
    }

    public boolean hasTag() {
        return (this.tag != null);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object instanceof DataItem) {
            DataItem otherDi = (DataItem) object;
            if (this.tag != null) {
                return (this.tag.equals(otherDi.getTag()) && this.majorType == otherDi.getMajorType());
            }
            else {
                return (otherDi.getTag() == null && this.majorType == otherDi.getMajorType());
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(majorType, tag);
    }

    protected void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

}
