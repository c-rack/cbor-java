package co.nstant.in.cbor.model;

public class UnicodeString extends ChunkableDataItem {

	private final String string;

	public UnicodeString(String string) {
		super(MajorType.UNICODE_STRING);
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof UnicodeString) {
			UnicodeString other = (UnicodeString) object;
			if (string == null) {
				return other == null;
			} else {
				return string.equals(other.string);
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return string.hashCode();
	}

}
