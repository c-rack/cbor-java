package co.nstant.in.cbor;

/**
 * -100 -> 0x3863
 */
public class Example17Test extends AbstractNumberTest {

    public Example17Test() {
        super(-100, new byte[] { 0x38, 0x63 });
    }

}
