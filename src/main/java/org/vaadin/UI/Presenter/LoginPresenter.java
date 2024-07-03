package org.vaadin.UI.Presenter;

import org.vaadin.UI.Util.SuccessCallBack;
import org.vaadin.UI.model.models.LoginModel;
import org.vaadin.UI.Presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.ViewInterface.ILoginView;

public class LoginPresenter implements IPresenter {

    private final ILoginView view;
    private final LoginModel model;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.model = new LoginModel();
    }

    public void onLogin(SuccessCallBack callBack) {
        String result = model.login(view.getUsername(), view.getPassword(), callBack);
        view.showNotification(result);

    }

    @Override
    public void onViewLoaded() {
    }

    @Override
    public void onViewStopped() {
    }

}
