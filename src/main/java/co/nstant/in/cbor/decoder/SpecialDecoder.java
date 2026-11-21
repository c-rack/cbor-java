package co.nstant.in.cbor.decoder;

import java.io.InputStream;

import co.nstant.in.cbor.CborDecoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.SimpleValue;
import co.nstant.in.cbor.model.SimpleValueType;
import co.nstant.in.cbor.model.Special;
import co.nstant.in.cbor.model.SpecialType;

public class SpecialDecoder extends AbstractDecoder<Special> {

    private final HalfPrecisionFloatDecoder halfPrecisionFloatDecoder;
    private final SinglePrecisionFloatDecoder singlePrecisionFloatDecoder;
    private final DoublePrecisionFloatDecoder doublePrecisionFloatDecoder;

    public SpecialDecoder(CborDecoder decoder, InputStream inputStream) {
        super(decoder, inputStream);
        this.halfPrecisionFloatDecoder = new HalfPrecisionFloatDecoder(decoder, inputStream);
        this.singlePrecisionFloatDecoder = new SinglePrecisionFloatDecoder(decoder, inputStream);
        this.doublePrecisionFloatDecoder = new DoublePrecisionFloatDecoder(decoder, inputStream);
    }

    @Override
    public Special decode(int initialByte) throws CborException {
        switch (SpecialType.ofByte(initialByte)) {
        case BREAK:
            return Special.BREAK;
        case SIMPLE_VALUE:
            // new instances: a tag set on a decoded value must not change the shared constants
            switch (SimpleValueType.ofByte(initialByte)) {
            case FALSE:
                return new SimpleValue(SimpleValueType.FALSE);
            case TRUE:
                return new SimpleValue(SimpleValueType.TRUE);
            case NULL:
                return new SimpleValue(SimpleValueType.NULL);
            case UNDEFINED:
                return new SimpleValue(SimpleValueType.UNDEFINED);
            case UNALLOCATED:
                return new SimpleValue(initialByte & 31);
            case RESERVED:
            default:
                throw new CborException("Not implemented");
            }
        case IEEE_754_HALF_PRECISION_FLOAT:
            return halfPrecisionFloatDecoder.decode(initialByte);
        case IEEE_754_SINGLE_PRECISION_FLOAT:
            return singlePrecisionFloatDecoder.decode(initialByte);
        case IEEE_754_DOUBLE_PRECISION_FLOAT:
            return doublePrecisionFloatDecoder.decode(initialByte);
        case SIMPLE_VALUE_NEXT_BYTE:
            return new SimpleValue(nextSymbol());
        default:
            throw new CborException("Not implemented");
        }
    }

}
