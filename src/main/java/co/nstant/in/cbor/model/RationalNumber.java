package co.nstant.in.cbor.model;

import java.math.BigInteger;

import co.nstant.in.cbor.CborException;

/**
 * Rational Numbers: http://peteroupc.github.io/CBOR/rational.html
 */

public class RationalNumber {

	private final Number numerator;
	private final Number denominator;

	protected RationalNumber(Number numerator, Number denomiator) throws CborException {
		if (numerator == null) {
			throw new CborException("Numerator is null");
		}
		if (denomiator == null) {
			throw new CborException("Denomiator is null");
		}
		if (denomiator.getValue().equals(BigInteger.ZERO)) {
			throw new CborException("Denomiator is zero");
		}
		this.numerator = numerator;
		this.denominator = denomiator;
	}

	public Number getNumerator() {
		return numerator;
	}

	public Number getDennominator() {
		return denominator;
	}

}
