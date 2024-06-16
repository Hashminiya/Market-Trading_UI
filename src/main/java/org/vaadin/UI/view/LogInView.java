package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.presenter.LoginPresenter;

import org.vaadin.UI.view.ViewInterface.ILoginView;


@Route("login")
public class LogInView extends ViewTemplate implements ILoginView {

    private LoginPresenter presenter;
    private final TextField usernameField;
    private final PasswordField passwordField;

    public LogInView() {
        presenter = new LoginPresenter(this);
        usernameField = new TextField("Username");
        passwordField = new PasswordField("Password");
        Button loginButton = new Button("Login");
        loginButton.addClickListener(event -> {
            presenter.onLogin(() -> succesfullLogin());});
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout formLayout = new VerticalLayout(usernameField, passwordField, loginButton);
        add(formLayout);
    }

    private void succesfullLogin() {
        getUI().ifPresent(ui -> ui.navigate(""));
    }

    @Override
    public void showNotification(String message) {
        Notification.show(message);
    }

    @Override
    public String getUsername() {
        return usernameField.getValue();
    }

    @Override
    public String getPassword() {
        return passwordField.getValue();
    }

}