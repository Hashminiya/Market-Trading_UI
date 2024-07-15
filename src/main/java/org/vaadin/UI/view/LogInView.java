package org.vaadin.UI.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Notifications.WebSocketHandler;
import org.vaadin.UI.Util.Messages;
import org.vaadin.UI.presenter.LoginPresenter;
import org.vaadin.UI.view.ViewInterface.ILoginView;

@Route("login")
public class LogInView extends ViewTemplate implements ILoginView {

    private final LoginPresenter presenter;
    private final TextField usernameField;
    private final PasswordField passwordField;

    public LogInView() {
        presenter = new LoginPresenter(this);
        usernameField = new TextField("Username");
        passwordField = new PasswordField("Password");
        Button loginButton = new Button("Login");
        loginButton.addClickListener(event -> presenter.onLogin(this::successfulLogin));
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout formLayout = new VerticalLayout(usernameField, passwordField, loginButton);
        add(formLayout);
    }

    @Override
    public void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(ui -> {
            QueryParameters queryParameters = ui.getInternals().getActiveViewLocation().getQueryParameters();
            if (queryParameters.getParameters().containsKey("username")) {
                usernameField.setValue(queryParameters.getParameters().get("username").get(0));
            }
        });
    }

    private void successfulLogin() {
        getUI().ifPresent(ui -> ui.navigate(""));
        WebSocketHandler.openConnection(Messages.getInstance());
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
