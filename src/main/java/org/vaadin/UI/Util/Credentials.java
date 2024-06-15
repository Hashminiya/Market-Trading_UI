package org.vaadin.UI.Util;

public class Credentials {
    private static String token;
    private static String username = "guest";

    public static String getToken() {
        return token;
    }

    public static void setToken(String value, String user) {
        token = value;
        username = user;
    }

    public static String getUsername() {
        return username;
    }

    public static void clear() {
        token = null;
        username = "guest";
    }
}
