package org.vaadin.UI.view.ViewInterface;

public interface ISignUpView {
    void showNotification(String message);
    String getUsername();
    String getPassword();
    String getEmail();
}