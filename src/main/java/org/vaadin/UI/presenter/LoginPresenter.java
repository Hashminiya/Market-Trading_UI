package org.vaadin.UI.presenter;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.models.LoginModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.ViewInterface.ILoginView;

public class LoginPresenter implements IPresenter {

    private final ILoginView view;
    private final LoginModel model;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.model = new LoginModel();
    }

    public void onLogin() {
        String result = model.login(view.getUsername(), view.getPassword());
        view.showNotification(result);
        if (result.contains("Login successful")) {
            view.updateUserDisplayName(view.getUsername());
        }
    }

    @Override
    public void onViewLoaded() {
    }

    @Override
    public void onViewStopped() {
    }
}
