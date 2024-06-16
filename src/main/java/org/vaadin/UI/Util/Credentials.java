package org.vaadin.UI.Util;

import org.vaadin.UI.presenter.LoginPresenter;

public class Credentials {

    private static String token;
    private static String userName = "guest";
    private static boolean isLogedIn = false;
    public static String getToken(){
        return token;
    }

    public static void setToken(String value, String user) {
//        if (caller instanceof LoginPresenter) {
//            token = value;
//        } else {
//            throw new IllegalArgumentException("Setter method can only be accessed from login and guestSession");
//        }
        token = value;
        userName = user;
        isLogedIn = true;
    }

    public static String getUserName() {
        return userName;
    }
    public static void logOut(){
        isLogedIn = false;
        token = null;
        username = "guest";
    }

    public static boolean isIsLogedIn() {
        return isLogedIn;
    }
}
