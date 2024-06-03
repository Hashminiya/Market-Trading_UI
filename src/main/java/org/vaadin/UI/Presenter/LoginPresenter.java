package org.vaadin.UI.Presenter;

import org.vaadin.UI.view.Interfaces.ILoginView;
import org.vaadin.UI.view.Interfaces.ILoginView;

public class LoginPresenter {
    private final ILoginView view;

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
}
