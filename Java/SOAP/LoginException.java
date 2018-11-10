/**
 * LoginException - SENG3400A2
 * Exception to be thrown when login fails
 *
 * @author Cody Lewis
 * @since 2018-10-17
 */
public class LoginException extends Exception {
    private static final long serialVersionUID = 1L;
    public LoginException() {
        super("Could not login to the system, " +
                " either your username or password is incorrect, please try again.");
    }
}
