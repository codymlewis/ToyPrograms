import java.util.Scanner;
import java.io.Console;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import localhost.axis.IdentityService.IdentityService_jws.*;
import localhost.axis.CurrencyService.Admin_jws.*;
/**
 * Conversion - SENG3400A2
 * Currency conversion client class
 *
 * @author Cody Lewis
 * @since 2018-10-11
 */
public class AdminClient extends Client {
    private IdentityService identityService;
    private Admin admin;
    private String sessionKey;
    private Console console;
    private Scanner input;
    /**
     * The main thread
     */
    public static void main(String[] args) {
        AdminClient ac = new AdminClient();
        System.exit(ac.run(args));
    }
    /**
     * Default constructor
     */
    public AdminClient() {
        try {
            console = System.console();
            input = new Scanner(System.in);
            IdentityServiceService iss = new IdentityServiceServiceLocator(); // summoning a space station
            identityService = iss.getIdentityService();
            AdminService as = new AdminServiceLocator();
            admin = as.getAdmin();
        } catch(ServiceException se) {
            System.out.println("Error: " + se.getMessage());
            System.exit(errorCodes.SERVICE.value());
        }
    }
    /**
     * Main driver method
     * @param args The command line arguments
     */
    private int run(String[] args) {
        try {
            if(args.length < 1) {
                System.out.println("No username specified");
                throw new LoginException();
            }
            String username = args[0];
            if(console == null) {
                throw new ConsoleException();
            }
            System.out.print("Password: ");
            String password = new String(console.readPassword());
            sessionKey = login(username, password);
            if(sessionKey.equals("Invalid")) {
                throw new LoginException();
            }
            System.out.println("Welcome, " + username);
            loop();
            return 0;
        } catch(LoginException le) {
            System.err.println("Error: " + le.getMessage());
            return errorCodes.LOGIN.value();
        } catch(AuthException ae) {
            System.err.println("Error: " + ae.getMessage());
            return errorCodes.AUTH.value();
        } catch(RemoteException re) {
            String errorMessage = re.getMessage();
            if(re.getCause() != null) { // check for nested exception
                errorMessage = re.getCause().getMessage();
            }
            System.err.println("Error: " + errorMessage);
            return errorCodes.REMOTE.value();
        } catch(ConsoleException ce) {
            System.err.println("Error: " + ce.getMessage());
            return errorCodes.CONSOLE.value();
        }
    }
    private void loop() throws RemoteException, AuthException, ConsoleException {
        boolean exit = false;
        Scanner sstream;
        System.out.println(help());
        Boolean status;
        while(!exit) {
            System.out.print("\nWhat would you like to do? >> ");
            input = new Scanner(System.in);
            String line = input.nextLine();
            sstream = new Scanner(line);
            switch(sstream.next()) {
                case "addCurrency":
                    addCurrency(sstream);
                    break;
                case "removeCurrency":
                    removeCurrency(sstream);
                    break;
                case "listCurrencies":
                    listCurrencies();
                    break;
                case "conversionsFor":
                    conversionsFor(sstream);
                    break;
                case "addRate":
                    addRate(sstream);
                    break;
                case "removeRate":
                    removeRate(sstream);
                    break;
                case "listRates":
                    listRates();
                    break;
                case "help":
                    System.out.println(help());
                    break;
                case "logout":
                    if(logout()) {
                        System.out.println("Logout Successful");
                    } else {
                        System.out.println("Logout failed");
                    }
                    System.out.println("Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("No such command, please try again.");
                    break;
            }
        }
    }
    /**
     * Login as an admin
     * @param username The name of the user
     * @param password The password key for the user
     * @return A session key
     */
    private String login(String username, String password) throws RemoteException {
        return identityService.login(username, password);
    }
    /**
     * Add a currency to the system
     * @param sstream A String stream of the prior input
     */
    private void addCurrency(Scanner sstream) throws AuthException, RemoteException {
        String currencyCode;
        if(sstream.hasNext()) {
            currencyCode = sstream.next();
        } else {
            System.out.print("Enter a currency to add: ");
            currencyCode = input.next();
        }
        Boolean status = admin.addCurrency(sessionKey, currencyCode);
        if(status == null) {
            throw new AuthException();
        } else if(status) {
            System.out.println("Successfully added " + currencyCode);
        } else {
            System.out.println("Failed to add " + currencyCode);
        }
        return ;
    }
    /**
     * Remove a currency from the system
     * @param sstream A String stream of the prior input
     */
    private void removeCurrency(Scanner sstream) throws AuthException, RemoteException {
        String currencyCode;
        if(sstream.hasNext()) {
            currencyCode = sstream.next();
        } else {
            System.out.println("Enter a currency to remove: ");
            currencyCode = input.next();
        }
        Boolean status = admin.removeCurrency(sessionKey, currencyCode);
        if(status == null) {
            throw new AuthException();
        } else if(status) {
            System.out.println("Successfully removed " + currencyCode);
        } else {
            System.out.println("Failed to remove " + currencyCode);
        }
    }
    /**
     * List the currencies
     */
    private void listCurrencies() throws AuthException, RemoteException {
        String[] currencies = admin.listCurrencies(sessionKey);
        if(currencies == null) {
            throw new AuthException();
        }
        if(currencies.length == 0) {
            System.out.println("There are no currencies");
        } else {
            String result = "";
            for(String currency : currencies) {
                result += "\t-" + currency + "\n";
            }
            System.out.print(result);
        }
    }
    /**
     * List the conversions for a currency
     */
    private void conversionsFor(Scanner sstream) throws AuthException, RemoteException {
        String currencyCode;
        if(sstream.hasNext()) {
            currencyCode = sstream.next();
        } else {
            System.out.print("Enter a currency: ");
            currencyCode = input.next();
        }
        String[] conversions = admin.conversionsFor(sessionKey, currencyCode);
        if(conversions == null) {
            throw new AuthException();
        }
        if(conversions.length == 0) {
            System.out.println("There are no conversions");
        }
        String result = "";
        for(String conversion : conversions) {
            result += conversion + "\n";
        }
        System.out.print(result);
    }
    /**
     * Add a rate of conversion between 2 currencies
     * @param sstream A String stream of the prior input
     */
    private void addRate(Scanner sstream) throws AuthException, RemoteException {
        String fromCurrencyCode;
        if(sstream.hasNext()) {
            fromCurrencyCode = sstream.next();
        } else {
            System.out.print("Enter a currency to convert from: ");
            fromCurrencyCode = input.next();
        }
        String toCurrencyCode;
        if(sstream.hasNext()) {
            toCurrencyCode = sstream.next();
        } else {
            System.out.print("Enter a currency to convert to: ");
            toCurrencyCode = input.next();
        }
        Double rate = new Double(0); // initialized to make the compiler happy
        boolean isNeg = true;
        while(isNeg) { // can't have negative rates
            if(sstream.hasNext()) {
                rate = Double.parseDouble(sstream.next());
            } else {
                System.out.format("Enter the conversion rate between %s and %s: ", fromCurrencyCode, toCurrencyCode);
                rate = Double.parseDouble(input.next());
            }
            if(rate.compareTo(new Double(0)) >= 0) {
                isNeg = false;
            }
            if(isNeg) {
                System.out.println("Please input a positive rate.");
            }
        }
        Boolean status = admin.addRate(sessionKey, fromCurrencyCode, toCurrencyCode, rate);
        if(status == null) {
            throw new AuthException();
        } else if(status) {
            System.out.println("Successfully added " + fromCurrencyCode + "-" +
                    toCurrencyCode + ":" + rate);
        } else {
            System.out.println("Failed to add " + fromCurrencyCode + "-" +
                    toCurrencyCode + ":" + rate);
        }
    }
    private void removeRate(Scanner sstream) throws AuthException, RemoteException {
        String fromCurrencyCode;
        if(sstream.hasNext()) {
            fromCurrencyCode = sstream.next();
        } else {
            System.out.print("Enter a currency to convert from: ");
            fromCurrencyCode = input.next();
        }
        String toCurrencyCode;
        if(sstream.hasNext()) {
            toCurrencyCode = sstream.next();
        } else {
            System.out.print("Enter a currency to convert to: ");
            toCurrencyCode = input.next();
        }
        Boolean status = admin.removeRate(sessionKey, fromCurrencyCode, toCurrencyCode);
        if(status == null) {
            throw new AuthException();
        } else if(status) {
            System.out.println("Successfully removed " + fromCurrencyCode + "-" + toCurrencyCode);
        } else {
            System.out.println("Failed to remove " + fromCurrencyCode + "-" + toCurrencyCode);
        }
    }
    /**
     * List the conversion rates
     */
    private void listRates() throws AuthException, RemoteException {
        String[] currencies = admin.listRates(sessionKey);
        if(currencies == null) {
            throw new AuthException();
        }
        if(currencies.length == 0) {
            System.out.println("There are no rates");
        } else {
            String result = "";
            for(String currency : currencies) {
                result += currency + "\n";
            }
            System.out.print(result);
        }
    }
    /**
     * Logout from the system
     * @param sessionKey The key of the current session
     * @return true if successfully logged out else false
     */
    private Boolean logout() throws RemoteException {
        return identityService.logout(sessionKey);
    }
    /**
     * Get the help message for the program
     * @return A String contianing help for the program
     */
    @Override
    public String help() {
        return "Commands:\n" +
            "\t-addCurrency <currencyCode>\n" +
            "\t-removeCurrency <currencyCode>\n" +
            "\t-listCurrencies\n" +
            "\t-conversionsFor <currencyCode>\n" +
            "\t-addRate <fromCurrencyCode> <toCurrencyCode> <rate>\n" +
            "\t-removeRate <fromCurrencyCode> <toCurrencyCode>\n" +
            "\t-listRates\n" +
            "\t-help\n" +
            "\t-logout";
    }
}
