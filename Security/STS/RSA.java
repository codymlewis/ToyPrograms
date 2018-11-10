import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * A BigInteger based implementation of RSA
 *
 * @author Cody Lewis
 * @since 2018-11-06
 */
public class RSA {
	public static final int PRIME_LEN = 1024; // Number of bits in the prime used in this
	// Certainty of primality in prime finding operations 1/(1 - 2^PRIME_CERTAINTY)
	public static final int PRIME_CERTAINTY = 10;
	private BigInteger p, q, n, e, d;

	/**
	 * Construct this object, setting the public keys and private keys
	 */
	public RSA() {
		SecureRandom rnd = new SecureRandom();
		p = new BigInteger(PRIME_LEN, PRIME_CERTAINTY, rnd);
		q = new BigInteger(PRIME_LEN, PRIME_CERTAINTY, rnd);
		BigInteger phiN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		e = genE(p, q, phiN);
		n = p.multiply(q);
		d = genD(e, phiN);
	}

	/**
	 * Generate a random prime bigger than the max of p and q, this will fit gcd(e,
	 * phiN) = 1
	 * 
	 * @param p    One of the chosen primes
	 * @param q    The other chosen prime
	 * @param phiN (p - 1) * (q - 1)
	 * @return A suitable value for e
	 */
	private BigInteger genE(BigInteger p, BigInteger q, BigInteger phiN) {
		SecureRandom rnd = new SecureRandom();
		int rndLen = rnd.nextInt(PRIME_LEN);
		BigInteger result = new BigInteger(PRIME_LEN + rndLen, PRIME_CERTAINTY, rnd);
		if (!result.gcd(phiN).equals(BigInteger.ONE)) { // Check whether result works
			return genE(p, q, phiN);
		}
		return result;
	}

	/**
	 * Generate a suitable value for e
	 * 
	 * @param e The encryptor value
	 * @param m (p - 1) * (q - 1)
	 * @return (e^-1 mod m)
	 */
	private BigInteger genD(BigInteger e, BigInteger m) {
		return e.modInverse(m);
	}

	/**
	 * The shared encryption/decryption method
	 * 
	 * @param message The input message
	 * @param cryptor The encrypting/decrypting value
	 * @param n       The modulus
	 * @return message^cryptor mod n
	 */
	private BigInteger crypt(BigInteger message, BigInteger cryptor, BigInteger n) {
		return message.modPow(cryptor, n);
	}

	/**
	 * Encrypt a given message
	 * 
	 * @param message     The given message
	 * @param otherPubKey The recievers public key
	 * @return RSA based cipher text corresponding to the other user
	 */
	public BigInteger encrypt(BigInteger message, BigInteger otherE, BigInteger otherN) {
		return crypt(message, otherE, otherN);
	}

	/**
	 * Decrypt a given message
	 * 
	 * @param message RSA ciphertext
	 * @return Plaintext equivalent of the message
	 */
	public BigInteger decrypt(BigInteger message) {
		return crypt(message, d, n);
	}

	/**
	 * Get the encryptor from this
	 * 
	 * @return The encryptor held in this
	 */
	public BigInteger getE() {
		return e;
	}

	/**
	 * Get the n
	 * 
	 * @return The value of n held in this
	 */
	public BigInteger getN() {
		return n;
	}
}