package co.nstant.in.cbor.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import co.nstant.in.cbor.CborException;

public class RationalNumberTest {

    @Test(expected = CborException.class)
    public void shouldThrowIfNumeratorIsNull() throws CborException {
        new RationalNumber(null, new UnsignedInteger(1));
    }

    @Test(expected = CborException.class)
    public void shouldThrowIfDenominatorIsNull() throws CborException {
        new RationalNumber(new UnsignedInteger(1), null);
    }

    @Test(expected = CborException.class)
    public void shouldThrowIfDenominatorIsZero() throws CborException {
        new RationalNumber(new UnsignedInteger(1), new UnsignedInteger(0));
    }

    @Test
    public void shouldSetNumeratorAndDenominator() throws CborException {
        UnsignedInteger one = new UnsignedInteger(1);
        UnsignedInteger two = new UnsignedInteger(2);
        RationalNumber rationalNumber = new RationalNumber(one, two);
        assertEquals(one, rationalNumber.getNumerator());
        assertEquals(two, rationalNumber.getDenominator());
    }

}
