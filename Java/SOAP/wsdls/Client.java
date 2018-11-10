/**
 * Class containing the shared functions of the clients
 *
 * @author Cody Lewis
 * @since 2018-10-11
 */
public abstract class Client {
    /**
     * Default constructor
     */
    public Client() {}
    /**
     * Get a help message
     * @return A string containing a help message
     */
    protected abstract String help();
    /**
     * Enum containg the various error codes that may be called (For use on exit)
     */
    protected enum errorCodes {
        DEFAULT(-1),
        SERVICE(-2),
        REMOTE(-3),
        LOGIN(-4),
        AUTH(-5),
        CONSOLE(-6);
        private int code; // The error code
        /**
         * Constructor
         * @param code The code to set onto this
         */
        errorCodes(int code) { this.code = code; }
        /**
         * Get the value of the error code
         * @return The error code of this
         */
        public int value() { return code; }
    }
}
