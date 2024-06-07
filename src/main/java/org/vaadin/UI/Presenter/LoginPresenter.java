package org.vaadin.UI.Presenter;

import org.vaadin.UI.view.ViewInterface.ILoginView;
import org.vaadin.UI.view.ViewInterface.ILoginView;

public class LoginPresenter  {
    private final ILoginView view;
    //TODO:: This presenter will hold credentials of the performing user
    public LoginPresenter(ILoginView view) {
        this.view = view;
    }

    public void handleLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        // Add your login logic here
        if (username.isEmpty() || password.isEmpty()) {
            view.showNotification("Username and password cannot be empty.");
        } else {
            view.showNotification("Login successful for user: " + username);
        }
    }

    public void handleLogout() {
        //TODO::send request to backend to log out user if loggged n
    }
}