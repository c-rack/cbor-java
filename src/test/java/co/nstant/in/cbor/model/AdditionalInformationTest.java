package co.nstant.in.cbor.model;

import org.junit.Assert;
import org.junit.Test;

public class AdditionalInformationTest {

    /**
     * Additional information values 28 to 30 are reserved for future expansion.
     */
    @Test
    public void shouldHandleReserved28() {
        Assert.assertEquals(AdditionalInformation.RESERVED,
                        AdditionalInformation.ofByte(28));
        Assert.assertEquals(AdditionalInformation.RESERVED,
                        AdditionalInformation.ofByte(29));
        Assert.assertEquals(AdditionalInformation.RESERVED,
                        AdditionalInformation.ofByte(30));
    }

}
