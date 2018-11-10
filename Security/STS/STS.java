import java.util.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.PublicKey;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

/**
 * An STS protocol implementation using Elliptic curve Diffie Hellman
 *
 * @author Cody Lewis
 * @since 2018-11-06
 */
public class STS {
	private static final int KEY_NUM = CTR.KEY_NUM;
	public static final int KEY_LENGTH = CTR.KEY_BITS * KEY_NUM; // A elliptic curve key size in bits
	private KeyAgreement ecdh;
	private PublicKey publicKey;
	private CTR ctr;
	private RSA rsa;

	/**
	 * Default constructor, set up the crypto systems
	 */
	public STS() throws NoSuchAlgorithmException, InvalidKeyException {
		ctr = new CTR();
		rsa = new RSA();
		// Set up ECDH
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
		kpg.initialize(KEY_LENGTH);
		KeyPair kp = kpg.generateKeyPair();
		publicKey = kp.getPublic();
		System.out.println(publicKey.getEncoded());
		ecdh = KeyAgreement.getInstance("ECDH");
		ecdh.init(kp.getPrivate());
	}

	/**
	 * Generate the shared session key based on the other user's public key
	 * 
	 * @param publicKey The other user's public key encoded with Base64
	 */
	public void setOtherPubKey(String publicKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
		KeyFactory kf = KeyFactory.getInstance("EC");
		X509EncodedKeySpec eks = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
		ecdh.doPhase(kf.generatePublic(eks), true);
		byte[] secretKey = ecdh.generateSecret();
		byte[][] secretKeyParts = new byte[3][secretKey.length / KEY_NUM];
		for (int i = 0; i < KEY_NUM; ++i) {
			for (int j = 0; j < secretKey.length / KEY_NUM; ++j) {
				secretKeyParts[i][j] = secretKey[j + i * (secretKey.length / KEY_NUM)];
			}
		}
		ctr.setKeys(secretKeyParts);
	}

	/**
	 * Start the STS protocol
	 * 
	 * @param alice The user to write from
	 * @param bob   The user write to
	 */
	public void startSTS(BufferedReader alice, PrintWriter bob)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, FailedVerificationException {
		// Assumption: Alice and Bob know eachothers public keys at the beginning
		bob.println(rsa.getE().toString());
		bob.println(rsa.getN().toString());
		BigInteger rsaE = new BigInteger(alice.readLine());
		BigInteger rsaN = new BigInteger(alice.readLine());
		// STS protocol
		String alicePublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
		startStepOne(alice, bob, alicePublicKey);
		String bobPublicKey = joinStepTwo(alice, bob, alicePublicKey, rsaE, rsaN);
		startStepThree(alice, bob, alicePublicKey, bobPublicKey);
	}

	/**
	 * Send a message first for STS
	 * 
	 * @param alice          The sender socket
	 * @param bob            The receiver socket
	 * @param alicePublicKey The sender's public key
	 */
	private void startStepOne(BufferedReader alice, PrintWriter bob, String alicePublicKey) throws IOException {
		bob.println(ctr.getNonce()); // Send the nonce to bob
		bob.println(alicePublicKey);
	}

	/**
	 * Receive a message in the second step of STS
	 * 
	 * @param alice          The sender socket
	 * @param bob            The receiver socket
	 * @param alicePublicKey The sender's public key
	 * @param rsaE           Part of the receiver's RSA public key
	 * @param rsaN           Other part of the receiver's RSA public key
	 * @return The receiver's public key
	 */
	private String joinStepTwo(BufferedReader alice, PrintWriter bob, String alicePublicKey, BigInteger rsaE,
			BigInteger rsaN) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, FailedVerificationException {
		String bobPublicKey = alice.readLine();
		setOtherPubKey(bobPublicKey);
		String signiture = alice.readLine();
		if (!verify(bobPublicKey, alicePublicKey, signiture, rsaE, rsaN)) {
			bob.println("Failed");
			throw new FailedVerificationException();
		}
		bob.println("Passed");
		return bobPublicKey;
	}

	/**
	 * Send a message for Step 3 of STS
	 * 
	 * @param alice          The sender's socket
	 * @param bob            The receiver's socket
	 * @param alicePublicKey The sender's public key
	 * @param bobPublicKey   The receiver's public key
	 */
	private void startStepThree(BufferedReader alice, PrintWriter bob, String alicePublicKey, String bobPublicKey)
			throws IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchPaddingException, FailedVerificationException {
		bob.println(sign(alicePublicKey, bobPublicKey));
		if (alice.readLine().equals("Failed")) {
			throw new FailedVerificationException();
		}
	}

