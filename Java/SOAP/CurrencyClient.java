import java.util.Scanner;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import localhost.axis.CurrencyService.Conversion_jws.*;
/**
 * Conversion - SENG3400A2
 * Currency conversion client class
 *
 * @author Cody Lewis
 * @since 2018-10-09
 */
public class CurrencyClient extends Client {
    private Conversion conversion;
    private Scanner console;
    /**
     * The main thread
     */
    public static void main(String[] args) {
        CurrencyClient cc = new CurrencyClient();
        System.exit(cc.run());
    }
    /**
     * Default constructor
     */
    public CurrencyClient() {
        try {
            ConversionService cs = new ConversionServiceLocator();
            conversion = cs.getConversion();
        } catch (ServiceException se) {
            System.out.println("Error: " + se.getMessage());
            System.exit(errorCodes.SERVICE.value());
        }
    }
    /**
     * Main driver method
     */
    private int run() {
        try {
            boolean exit = false;
            Scanner sstream;
            System.out.println("Welcome to the currency client");
            System.out.println(help());
            while(!exit) {
                console = new Scanner(System.in);
                System.out.print("\nWhat would you like to do? >> ");
                String line = console.nextLine();
                sstream = new Scanner(line);
                switch(sstream.next()) {
                    case "convert":
                        convert(sstream);
                        break;
                    case "rateOf":
                        rateOf(sstream);
                        break;
                    case "listRates":
                        listRates();
                        break;
                    case "help":
                        System.out.println(help());
                        break;
                    case "exit":
                        System.out.println("Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("No such command, please try again.");
                        break;
                }
            }
            return 0;
        } catch(RemoteException re) {
            String errorMessage = re.getMessage();
            if(re.getCause() != null) {
                errorMessage = re.getCause().getMessage();
            }
            System.out.println("Error: " + errorMessage);
            return errorCodes.REMOTE.value();
        }
    }
    /**
     * Get a conversion of an amount between 2 currencies
     * @param sstream A string stream of inputs
     */
    private void convert(Scanner sstream) throws RemoteException {
        String from, to;
        Double amount;
        if(sstream.hasNext()) {
            from = sstream.next();
        } else {
            System.out.print("Input a the Currency to convert from: ");
            from = console.next();
        }
        if(sstream.hasNext()) {
            to = sstream.next();
        } else {
            System.out.print("Input a the Currency to convert to: ");
            to = console.next();
        }
        if(sstream.hasNext()) {
            amount = Double.parseDouble(sstream.next());
        } else {
            System.out.print("Input a the amount of " + from + " to convert: ");
            amount = Double.parseDouble(console.next());
        }
        Double conversionVal = conversion.convert(from, to, amount);
        if(conversionVal < 0) {
            System.out.format("There is no conversion between %s and %s\n", from, to);
        } else {
            System.out.format("%.4f%s converts to %.4f%s\n", amount, from, conversionVal, to);
        }
    }
    /**
     * Get the conversion rate between 2 currencies
     * @param sstream A string stream of inputs
     */
    private void rateOf(Scanner sstream) throws RemoteException {
        String from, to;
        if(sstream.hasNext()) {
            from = sstream.next();
        } else {
            System.out.print("Input a currency to convert from: ");
            from = console.next();
        }
        if(sstream.hasNext()) {
            to = sstream.next();
        } else {
            System.out.print("Input a currency to convert to: ");
            to = console.next();
        }
        Double conversionVal = conversion.rateOf(from, to);
        if(conversionVal < 0) {
            System.out.format("There is no conversion between %s and %s\n", from, to);
        } else {
            System.out.format("%s-%s: %.4f\n", from, to, conversionVal);
        }
    }
    /**
     * List all of the conversion rates
     */
    private void listRates() throws RemoteException {
        String result = "";
        String[] rateList = conversion.listRates();
        if(rateList != null) {
            for(String rate : rateList) {
                result += rate + "\n";
            }
            System.out.println(result.substring(0, result.length() - 1)); // remove final carriage return
        } else {
            System.out.println("Sorry, there are no conversion rates");
        }
    }
    /**
     * Get the help message for the program
     * @return A String contianing help for the program
     */
    @Override
    public String help() {
        return "Commands:\n" +
            "\t-convert <fromCurrency> <toCurrency> <amount>\n" +
            "\t-rateOf <fromCurrency> <toCurrency>\n" +
            "\t-listRates\n" +
            "\t-help\n" +
            "\t-exit";
    }
}
