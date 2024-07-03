package org.vaadin.UI.Util;

import org.vaadin.UI.model.models.LoginModel;

public class Credentials {

    private static String token;
    private static String userName = "guest";
    private static boolean isLogedIn = false;
    public static String getToken(){
        if (token == null){
            LoginModel.guestEntry();// Log in as guest and set guest token
        }
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

    public static void setGuestToken(String value, String user) {
        token = value;
        userName = user;
    }

    public static String getUserName() {
        return userName;
    }
    public static void logOut(){
        isLogedIn = false;
        LoginModel.guestEntry(); // Log in as guest and set guest token
        userName = "guest";
    }

    public static boolean isIsLogedIn() {
        return isLogedIn;
    }
}
