package servlet;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static final Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "admin");
        users.put("user", "user");
    }

    public static Boolean isValid(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        if (users.containsKey(username)) {
            return users.get(username).equals(password);
        }
        return false;
    }

    public static boolean addUser(String username, String password, String confirmPassword) {
        if (username == null || password == null || confirmPassword == null) {
            return false;
        }
        if (users.containsKey(username) || !password.equals(confirmPassword)) {
            return false;
        } else {
            users.put(username, password);
        }
        return true;
    }
}
