/**
 * AuthException - SENG3400A2
 * Exception to be thrown when authorisation fails
 *
 * @author Cody Lewis
 * @since 2018-10-17
 */
public class AuthException extends Exception {
    private static final long serialVersionUID = 1L;
    public AuthException() {
        super("Could not authorise with the system, " +
                "your session key is invalid, try logging in again.");
    }
}
