package co.nstant.in.cbor.model;

import co.nstant.in.cbor.CborException;

public enum SpecialType {

    SIMPLE_VALUE, SIMPLE_VALUE_NEXT_BYTE, IEEE_754_HALF_PRECISION_FLOAT, IEEE_754_SINGLE_PRECISION_FLOAT,
    IEEE_754_DOUBLE_PRECISION_FLOAT, BREAK;

    public static SpecialType ofByte(int b) throws CborException {
        switch (b & 31) {
        case 24:
            return SIMPLE_VALUE_NEXT_BYTE;
        case 25:
            return IEEE_754_HALF_PRECISION_FLOAT;
        case 26:
            return IEEE_754_SINGLE_PRECISION_FLOAT;
        case 27:
            return IEEE_754_DOUBLE_PRECISION_FLOAT;
        case 28:
        case 29:
        case 30:
            throw new CborException("Not implemented special type " + b);
        case 31:
            return BREAK;
        default:
            return SIMPLE_VALUE;
        }
    }

}
