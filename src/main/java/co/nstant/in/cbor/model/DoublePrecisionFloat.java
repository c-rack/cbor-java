package co.nstant.in.cbor.model;

public class DoublePrecisionFloat extends Special {

	private final double value;

	public DoublePrecisionFloat(double value) {
		super(SpecialType.IEEE_754_DOUBLE_PRECISION_FLOAT);
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof DoublePrecisionFloat) {
			DoublePrecisionFloat other = (DoublePrecisionFloat) object;
			return value == other.value;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Double.valueOf(value).hashCode();
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
