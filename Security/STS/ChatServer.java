import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;

/**
 * A socket based half duplex chat server
 *
 * @author Cody Lewis
 * @since 2018-08-10 (Was taken and adapted from one of my other assignments)
 */
public class ChatServer extends Chat {
    private ServerSocket serverSocket;
    private String welcomeMessage;
    private static final int BACKLOG = 1; // Max length for queue of messages

    /**
     * Default Constructor
     */
    public ChatServer() throws NoSuchAlgorithmException, InvalidKeyException {
        super();
    }

    /**
     * The main thread
     * 
     * @param args command line arguments
     */
    public static void main(String args[]) {
        try {
            ChatServer cs = new ChatServer();
            int exitVal = cs.run(args);
            System.exit(exitVal);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(errorCodes.INVALIDCRYPTO.value());
        }
    }

    /**
     * The main flow of the program
     * 
     * @param args The arguments sent in with the program
     * @return an end status int
     */
    public int run(String args[]) {
        try {
            address = args.length > 0 ? InetAddress.getByName(args[0]) : InetAddress.getLocalHost();
            port = args.length > 1 ? Integer.parseInt(args[1]) : 2250;
            if (port < 1024) {
                throw new PortException();
            }
            welcomeMessage = args.length > 2 ? args[2] : "Welcome to Chat";
            System.out.format("Starting server on %s:%d\n", address.getHostAddress(), port);
            startSocket();
            System.out.println("Started server");
            while (true) {
                hostConnection();
                resetCrypto();
            }
        } catch (IOException ioe) {
            System.err.println("Error: " + ioe.getMessage());
            return errorCodes.IOERROR.value();
        } catch (PortException pe) {
            System.err.println("Error: " + pe.getMessage());
            return errorCodes.PORT.value();
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                | NoSuchPaddingException | InvalidKeySpecException ie) {
            System.err.println("Error: " + ie.getMessage());
            return errorCodes.INVALIDCRYPTO.value();
        } catch (FailedVerificationException fve) {
            System.err.println("Error: " + fve.getMessage());
            return errorCodes.FAILEDVERIFICATION.value();
        }
    }

    /**
     * Host a client connection
     */
    private void hostConnection()
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, FailedVerificationException {
        try {
            System.out.println("Waiting for client to connect");
            acceptClient();
            System.out.println("Client successfully connected");
            System.out.println("Authenticating with client");
            sts.startSTS(in, out);
            System.out.println("Successfully authenticated with the client");
            System.out.println();
            messageLoop();
        } catch (NullPointerException npe) {
            System.out.println("\nError: unexpected cut-off from client, looking for new client");
        }
    }

    /**
     * Loop for sending a recieving messages
     */
    private void messageLoop() throws IOException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
        String message = welcomeMessage + "\n" + rules() + "\n"; // send welcome message + chat rules to client
        String recievedMessage;
        boolean disconnect = false;
        System.out.println(rules());
        out.println(textToMessage(message));
        while (!disconnect) { // I assume this gets sigkilled to end
            System.out.print(String.format("The client is typing..."));
            recievedMessage = recieveMessage();
            System.out.println();
            if (recievedMessage.equals("DISCONNECT")) {
                System.out.println("Client disconnected");
                disconnect = true;
                break;
            }
            System.out.format("Client: %s\n", recievedMessage);
            System.out.print("Send a message: ");
            message = textToMessage();
            if (message.compareTo("DISCONNECT") == 0) {
                out.println(textToMessage("DISCONNECT"));
                disconnect = true;
            }
            out.println(message);
            System.out.println("Waiting for message to send");
        }
    }

    /**
     * Start a server socket
     * 
     * @param hostAddress The ip address for the host server
     * @param port        The port to run the server on
     * @return True on successful start
     */
    private boolean startSocket() throws IOException {
        serverSocket = new ServerSocket(port, BACKLOG, address);
        return true;
    }

    /**
     * Accept a client into the server
     * 
     * @return True on client connection
     */
    private boolean acceptClient() throws IOException {
        cliSocket = serverSocket.accept();
        out = new PrintWriter(cliSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(cliSocket.getInputStream()));
        return true;
    }
}
