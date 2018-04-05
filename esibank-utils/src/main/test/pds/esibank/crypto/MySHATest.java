package pds.esibank.crypto;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author BOURGEOIS Thibault
 * Date     05/04/2018
 * Time     10:17
 */

public class MySHATest {

    @Test
    public void shouldReturnGoodHashOfPass() {
        // assert statements
        Assert.assertEquals(
                MySHA.passToSHA(
                        "mypass",
                        "SHA-512"),
                "1c573dfeb388b562b55948af954a7b344dde1cc2099e978a992790429e7c01a4205506a93d9aef3bab32d6f06d75b7777a7ad8859e672fedb6a096ae369254d2");
    }

    @Test
    public void shouldReturnGoodSign() {
        // assert statements
        Assert.assertEquals(
                MySHA.generateSign(
                        "encryptedPassword",
                        "saltParam",
                        "HmacSHA512"),
                "94d83ab42cb9ae8d6f2315794723c5c851dd71cb9dfc7c84b73c225333316a12332365265a13401207f86efb2d3244505b8a0644a7a5a2744f202cb7453df7a5");
    }

    @Test
    public void shouldValidatingSign() {
        // assert statements
        Assert.assertTrue(MySHA.checkSign(
                "encryptedPassword",
                "saltParam",
                "HmacSHA512",
                "94d83ab42cb9ae8d6f2315794723c5c851dd71cb9dfc7c84b73c225333316a12332365265a13401207f86efb2d3244505b8a0644a7a5a2744f202cb7453df7a5"));
    }

}
