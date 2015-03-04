package co.nstant.in.cbor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array extends ChunkableDataItem {

    private final ArrayList<DataItem> objects = new ArrayList<>();

    public Array() {
        super(MajorType.ARRAY);
    }

    public Array add(DataItem object) {
        objects.add(object);
        return this;
    }

    public List<DataItem> getDataItems() {
        return objects;
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
			if (object instanceof Array) {
				Array other = (Array) object;
				return objects.equals(other.objects);
			}
		}
        return false;
    }

    @Override
    public int hashCode() {
		int hash = super.hashCode();
		hash += objects.hashCode();
		return hash;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        if (isChunked()) {
            stringBuilder.append("_ ");
        }
        stringBuilder.append(Arrays.toString(objects.toArray()).substring(1));
        return stringBuilder.toString();
    }

}
