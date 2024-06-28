package org.vaadin.UI.Presenter;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.Notifications.WebSocketHandler;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.Util.SuccessCallBack;
import org.vaadin.UI.model.models.LoginModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.LogOutView;
import org.vaadin.UI.view.ViewInterface.ILoginView;
import org.vaadin.UI.view.ViewTemplate;

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
    }
}
