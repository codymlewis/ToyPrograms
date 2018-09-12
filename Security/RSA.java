import java.math.BigInteger;
import java.security.SecureRandom;
/**
 * RSA.java
 * A simulation of an RSA key exchange and encryption/decryption
 *
 * @author Cody Lewis
 * @since 2018-09-11
 */
class RSA {
    public static int bitLength = 2048;
    private BigInteger n, m, e, d;
    private BigInteger pubKeyA, pubKeyB;
    /**
     * The main thread
     * @param args command line arguments
     */
    public static void main(String args[]) {
        RSA rsa = new RSA();
        System.exit(rsa.run());
    }
    /**
     * The non-static run function
     */
    public int run() {
        pubKeyA = new BigInteger(bitLength, new SecureRandom());
        System.out.println("A's public key is " + pubKeyA);
        pubKeyB = new BigInteger(bitLength, new SecureRandom());
        System.out.println("B's public key is " + pubKeyB);
        announceM();
        chooseE();
        chooseD();
        authenticate();
        BigInteger msg = BigInteger.probablePrime(bitLength, new SecureRandom());
        System.out.println("Encrypting message: " + msg.toString());
        msg = crypt(e, msg);
        System.out.println("Ciphertext: " + msg.toString());
        System.out.println("Decrypting Ciphertext");
        msg = crypt(d, msg);
        System.out.println("Message: " + msg.toString());

        return 0;
    }
    /**
     * Choose an M and N for the RSA operations
     */
    public void announceM() {
        BigInteger p = choosePQ(), q = choosePQ();
        n = p.multiply(q);
        System.out.println("Chosen n: " + n.toString());
        m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("Chosen m: " + m.toString());
    }
    /**
     * Choose a suitable p or q
     * @return a suitable value for p or q
     */
    public BigInteger choosePQ() {
        return BigInteger.probablePrime(bitLength, new SecureRandom());
    }
    /**
     * Choose a suitable e
     */
    public void chooseE() {
        for(e = BigInteger.ONE.add(BigInteger.ONE); e.gcd(m).compareTo(BigInteger.ONE) != 0; e = e.add(BigInteger.ONE));
        System.out.println("Chosen e: " + e.toString());
    }
    /**
     * Choose a suitable d
     */
    public void chooseD() {
        d = e.modInverse(m);
        System.out.println("Chosen d: " + d.toString());
    }
    /**
     * Encryption/Decryption function
     * @param i encryptor/decryptor value
     * @param msg the message to encrypt/decrypt
     * @return the encrypted/decrypted message
     */
    public BigInteger crypt(BigInteger i, BigInteger msg) {
        return msg.modPow(i, n);
    }
    public void authenticate() {
        System.out.println("Starting authentication");
        BigInteger authA = crypt(e, pubKeyA);
        System.out.println("A sends B their public key encrypted with RSA: " + authA);
        BigInteger authAB = crypt(d, authA);
        System.out.println("B decrypts it and gets " + authAB + " which is " + (authAB.equals(pubKeyA) ? "" : "not ") + "A's public key");
        BigInteger authB = crypt(d, pubKeyB);
        System.out.println("B sends A their public key encrypted with RSA: " + authB);
        BigInteger authBA = crypt(e, authB);
        System.out.println("A decrypts it and gets " + authBA + " which is " + (authBA.equals(pubKeyB) ? "" : "not ") + "B's public key");
    }
}
