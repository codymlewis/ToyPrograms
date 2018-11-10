/**
 * LoginException - SENG3400A2
 * Exception to be thrown when login fails
 *
 * @author Cody Lewis
 * @since 2018-10-17
 */
public class ConsoleException extends Exception {
    private static final long serialVersionUID = 1L;
    public ConsoleException() {
        super("Could not access the console, please open this program in a terminal");
    }
}
