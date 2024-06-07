package org.vaadin.UI.view.ViewInterface;

public interface ISignUpView {
    String getUsername();
    String getPassword();
    String getAge();
    void showNotification(String message);
}
