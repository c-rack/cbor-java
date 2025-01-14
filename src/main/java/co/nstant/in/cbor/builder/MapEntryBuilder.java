package co.nstant.in.cbor.builder;

import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.Map;

public class MapEntryBuilder<T extends MapBuilder<?>> extends AbstractBuilder<T> {
    private final DataItem key;

    public MapEntryBuilder(T parent, DataItem key) {
        super(parent);
        this.key = key;
    }

    public T value(boolean value) {
        return put(key, convert(value));
    }

    public T value(byte[] value) {
        return put(key, convert(value));
    }

    public T value(double value) {
        return put(key, convert(value));
    }

    public T value(String value) {
        return put(key, convert(value));
    }

    public ArrayBuilder<T> valueArray() {
        Array nestedArray = new Array();
        this.put(key, nestedArray);
        return new ArrayBuilder<>(getParent(), nestedArray);
    }

    public MapBuilder<T> valueMap() {
        Map nestedMap = new Map();
        this.put(key, nestedMap);
        return new MapBuilder<>(getParent(), nestedMap);
    }

    private T put(DataItem key, DataItem value) {
        getParent().put(key, value);
        return getParent();
    }

    public MapEntryBuilder<T> tagged(long tag) {
        DataItem item = key.getOuterTaggable();
        item.setTag(tag);
        return this;
    }
}
