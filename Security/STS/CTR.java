import java.util.Base64;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * A DES CTR mode implementation
 *
 * @author Cody Lewis
 * @since 2018-11-06
 */
public class CTR {
	private BigInteger counter, nonce;
	private SecretKeySpec[] keys;
	private static final int BLOCK_BITS = 63; // Indexing starts from 0
	public static final int KEY_BITS = 64;
	public static final int KEY_NUM = 3;

	/**
	 * Default constructor
	 */
	public CTR() {
		counter = BigInteger.ZERO;
		SecureRandom rnd = new SecureRandom();
		nonce = new BigInteger(BLOCK_BITS, rnd);
		keys = new SecretKeySpec[KEY_NUM];
	}

	/**
	 * Get the nonce held in this
	 * 
	 * @return A String representation of this
	 */
	public String getNonce() {
		return nonce.toString();
	}

	/**
	 * Set the nonce held in this
	 * 
	 * @param nonce A String representation of the nonce to set this to
	 */
	public void setNonce(String nonce) {
		this.nonce = new BigInteger(nonce);
	}

	/**
	 * Set the keys held in this
	 * 
	 * @param key1 The first key to set
	 * @param key2 The second key to set
	 */
	public void setKeys(byte[][] keys) {
		for (int i = 0; i < KEY_NUM; ++i) {
			this.keys[i] = new SecretKeySpec(keys[i], "DES");
		}
	}

	/**
	 * Encrypt a given message
	 * 
	 * @param message The message to encrypt
	 * @return Cipher text corresponding the given message
	 */
	public String encrypt(String message) throws NoSuchAlgorithmException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
		String result = "";
		for (int i = 0; i < Math.ceil((float) message.length() / 8f); ++i) { // split up into input blocks
			String current = Base64.getEncoder().encodeToString(crypt(message
					.substring(i * 8, ((i + 1) * 8 > message.length() ? message.length() : (i + 1) * 8)).getBytes()));
			Integer len = current.length();
			result += String.format("%d%d%s", len.toString().length(), len, current);
		}
		return result;
	}

	/**
	 * Decrypt a given ciphertext
	 * 
	 * @param ciphertext The ciphertext to decrypt
	 * @return Plaintext corresponding the given ciphertext
	 */
	public String decrypt(String ciphertext) throws NoSuchAlgorithmException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
		int i = 0;
		String result = "";
		while (i < ciphertext.length()) { // Attatch the split blocks back together
			int currentLenLen = Integer.parseInt(ciphertext.substring(i, i + 1)); // Length of the length
			++i;
			int currentLen = Integer.parseInt(ciphertext.substring(i, i + currentLenLen));
			i += currentLenLen;
			byte[] plaintext = crypt(Base64.getDecoder().decode(ciphertext.substring(i, i + currentLen)));
			result += new String(plaintext, 0, plaintext.length);
			i += currentLen;
		}
		return result;
	}

	/**
	 * Shared encryption/decryption method
	 * 
	 * @param message Message to encrypt/decrypt
	 * @return ciphertext/plaintext
	 */
	public byte[] crypt(byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchPaddingException {
		BigInteger current = nonce.xor(counter);
		current = des3crypt(current);
		return new BigInteger(message).xor(current).toByteArray();
	}

	/**
	 * Pass a given message through 3 DES EDE
	 * 
	 * @param m The given message
	 * @return Ciphertext of the message
	 */
	private BigInteger des3crypt(BigInteger m) throws NoSuchAlgorithmException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
		Cipher des = Cipher.getInstance("DES/ECB/PKCS5Padding");
		byte[] currentMessage = m.toByteArray();
		des.init(Cipher.ENCRYPT_MODE, keys[0]);
		currentMessage = des.doFinal(currentMessage);
		des = Cipher.getInstance("DES/ECB/NoPadding");
		des.init(Cipher.DECRYPT_MODE, keys[1]);
		currentMessage = des.doFinal(currentMessage);
		des = Cipher.getInstance("DES/ECB/NoPadding");
		des.init(Cipher.ENCRYPT_MODE, keys[2]);
		currentMessage = des.doFinal(currentMessage);
		counter = counter.add(BigInteger.ONE); // Increment the counter
		return new BigInteger(currentMessage);
	}
}