import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.math.BigInteger;
/**
 * Client for a simple encrypted message
 *
 * @author Cody Lewis
 * @since 2018-08-29
 */
class DHClient {
    /**
     * The main thread
     */
    public static void main(String args[]) throws Exception {
        if(args.length == 2) {
            DHClient dhc = new DHClient();
            dhc.run(args);
        } else {
            System.out.println("Not enough arguments, please execute the program with target ip then message as arguments");
        }
    }
    private final int port = 2250;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private BigInteger prime;
    private byte[] sessionKey;
    /**
     * Run the client
     * @param args the command line arguments
     */
    private void run(String args[]) throws Exception {
        socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        String address = args[0];
        primeAgreement(address);
        keyAgreement(address);
        sendMessage(address, args[1]);
        socket.close();
    }
    /**
     * Agree with the server on a chosen prime
     * @param address the address of the server
     */
    private void primeAgreement(String address) throws Exception {
        System.out.println("Generating a prime");
        prime = DH.genPrime(16);
        System.out.println("Prime: " + prime);
        byte[] primeBuffer = prime.toString().getBytes();
        packet = new DatagramPacket(primeBuffer, primeBuffer.length, InetAddress.getByName(address), port);
        socket.send(packet);
    }
    /**
     * Generate keys and exchange public keys with the server
     * @param address the address of the server
     */
    private void keyAgreement(String address) throws Exception {
        System.out.println("Generating a private key");
        BigInteger priKey = DH.genPrivateKey(prime);
        System.out.println("Private key: " + priKey);
        System.out.println("Generating a public key");
        BigInteger pubKey = DH.genPublicKey(priKey, prime);
        System.out.println("Public key: " + pubKey);
        System.out.println("Getting server's public key");
        socket.receive(packet);
        System.out.println("Server public key: " + new BigInteger(new String(packet.getData(), 0, packet.getLength())));
        System.out.println("Generating session key");
        sessionKey = DH.genSessionKey(new BigInteger(new String(packet.getData(), 0, packet.getLength())), priKey, prime).toByteArray();
        System.out.println("Session key: " + sessionKey);
        System.out.println("Sending public key to server");
        byte[] pubKeyBuffer = pubKey.toString().getBytes();
        packet = new DatagramPacket(pubKeyBuffer, pubKeyBuffer.length, InetAddress.getByName(address), port);
        socket.send(packet);
    }
    /**
     * Send a message to the server
     * @param address the address of the server
     * @param plaintext the message to send to the server
     */
    private void sendMessage(String address, String plaintext) throws Exception {
        byte[] message = plaintext.getBytes();
        byte[] ciphertext = AES.encrypt(sessionKey, message);
        System.out.println("Sending ciphertext of message");
        byte[] send = new byte[1];
        for(byte cbyte : ciphertext) { // break up cipher text into chunks
            send[0] = cbyte;
            packet = new DatagramPacket(send, send.length, InetAddress.getByName(address), port);
            socket.send(packet);
        }
    }
}
