package co.nstant.in.cbor.model;

class ChunkableDataItem extends DataItem {

    private boolean chunked = false;

    protected ChunkableDataItem(MajorType majorType) {
        super(majorType);
    }

    public boolean isChunked() {
        return chunked;
    }

    public ChunkableDataItem setChunked(boolean chunked) {
        this.chunked = chunked;
        return this;
    }

}
