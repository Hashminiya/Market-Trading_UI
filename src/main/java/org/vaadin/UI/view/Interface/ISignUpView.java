package org.vaadin.UI.view.Interface;

import org.vaadin.UI.Presenter.SignUpPresenter;

public interface ISignUpView {
    void showNotification(String message);
    String getUsername();
    String getPassword();
    String getEmail();
}
