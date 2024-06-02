package org.vaadin.UI.Presenter;
import com.vaadin.flow.component.UI;
import org.vaadin.UI.view.Interface.ISignUpView;

public class SignUpPresenter {

    private ISignUpView view;

    public SignUpPresenter(ISignUpView view) {
        this.view = view;
        this.view.setPresenter(this);
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

