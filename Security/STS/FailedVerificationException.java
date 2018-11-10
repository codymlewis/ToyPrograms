/**
 * A custom exception for verification failures
 *
 * @author Cody Lewis
 * @since 2018-11-07
 */
public class FailedVerificationException extends Exception {
    public static final long serialVersionUID = 1L;
    /**
     * Default Constructor
     */
    public FailedVerificationException() {
        super("Failed to verify the other user as they are.");
    }
}
