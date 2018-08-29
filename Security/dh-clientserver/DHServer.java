import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.math.BigInteger;
import javax.crypto.KeyAgreement;
/**
 * Server for a simple encrypted message system, runs for a single communication
 *
 * @author Cody Lewis
 * @since 2018-08-29
 */
class DHServer {
    /**
     * The main thread
     */
    public static void main( String args[] ) {
        DHServer dhs = new DHServer();
        dhs.run();
    }
    private final int port = 2250;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private InetAddress clientIp;
    private int clientPort;
    private BigInteger prime;
    private byte[] sessionKey;
    /**
     * Run the server
     */
    private void run() {
        try {
            socket = new DatagramSocket(port);
            packet = new DatagramPacket(new byte[512], 512);
            System.out.println("Listening on 127.0.0.1:" + port);
            getPrime();
            keyAgreement();
            getMessage();
            socket.close();
        } catch(Exception e) {
            System.err.println("Critical error occured");
        }
    }
    /**
     * Agree on a prime with the client
     */
    public void getPrime() throws Exception {
        socket.receive(packet);
        clientIp = packet.getAddress();
        clientPort = packet.getPort();
        prime = new BigInteger(new String(packet.getData(), 0, packet.getLength()));
        System.out.println("Recieved a prime from the client: " + prime);
    }
    /**
     * Generate keys and exchange public key with client
     */
    public void keyAgreement() throws Exception {
        System.out.println("Generating a private key");
        BigInteger priKey = DH.genPrivateKey(prime);
        System.out.println("Private key: " + priKey);
        System.out.println("Generating a public key");
        BigInteger pubKey = DH.genPublicKey(priKey, prime);
        System.out.println("Public key: " + pubKey);
        System.out.println("Sending public key to client");
        byte[] pubKeyBuffer = pubKey.toString().getBytes();
        packet = new DatagramPacket(pubKeyBuffer, pubKeyBuffer.length, clientIp, clientPort);
        socket.send(packet);
        socket.receive(packet);
        System.out.println("Recieved client's public key: " + new BigInteger(new String(packet.getData())));
        sessionKey = DH.genSessionKey(new BigInteger(new String(packet.getData(), 0, packet.getLength())), priKey, prime).toByteArray();
        System.out.println("A shared key has been agreed upon: " + sessionKey);
    }
    /**
     * Get message from client
     */
    public void getMessage() throws Exception {
        byte[] ciphertext = new byte[16];
        for(int i = 0; i < 16; i++) {
            socket.receive(packet);
            ciphertext[i] = packet.getData()[0];
        }
        byte[] message = AES.decrypt(sessionKey, ciphertext);
        String messageStr = new String(message, 0, message.length);
        System.out.println("Recieved message: " + messageStr);
    }
}