	/**
	 * Be the second to send messages on STS
	 * 
	 * @param alice The receiver's socket
	 * @param bob   The sender's socket
	 */
	public void joinSTS(PrintWriter alice, BufferedReader bob)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, FailedVerificationException {
		// Assumption: Alice and Bob know eachothers public keys at the beginning
		BigInteger rsaE = new BigInteger(bob.readLine());
		BigInteger rsaN = new BigInteger(bob.readLine());
		alice.println(rsa.getE().toString());
		alice.println(rsa.getN().toString());
		// STS protocol
		String alicePublicKey = joinStepOne(alice, bob);
		String bobPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
		startStepTwo(alice, bob, bobPublicKey, alicePublicKey);
		joinStepThree(alice, bob, bobPublicKey, alicePublicKey, rsaE, rsaN);
	}

	/**
	 * Recieve a message for step 1 of STS
	 * 
	 * @param alice The receiver's socket
	 * @param bob   The sender's socket
	 * @return The receiver's public key
	 */
	private String joinStepOne(PrintWriter alice, BufferedReader bob)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
		String nonce = bob.readLine();
		ctr.setNonce(nonce);
		String alicePublicKey = bob.readLine();
		setOtherPubKey(alicePublicKey);
		return alicePublicKey;
	}

	/**
	 * Send a message for step 2 of STS
	 * 
	 * @param alice          The receiver's socket
	 * @param bob            The sender's socket
	 * @param bobPublicKey   The sender's public key
	 * @param alicePublicKey The receiver's public key
	 */
	private void startStepTwo(PrintWriter alice, BufferedReader bob, String bobPublicKey, String alicePublicKey)
			throws IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchPaddingException, FailedVerificationException {
		alice.println(bobPublicKey);
		alice.println(sign(bobPublicKey, alicePublicKey));
		if (bob.readLine().equals("Failed")) {
			throw new FailedVerificationException();
		}
	}

	/**
	 * Receive a message for step 3 of STS
	 * 
	 * @param alice          The receiver's socket
	 * @param bob            The sender's socket
	 * @param bobPublicKey   The sender's public key
	 * @param alicePublicKey The receiver's public key
	 * @param rsaE           Part of the receiver's RSA public key
	 * @param rsaN           Other part of the receiver's RSA public key
	 */
	private void joinStepThree(PrintWriter alice, BufferedReader bob, String bobPublicKey, String alicePublicKey,
			BigInteger rsaE, BigInteger rsaN) throws IOException, NoSuchAlgorithmException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, FailedVerificationException {
		String signiture = bob.readLine();
		if (!verify(alicePublicKey, bobPublicKey, signiture, rsaE, rsaN)) {
			alice.println("Failed");
			throw new FailedVerificationException();
		}
		alice.println("Passed");
	}

	/**
	 * Sign and encrypt a pair of public keys
	 * 
	 * @param pubKey1 The first specified public key
	 * @param pubKey2 The second specified public key
	 * @return The public keys signed and encrypted
	 */
	private String sign(String pubKey1, String pubKey2) throws NoSuchAlgorithmException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
		BigInteger signiture = hash((pubKey1 + pubKey2), rsa.getN());
		signiture = rsa.decrypt(signiture);
		String ctrSig = ctr.encrypt(Base64.getEncoder().encodeToString(signiture.toByteArray()));
		return ctrSig;
	}

	/**
	 * Check whether a signiture is valid
	 * 
	 * @param pubKey1   The first specified public key
	 * @param pubKey2   The second specified public key
	 * @param signiture The signiture to check
	 * @param rsaE      Part of the receiver's RSA public key
	 * @param rsaN      Other part of the receiver's RSA public key
	 * @return true if the signiture is valid else false
	 */
	private boolean verify(String pubKey1, String pubKey2, String signiture, BigInteger rsaE, BigInteger rsaN)
			throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchPaddingException {
		BigInteger hashVal = hash((pubKey1 + pubKey2), rsaN);
		signiture = ctr.decrypt(signiture);
		BigInteger decryptedSig = rsa.encrypt(new BigInteger(Base64.getDecoder().decode(signiture)), rsaE, rsaN);
		return decryptedSig.equals(hashVal);
	}

	/**
	 * Hash a given String
	 * 
	 * @param m       The given string message
	 * @param modulus The max number of the hash
	 * @return The hash corresponding to the input message
	 */
	public static BigInteger hash(String m, BigInteger modulus) throws NoSuchAlgorithmException {
		return new BigInteger(hash(m.getBytes())).mod(modulus);
	}

	/**
	 * Hash a given byte array
	 * 
	 * @param m The message to hash
	 * @return The hash corresponding to the given message
	 */
	public static byte[] hash(byte[] m) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(m);
	}

	/**
	 * Encrypt a message using the CTR composed in this
	 * 
	 * @param message The message to encrypt
	 * @return Cipher-text of the message
	 */
	public String encrypt(String message) throws NoSuchAlgorithmException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
		return ctr.encrypt(message);
	}

	/**
	 * Decrypt a given cipher-text message using the CTR composed in this
	 * 
	 * @param message The cipher-text message
	 * @return The message in plain-text
	 */
	public String decrypt(String message) throws NoSuchAlgorithmException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
		return ctr.decrypt(message);
	}
}
