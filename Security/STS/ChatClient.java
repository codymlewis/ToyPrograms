import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.InvalidKeyException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;

/**
 * A socket based half duplex chat client
 *
 * @author Cody Lewis
 * @since 2018-08-10 (Was taken and adapted from one of my other assignments)
 */
public class ChatClient extends Chat {
    /**
     * Default constructor
     */
    public ChatClient() throws NoSuchAlgorithmException, InvalidKeyException {
        super();
    }

    /**
     * The main thread
     * 
     * @param args command line arguments
     */
    public static void main(String args[]) {
        try {
            ChatClient cc = new ChatClient();
            int exitVal = cc.run(args);
            System.exit(exitVal);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(errorCodes.INVALIDCRYPTO.value());
        }
    }

    /**
     * Main flow of the program
     * 
     * @param args arguments sent in with the program
     * @return an end status int
     */
    public int run(String args[]) {
        try {
            address = args.length > 0 ? InetAddress.getByName(args[0]) : InetAddress.getLocalHost();
            port = args.length > 1 ? Integer.parseInt(args[1]) : 2250;
            if (port < 1024) {
                throw new PortException();
            }
            System.out.println(String.format("Connecting to %s:%d", address.getHostAddress(), port));
            connectToServer();
            System.out.println("Connected to server");
            System.out.println("Started Authenticating");
            sts.joinSTS(out, in);
            System.out.println("Successfully Authenticated");
            System.out.println("Connected to Chat");
            messageLoop();
            console.close();
            return 0;
        } catch (UnknownHostException uhe) {
            System.err.println("Error: " + uhe.getMessage());
            return errorCodes.UNKNOWNHOSTERROR.value();
        } catch (IOException ioe) {
            System.err.println("Error: " + ioe.getMessage());
            return errorCodes.IOERROR.value();
        } catch (NullPointerException npe) {
            System.out.println("\nError: unexpected cutoff from Server, ending program");
            return errorCodes.NULLERROR.value();
        } catch (PortException pe) {
            System.out.println("\nError: " + pe.getMessage());
            return errorCodes.PORT.value();
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | IllegalBlockSizeException
                | BadPaddingException | NoSuchPaddingException ie) {
            System.out.println("\nError: " + ie.getMessage());
            return errorCodes.INVALIDCRYPTO.value();
        } catch (FailedVerificationException fve) {
            System.out.println("\nError: " + fve.getMessage());
            return errorCodes.FAILEDVERIFICATION.value();
        }
    }

    /**
     * Loop for sending and recieving messages
     */
    private void messageLoop() throws IOException, NullPointerException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
        String message;
        String recievedMessage;
        boolean disconnect = false;
        while (!disconnect) {
            recievedMessage = recieveMessage();
            System.out.println();
            if (recievedMessage.equals("DISCONNECT")) {
                System.out.println("Server disconnected");
                disconnect = true;
                break;
            }
            System.out.println(String.format("Server: %s", recievedMessage));
            System.out.print("Send a message: ");
            message = textToMessage();
            if (message.equals("DISCONNECT")) {
                out.println(textToMessage("DISCONNECT"));
                System.out.println("Disconnected from server");
                disconnect = true;
                break;
            }
            System.out.println("Waiting for message to send");
            out.println(message);
            System.out.print("Server is typing...");
        }
    }

    /**
     * Connect to the server
     * 
     * @param hostName the name of the server
     * @param port     the port number the server is running on
     * @return true on successful connection
     */
    private boolean connectToServer() throws UnknownHostException, IOException {
        cliSocket = new Socket(address.getHostAddress(), port);
        out = new PrintWriter(cliSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(cliSocket.getInputStream()));
        return true;
    }
}
