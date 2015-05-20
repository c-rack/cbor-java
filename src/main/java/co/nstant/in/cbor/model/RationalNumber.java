package co.nstant.in.cbor.model;

import java.math.BigInteger;

import co.nstant.in.cbor.CborException;

/**
 * Rational Numbers: http://peteroupc.github.io/CBOR/rational.html
 */

public class RationalNumber extends Array {

    public RationalNumber(Number numerator, Number denomiator) throws CborException {
        setTag(30);
        if (numerator == null) {
            throw new CborException("Numerator is null");
        }
        if (denomiator == null) {
            throw new CborException("Denomiator is null");
        }
        if (denomiator.getValue().equals(BigInteger.ZERO)) {
            throw new CborException("Denomiator is zero");
        }
        add(numerator);
        add(denomiator);
    }

    public Number getNumerator() {
        return (Number) getDataItems().get(0);
    }

    public Number getDennominator() {
        return (Number) getDataItems().get(1);
    }

}
