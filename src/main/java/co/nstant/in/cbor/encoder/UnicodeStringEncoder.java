package co.nstant.in.cbor.encoder;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.MajorType;
import co.nstant.in.cbor.model.SimpleValue;
import co.nstant.in.cbor.model.UnicodeString;

public class UnicodeStringEncoder extends AbstractEncoder<UnicodeString> {

    private static final String UTF8 = "UTF-8";

    public UnicodeStringEncoder(CborEncoder encoder, OutputStream outputStream) {
        super(encoder, outputStream);
    }

    @Override
    public void encode(UnicodeString dataItem) throws CborException {
        String string = dataItem.getString();
        if (dataItem.isChunked()) {
            encodeTypeChunked(MajorType.UNICODE_STRING);
            if (string != null) {
                encode(new UnicodeString(string));
            }
        } else if (string == null) {
            encoder.encode(SimpleValue.NULL);
        } else {
            byte[] bytes;
			try {
				bytes = string.getBytes(UTF8);
			} catch (UnsupportedEncodingException e) {
				throw new CborException(e);
			}
            encodeTypeAndLength(MajorType.UNICODE_STRING, bytes.length);
            write(bytes);
        }
    }

}
