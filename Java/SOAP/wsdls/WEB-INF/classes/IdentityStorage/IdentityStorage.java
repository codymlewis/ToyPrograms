package IdentityStorage;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.security.SecureRandom;
/**
 * IdentityStorage - SENG3400A2
 * Handle logins and logouts
 *
 * @author Cody Lewis
 * @since 2018-10-07
 */
public class IdentityStorage {
    private static HashMap<String, String> logins = new HashMap<>(); // map login to password
    private static HashMap<String, String> usersFromSessionKeys = new HashMap<>(); // find users from session keys
    private static HashMap<String, String> sessionKeysFromUsers = new HashMap<>(); // find sessionKeys from users
    private static final int KEY_LENGTH = 5; // length of generated keys
    private static final int ASCII_START = 33; // first outputable char in ASCII
    private static final int ASCII_END = 127; // last outputable char in ASCII + 1
    private static Boolean inited = false;
    /**
     * Simulate a constructor
     */
    public static void init() {
        if(!inited) {
            logins.put("hayden", "1234");
            logins.put("josh", "4321");
            inited = true;
        }
    }
    /**
     * Login to the service
     * @param username A username
     * @param password A password to correspond to the username
     * @return "Invalid" if there username password pair is wrong else a random 5 char session key
     */
    public static String login(String username, String password) {
        String login = logins.containsKey(username) ? logins.get(username) : null;
        if(login != null && login.equals(password)) {
            if(usersFromSessionKeys.containsKey(username)) {
                return usersFromSessionKeys.get(username);
            }
            return genKey(username);
        }
        return "Invalid";
    }
    /**
     * Generate a session key
     * @param username The name of the user this key is being generated for
     * @return A random String of characters as a session key
     */
    public static String genKey(String username) {
        char[] key = new char[KEY_LENGTH];
        SecureRandom random = new SecureRandom();
        int randInt;
        for(int i = 0; i < key.length; ++i) {
            randInt = random.nextInt(ASCII_END - ASCII_START);
            key[i] = (char) (randInt + ASCII_START);
        }
        String keyString = new String(key);
        usersFromSessionKeys.put(keyString, username);
        sessionKeysFromUsers.put(username, keyString);
        return keyString;
    }
    /**
     * Log a user out from the system
     * @param key The user's session key
     * @return true if the user was logged out else false
     */
    public static Boolean logout(String key) {
        if(!usersFromSessionKeys.containsKey(key)) {
            return false;
        }
        String user = usersFromSessionKeys.get(key);
        usersFromSessionKeys.remove(key);
        sessionKeysFromUsers.remove(user);
        return true;
    }
    /**
     * Check whether a session key is being used
     * @param key The session key to check
     * @return true if the key is being used else false
     */
    public static Boolean keyInUse(String key) {
        return usersFromSessionKeys.containsKey(key);
    }
}
