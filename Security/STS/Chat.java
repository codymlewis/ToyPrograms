import java.util.Scanner;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;

/**
 * Shared functions and variables between ChatServer and ChatClient
 *
 * @author Cody Lewis
 * @since 2018-08-19 (Was taken and adapted from one of my other assignments)
 */
public class Chat {
    protected Scanner console;
    protected PrintWriter out;
    protected BufferedReader in;
    protected Socket cliSocket;
    protected InetAddress address;
    protected int port;
    protected STS sts;

    /**
     * Enum of the various error codes returned by the classes
     */
    protected enum errorCodes {
        DEFAULT(-1), UNKNOWNHOSTERROR(-2), IOERROR(-3), NULLERROR(-4), INVALIDCRYPTO(-5), PORT(-6),
        FAILEDVERIFICATION(-7);

        private final int code; // error code variable

        /**
         * Default constructor
         */
        private errorCodes(int code) {
            this.code = code;
        }

        /**
         * Get the error code value
         * 
         * @return the error code of this
         */
        public int value() {
            return code;
        }
    }

    /**
     * Default Constructor
     */
    public Chat() throws NoSuchAlgorithmException, InvalidKeyException {
        console = new Scanner(System.in);
        sts = new STS();
    }

    /**
     * Reset the crypto system held in this
     */
    protected void resetCrypto() throws NoSuchAlgorithmException, InvalidKeyException {
        sts = new STS();
    }

    /**
     * The chat message rules
     * 
     * @return A String containing the rules of the chat's messaging
     */
    protected String rules() {
        return "Press enter to send a message,\nType DISCONNECT to end the chat\n";
    }

    /**
     * Take an input from the users and give an out suitable to put into a message
     * 
     * @return A String of the input formatted to be embedded in a SCP message
     */
    protected String textToMessage() throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, NoSuchPaddingException {
        String message = console.nextLine();
        if (message.indexOf("DISCONNECT") > -1) {
            return "DISCONNECT";
        }
        return textToMessage(message);
    }

    /**
     * Encryped a given message using the Crypto system held in this
     * 
     * @param message The message to encrypt
     * @return Cipher-text corresponding to the message
     */
    protected String textToMessage(String message) throws NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
        return sts.encrypt(message);
    }

    /**
     * Recieve a message from the other user
     */
    protected String recieveMessage() throws IOException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
        return sts.decrypt(in.readLine());
    }
}
