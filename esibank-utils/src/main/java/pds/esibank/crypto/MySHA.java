package pds.esibank.crypto;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.security.CryptoPrimitive;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static sun.security.pkcs11.wrapper.Functions.toHexString;

/**
 * @author BOURGEOIS Thibault
 * Date     11/03/2018
 * Time     16:58
 */
public class MySHA {

    public static String passToSHA(String pass, String cryptType) {
        try {
            MessageDigest md = MessageDigest.getInstance(cryptType);
            md.update(pass.getBytes());
            return toHexString(md.digest());
        } catch (Exception e) {
            return null;
        }
    }

    public static String generateSign(String encryptedPass, String paramRequest, String cryptSignType) {
        try {
            String chaineToCrypt = encryptedPass + paramRequest;
            SecretKeySpec signingKey = new SecretKeySpec(chaineToCrypt.getBytes(), cryptSignType);
            Mac mac = Mac.getInstance(cryptSignType);
            mac.init(signingKey);
            return toHexString(mac.doFinal());
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean checkSign(String encryptedPass, String paramRequest, String cryptSignType, String encryptedSignToCheck) {

        String encryptedSignGenerate = generateSign(encryptedPass, paramRequest, cryptSignType);

        if(encryptedSignToCheck.equals(encryptedSignGenerate))
            return true;
        else
            return false;
    }

}
