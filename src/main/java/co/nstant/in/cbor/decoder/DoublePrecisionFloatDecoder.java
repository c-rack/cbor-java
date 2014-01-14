package co.nstant.in.cbor.decoder;

import java.io.InputStream;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.DoublePrecisionFloat;

public class DoublePrecisionFloatDecoder extends
                AbstractDecoder<DoublePrecisionFloat> {

    public DoublePrecisionFloatDecoder(CborDecoder decoder,
                    InputStream inputStream) {
        super(decoder, inputStream);
    }

    @Override
    public DoublePrecisionFloat decode(int initialByte) throws CborException {
        long bits = 0;
        bits |= nextSymbol() & 0xFF;
        bits <<= 8;
        bits |= nextSymbol() & 0xFF;
        bits <<= 8;
        bits |= nextSymbol() & 0xFF;
        bits <<= 8;
        bits |= nextSymbol() & 0xFF;
        bits <<= 8;
        bits |= nextSymbol() & 0xFF;
        bits <<= 8;
        bits |= nextSymbol() & 0xFF;
        bits <<= 8;
        bits |= nextSymbol() & 0xFF;
        bits <<= 8;
        bits |= nextSymbol() & 0xFF;
        return new DoublePrecisionFloat(Double.longBitsToDouble(bits));
    }

}
