package co.nstant.in.cbor.model;

public class AbstractFloat extends Special {

    private final float value;

    public AbstractFloat(SpecialType specialType, float value) {
        super(specialType);
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof AbstractFloat) {
            AbstractFloat other = (AbstractFloat) object;
            return value == other.value;
        }
        return false;
    }

}
