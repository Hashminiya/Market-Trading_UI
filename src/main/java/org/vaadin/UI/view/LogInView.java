package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.LoginPresenter;
import org.vaadin.UI.view.Interfaces.ILoginView;


@Route("login")
public class LogInView extends ViewTemplate implements ILoginView {

    private final LoginPresenter presenter;
    private final TextField usernameField;
    private final PasswordField passwordField;

    public LogInView() {
        // Initialize presenter
        presenter = new LoginPresenter(this);

        // Create input fields
        usernameField = new TextField("Username");
        passwordField = new PasswordField("Password");

        // Create login button
        Button loginButton = new Button("Login");
        loginButton.addClickListener(event -> presenter.handleLogin());
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Add input fields and button to the layout
        VerticalLayout formLayout = new VerticalLayout(usernameField, passwordField, loginButton);
        add(formLayout);
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

    @Override
    void addManageStoresButton(HorizontalLayout layout) {
        layout.add(new Button("Manage Store"));
    }

    @Override
    void addLoginButton(HorizontalLayout layout) {
        layout.add(new Button("Login"));
    }

    @Override
    void addLogoutButton(HorizontalLayout layout) {
        layout.add(new Button("Logout"));
    }

    @Override
    void addSignupButton(HorizontalLayout layout) {
        layout.add(new Button("Sign Up"));
    }
}
