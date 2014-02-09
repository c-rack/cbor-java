package co.nstant.in.cbor.encoder;

import java.io.OutputStream;

import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.RationalNumber;
import co.nstant.in.cbor.model.Tag;

public class RationalNumberEncoder extends AbstractEncoder<RationalNumber> {

	public RationalNumberEncoder(CborEncoder encoder, OutputStream outputStream) {
		super(encoder, outputStream);
	}

	@Override
	public void encode(RationalNumber rationalNumber) throws CborException {
		encoder.encode(new Tag(30));
		encoder.encode(new Array().add(rationalNumber.getNumerator()).add(rationalNumber.getDennominator()));
	}

}
