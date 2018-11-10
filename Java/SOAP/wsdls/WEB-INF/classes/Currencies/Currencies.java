package Currencies;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
/**
 * Currencies - SENG3400A2
 * Driver Endpoint for conversion of currencies
 *
 * @author Cody Lewis
 * @since 2018-10-08
 */
public class Currencies {
    private static HashMap<String, HashMap<String, Double>> conversionRates = new HashMap<>();
    private static Boolean inited = false;
    /**
     * Simulate a Constructor
     */
    public static void init() {
        if(!inited) {
            addRate("AUD", "USD", 0.7d);
            addRate("AUD", "NZD", 1.09d);
            addRate("AUD", "GBP", 0.55d);
            inited = true;
        }
    }
    /**
     * Add a currency unit
     * @param currencyCode The name of the currency to add
     * @return true if the currency is added else false
     */
    public static Boolean addCurrency(String currencyCode) {
        if(!conversionRates.containsKey(currencyCode)) {
            conversionRates.put(currencyCode, new HashMap<String, Double>());
            return true;
        }
        return false;
    }
    /**
     * Remove a currency unit
     * @param currencyCode The name of the currency to remove
     * @return true if a currency is removed else false
     */
    public static Boolean removeCurrency(String currencyCode) {
        if(conversionRates.containsKey(currencyCode)) {
            conversionRates.remove(currencyCode);
            for(HashMap<String, Double> residue : conversionRates.values()) { // get rid of the residue values
                if(residue.containsKey(currencyCode)) {
                    residue.remove(currencyCode);
                }
            }
            return true;
        }
        return false;
    }
    /**
     * Add a currency to the conversionRates
     * @param from The currency code to convert from
     * @param to The currency code to convert to
     * @param rate The rate of conversion between the from and to
     * @return true on completion
     */
    public static Boolean addRate(String from, String to, Double rate) {
        HashMap<String, Double> fromRates = conversionRates.containsKey(from) ?
            conversionRates.get(from) : new HashMap<>();
        fromRates.put(to, rate);
        conversionRates.put(from, fromRates);
        HashMap<String, Double> toRates = conversionRates.containsKey(to) ?
            conversionRates.get(to) : new HashMap<>();
        toRates.put(from, 1 / rate);
        conversionRates.put(to, toRates);
        return true;
    }
    /**
     * Update the rate for trading between 2 currencies
     * @param from Name of the currency to trade from
     * @param to Name of the currency to trade to
     * @param rate The new rate to set to
     * @return true if the rate is updated else false
     */
    public static Boolean updateRate(String from, String to, Double rate) {
        if(conversionRates.containsKey(from) && conversionRates.containsKey(to)) {
            conversionRates.get(from).put(to, rate);
            conversionRates.get(to).put(from, rate);
            return true;
        }
        return false;
    }
    /**
     * Remove the rate for trading between 2 currencies
     * @param from Name of the currency to trade from
     * @param to Name of the currency to trade to
     * @return true if the rate is updated else false
     */
    public static Boolean removeRate(String from, String to) {
        if(conversionRates.containsKey(from) && conversionRates.containsKey(to)) {
            conversionRates.get(from).remove(to);
            conversionRates.get(to).remove(from);
            return true;
        }
        return false;
    }
    /**
     * List the currencies in the system
     * @return An array of strings stating the names of the currencies
     */
    public static String[] listCurrencies() {
        ArrayList<String> currenciesList = new ArrayList<>();
        for(String currency : conversionRates.keySet()) {
            currenciesList.add(currency);
        }
        return toArray(currenciesList);
    }
    /**
     * Find all of the available conversions for a specified currency
     * @param currencyCode Name of the currency
     * @return An array of Strings stating the things the currency can convert into
     */
    public static String[] conversionsFor(String currencyCode) {
        ArrayList<String> conversions = new ArrayList<>();
        for(Map.Entry<String, Double> conversion : conversionRates.get(currencyCode).entrySet()) {
            conversions.add(String.format("%s-%s:%.4f", currencyCode, conversion.getKey(), conversion.getValue()));
        }
        return toArray(conversions);
    }
    /**
     * Get a list of the conversions rates
     * @return An array of Strings showing the conversion rates
     */
    public static String[] listRates() {
        ArrayList<String> conversionsString = new ArrayList<>();
        for(Map.Entry<String, HashMap<String, Double>> from : conversionRates.entrySet()) {
            for(Map.Entry<String, Double> to : from.getValue().entrySet()) {
                if(to.getValue() != null) {
                    conversionsString.add(String.format("%s-%s:%.4f", from.getKey(), to.getKey(), to.getValue()));
                }
            }
        }
        return toArray(conversionsString);
    }
    /**
     * Convert an ArrayList of Strings to an array of Strings, as ArrayList.toString() has casting issues
     * @param convert The ArrayList to convert
     * @return A String array holding the contents of the ArrayList
     */
    private static String[] toArray(ArrayList<String> convert) {
        String[] result = new String[convert.size()];
        for(int i = 0; i < result.length; ++i) {
            result[i] = convert.get(i);
        }
        return result;
    }
    /**
     * Find the from conversion rate from a specified currency to  specified currency
     * @param fromCurrencyCode The code of the currency to convert from
     * @param toCurrencyCode The code of the currency to convert to
     * @return -1.0 if they have no conversion else their conversion rate
     */
    public static Double rateOf(String fromCurrencyCode, String toCurrencyCode) {
        if(!conversionRates.containsKey(fromCurrencyCode)) {
            return -1.0d;
        }
        HashMap<String, Double> from = conversionRates.get(fromCurrencyCode);
        if(!from.containsKey(toCurrencyCode)) {
            return -1.0d;
        }
        return from.get(toCurrencyCode);
    }
    /**
     * Calculate the conversion of an amount between 2 specified currencies
     * @param fromCurrencyCode The code of the currency to convert from
     * @param toCurrencyCode The code of the currency to convert to
     * @param amount The amount to convert
     * @return -1.0 if there is no conversion rate else their amount * rate * 0.99
     */
    public static Double convert(String fromCurrencyCode, String toCurrencyCode, Double amount) {
        Double rate = rateOf(fromCurrencyCode, toCurrencyCode);
        if(rate.equals(-1.0d)) {
            return -1.0d;
        }
        return amount * rate * 0.99d;
    }
}
