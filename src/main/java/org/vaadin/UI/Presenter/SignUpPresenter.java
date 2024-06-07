package org.vaadin.UI.presenter;
import com.vaadin.flow.component.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.UI.view.ViewInterface.ISignUpView;

@Component
public class SignUpPresenter  {

    private ISignUpView view;

    @Autowired
    public SignUpPresenter(ISignUpView view) {
        this.view = view;
    }

    public void handleSignup() {
        String username = view.getUsername();
        String password = view.getPassword();
        String email = view.getEmail();

        // Add your signup logic here, such as validation and service calls.
        // For example:
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            view.showNotification("Please fill in all fields.");
        } else {
            // Simulate a successful signup
            view.showNotification("Signup successful!");
            // Navigate to the homepage or another view if necessary
            // UI.getCurrent().navigate("");
        }
    }
}