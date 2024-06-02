package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.SignUpPresenter;
import org.vaadin.UI.view.Interface.ISignUpView;

@Route(value = "sign-up", layout = ViewTemplate.class)
public class SignupView extends VerticalLayout implements ISignUpView {

    private final TextField usernameField;
    private final PasswordField passwordField;
    private final EmailField emailField;
    private final SignUpPresenter presenter;

    public SignupView() {

        presenter = new SignUpPresenter(this);
        usernameField = new TextField("Username");
        passwordField = new PasswordField("Password");
        emailField = new EmailField("Email");

        // Create sign-up button
        Button signUpButton = new Button("Sign Up");
        signUpButton.addClickListener(event -> {presenter.handleSignup();});

        signUpButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Add input fields and button to the layout
        VerticalLayout formLayout = new VerticalLayout(usernameField, passwordField, emailField, signUpButton);
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
    public String getEmail() {
        return emailField.getValue();
    }
}
