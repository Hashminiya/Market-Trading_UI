package org.vaadin.UI.Util;

import org.vaadin.UI.Presenter.LoginPresenter;

public class Credentials {

    private static String token;

    public static String getToken(){
        return token;
    }

    public static void setToken(String value, Object caller) {
        if (caller instanceof LoginPresenter) {
            token = value;
        } else {
            throw new IllegalArgumentException("Setter method can only be accessed from login and guestSession");
        }
    }
}
