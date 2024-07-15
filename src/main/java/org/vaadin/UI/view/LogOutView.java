package org.vaadin.UI.view;

import com.vaadin.flow.router.Route;
import org.vaadin.UI.presenter.LogoutPresenter;

@Route("logout")
public class LogOutView extends ViewTemplate {
    private LogoutPresenter presenter;

    public LogOutView() {
        presenter = new LogoutPresenter(this);
        presenter.onLogOut(this::succesfullLogout);
    }

    private void succesfullLogout() {
        getUI().ifPresent(ui -> ui.navigate(""));
    }
}
