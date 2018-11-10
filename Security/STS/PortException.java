/**
 * A custom exception for port allocation
 * 
 * @author Cody Lewis
 * @since 2018-11-07
 */
public class PortException extends Exception {
    public static final long serialVersionUID = 1L;
    /**
     * Default Constructor
     */
    public PortException() {
        super("Using a port number 1023 or lower may interrupt system operations");
    }
}