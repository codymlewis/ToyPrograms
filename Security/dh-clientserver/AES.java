import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
/**
 * AES encryption wrapper library
 *
 * @author Cody Lewis
 * @since 2018-08-29
 */
public class AES {
    /**
     * Hash the input key
     * @param key a secret key
     * @return the hash of the secret key
     */
    private static byte[] hashKey(byte[] key) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(key);
    }
    /**
     * Perform AES encryption
     * @param sessionKey a secret key
     * @param message the plaintext
     * @return cipher-text of the message
     */
    public static byte[] encrypt(byte[] sessionKey, byte[] message) throws Exception {
        Cipher c = Cipher.getInstance("AES");
        SecretKeySpec secret = new SecretKeySpec(hashKey(sessionKey), "AES");
        c.init(c.ENCRYPT_MODE, secret);
        return c.doFinal(message);
    }
    /**
     * Perform AES decryption
     * @param sessionKey a secret key
     * @param ciphertext some AES encrypted text
     * @return the plain-text
     */
    public static byte[] decrypt(byte[] sessionKey, byte[] ciphertext) throws Exception {
        Cipher c = Cipher.getInstance("AES");
        SecretKeySpec secret = new SecretKeySpec(hashKey(sessionKey), "AES");
        c.init(c.DECRYPT_MODE, secret);
        return c.doFinal(ciphertext);
    }
}