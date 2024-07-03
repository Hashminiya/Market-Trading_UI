package org.vaadin.UI.presenter;
import com.vaadin.flow.component.notification.Notification;
import org.vaadin.UI.Notifications.WebSocketHandler;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.Util.Messages;
import org.vaadin.UI.Util.SuccessCallBack;
import org.vaadin.UI.model.models.LoginModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.ViewTemplate;

import java.util.ArrayList;

public class LogoutPresenter implements IPresenter {
    private  ViewTemplate view;
    private final LoginModel model;

    public LogoutPresenter(ViewTemplate view) {
        this.view = view;
        this.model = new LoginModel();
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
    public void onLogOut(SuccessCallBack callBack){
        String result = model.logout(Credentials.getToken(),callBack);
        Notification.show(result);
        WebSocketHandler.closeConnection();
        Messages.getInstance().replace(new ArrayList<>());
        Messages.getInstance().seen();
    }
}
