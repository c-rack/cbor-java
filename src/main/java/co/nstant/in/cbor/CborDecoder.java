package co.nstant.in.cbor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import co.nstant.in.cbor.decoder.ArrayDecoder;
import co.nstant.in.cbor.decoder.ByteStringDecoder;
import co.nstant.in.cbor.decoder.MapDecoder;
import co.nstant.in.cbor.decoder.NegativeIntegerDecoder;
import co.nstant.in.cbor.decoder.SpecialDecoder;
import co.nstant.in.cbor.decoder.TagDecoder;
import co.nstant.in.cbor.decoder.UnicodeStringDecoder;
import co.nstant.in.cbor.decoder.UnsignedIntegerDecoder;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.MajorType;
import co.nstant.in.cbor.model.Tag;

/**
 * Decoder for the CBOR format based.
 */
public class CborDecoder {

	private final InputStream inputStream;
	private final UnsignedIntegerDecoder unsignedIntegerDecoder;
	private final NegativeIntegerDecoder negativeIntegerDecoder;
	private final ByteStringDecoder byteStringDecoder;
	private final UnicodeStringDecoder unicodeStringDecoder;
	private final ArrayDecoder arrayDecoder;
	private final MapDecoder mapDecoder;
	private final TagDecoder tagDecoder;
	private final SpecialDecoder specialDecoder;

	private boolean autoDecodeInfinitiveArrays = true;
	private boolean autoDecodeInfinitiveMaps = true;
	private boolean autoDecodeInfinitiveByteStrings = true;
	private boolean autoDecodeInfinitiveUnicodeStrings = true;

	/**
	 * Initialize a new decoder which reads the binary encoded data from an
	 * {@link InputStream}.
	 */
	public CborDecoder(InputStream inputStream) {
		Objects.requireNonNull(inputStream);
		this.inputStream = inputStream;
		unsignedIntegerDecoder = new UnsignedIntegerDecoder(this, inputStream);
		negativeIntegerDecoder = new NegativeIntegerDecoder(this, inputStream);
		byteStringDecoder = new ByteStringDecoder(this, inputStream);
		unicodeStringDecoder = new UnicodeStringDecoder(this, inputStream);
		arrayDecoder = new ArrayDecoder(this, inputStream);
		mapDecoder = new MapDecoder(this, inputStream);
		tagDecoder = new TagDecoder(this, inputStream);
		specialDecoder = new SpecialDecoder(this, inputStream);
	}

	/**
	 * Convenience method to decode a byte array directly.
	 *
	 * @param bytes
	 *            the CBOR encoded data
	 * @return a list of {@link DataItem}s
	 * @throws CborException
	 *             if decoding failed
	 */
	public static List<DataItem> decode(byte[] bytes) throws CborException {
		return new CborDecoder(new ByteArrayInputStream(bytes)).decode();
	}

	/**
	 * Decode the {@link InputStream} to a list of {@link DataItem}s.
	 *
	 * @return the list of {@link DataItem}s
	 * @throws CborException
	 *             if decoding failed
	 */
	public List<DataItem> decode() throws CborException {
		List<DataItem> dataItems = new LinkedList<>();
		DataItem dataItem;
		while ((dataItem = decodeNext()) != null) {
			dataItems.add(dataItem);
		}
		return dataItems;
	}

	/**
	 * Streaming decoding of an input stream. On each decoded DataItem, the
	 * callback listener is invoked.
	 *
	 * @param dataItemListener
	 *            the callback listener
	 * @throws CborException
	 *             if decoding failed
	 */
	public void decode(DataItemListener dataItemListener) throws CborException {
		Objects.requireNonNull(dataItemListener);
		DataItem dataItem = decodeNext();
		while (dataItem != null) {
			dataItemListener.onDataItem(dataItem);
			dataItem = decodeNext();
		}
	}

	/**
	 * Decodes exactly one DataItem from the input stream.
	 *
	 * @return a {@link DataItem} or null if end of stream has reached.
	 * @throws CborException
	 *             if decoding failed
	 */
	public DataItem decodeNext() throws CborException {
		int symbol;
		try {
			symbol = inputStream.read();
		} catch (IOException ioException) {
			throw new CborException(ioException);
		}
		if (symbol == -1) {
			return null;
		}
		switch (MajorType.ofByte(symbol)) {
		case ARRAY:
			return arrayDecoder.decode(symbol);
		case BYTE_STRING:
			return byteStringDecoder.decode(symbol);
		case MAP:
			return mapDecoder.decode(symbol);
		case NEGATIVE_INTEGER:
			return negativeIntegerDecoder.decode(symbol);
		case UNICODE_STRING:
			return unicodeStringDecoder.decode(symbol);
		case UNSIGNED_INTEGER:
			return unsignedIntegerDecoder.decode(symbol);
		case SPECIAL:
			return specialDecoder.decode(symbol);
		case TAG:
			Tag tagDi = tagDecoder.decode(symbol);
			DataItem nextDi = decodeNext();
			nextDi.setTag(tagDi);

			return nextDi;
			//return tagDecoder.decode(symbol);
		default:
			throw new CborException("Not implemented major type " + symbol);
		}
	}

	public boolean isAutoDecodeInfinitiveArrays() {
		return autoDecodeInfinitiveArrays;
	}

	public void setAutoDecodeInfinitiveArrays(boolean autoDecodeInfinitiveArrays) {
		this.autoDecodeInfinitiveArrays = autoDecodeInfinitiveArrays;
	}

	public boolean isAutoDecodeInfinitiveMaps() {
		return autoDecodeInfinitiveMaps;
	}

	public void setAutoDecodeInfinitiveMaps(boolean autoDecodeInfinitiveMaps) {
		this.autoDecodeInfinitiveMaps = autoDecodeInfinitiveMaps;
	}

	public boolean isAutoDecodeInfinitiveByteStrings() {
		return autoDecodeInfinitiveByteStrings;
	}

	public void setAutoDecodeInfinitiveByteStrings(
			boolean autoDecodeInfinitiveByteStrings) {
		this.autoDecodeInfinitiveByteStrings = autoDecodeInfinitiveByteStrings;
	}

	public boolean isAutoDecodeInfinitiveUnicodeStrings() {
		return autoDecodeInfinitiveUnicodeStrings;
	}

	public void setAutoDecodeInfinitiveUnicodeStrings(
			boolean autoDecodeInfinitiveUnicodeStrings) {
		this.autoDecodeInfinitiveUnicodeStrings = autoDecodeInfinitiveUnicodeStrings;
	}

}
