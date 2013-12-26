package co.nstant.in.cbor.model;

public class Special extends DataItem {

    public static final Special BREAK = new Special(SpecialType.BREAK);

    private final SpecialType specialType;

    protected Special(SpecialType specialType) {
        super(MajorType.SPECIAL);
        this.specialType = specialType;
    }

    public SpecialType getSpecialType() {
        return specialType;
    }

    @Override
    public String toString() {
        return specialType.name();
    }

}
