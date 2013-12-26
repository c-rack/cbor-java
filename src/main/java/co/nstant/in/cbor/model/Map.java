package co.nstant.in.cbor.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Map extends ChunkableDataItem {

    private HashMap<DataItem, DataItem> map = new HashMap<>();
    private List<DataItem> keys = new LinkedList<>();

    public Map() {
        super(MajorType.MAP);
    }

    public Map put(DataItem key, DataItem value) {
        if (map.put(key, value) == null) {
            keys.add(key);
        }
        return this;
    }

    public DataItem get(DataItem key) {
        return map.get(key);
    }

    public DataItem remove(DataItem key) {
        return map.remove(key);
    }

    public Collection<DataItem> getKeys() {
        return keys;
    }

    public Collection<DataItem> getValues() {
        return map.values();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Map) {
            Map other = (Map) object;
            return map.equals(other.map);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (isChunked()) {
            stringBuilder.append("{_ ");
        } else {
            stringBuilder.append("{ ");
        }
        for (java.util.Map.Entry<DataItem, DataItem> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey())
                            .append(": ")
                            .append(entry.getValue())
                            .append(", ");
        }
        if (stringBuilder.toString().endsWith(", ")) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}
