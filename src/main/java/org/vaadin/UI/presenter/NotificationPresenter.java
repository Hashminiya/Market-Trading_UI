package org.vaadin.UI.presenter;

import org.vaadin.UI.Notifications.MessageListener;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.Util.Messages;
import org.vaadin.UI.Util.SuccessCallBack;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.NotificationDTO;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.model.models.NotificationModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.InventorySettingView;
import org.vaadin.UI.view.NotificationView;

import javax.security.auth.callback.Callback;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.Util.Messages;
import org.vaadin.UI.Util.SuccessCallBack;
import org.vaadin.UI.model.DTOs.NotificationDTO;
import org.vaadin.UI.model.models.NotificationModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.NotificationView;

import java.util.ArrayList;
import java.util.List;

public class NotificationPresenter implements IPresenter {

    private final NotificationView view;
    private NotificationModel model;


    public NotificationPresenter(NotificationView view) {
        this.view = view;
        this.model = new NotificationModel();
    }

    @Override
    public void onViewLoaded() {
        if(!Credentials.isIsLogedIn()){
            view.setGrid(new ArrayList<>());
            view.showNotification("To see messages, please login first");
            return;
        }
        Messages messages = Messages.getInstance();
        List<NotificationDTO> resultListOfNotifications;
        if(!messages.isLoaded()){
            resultListOfNotifications = model.getListOfNotifications(Credentials.getToken());
            messages.load(resultListOfNotifications);
        }
        resultListOfNotifications = messages.getNotifications();

        if(resultListOfNotifications!= null){
            view.setGrid(resultListOfNotifications);
        }
        else view.showNotification("Failed to fetch notifications");
    }

    @Override
    public void onViewStopped() {
        // Implement any cleanup logic here
    }
    public void clear(SuccessCallBack callback){
        Messages messages = Messages.getInstance();
        List<NotificationDTO> resultListOfNotifications = model.clear(Credentials.getToken(), callback);
        messages.replace(resultListOfNotifications);
        if(resultListOfNotifications!= null){
            view.setGrid(resultListOfNotifications);
        }
        else view.showNotification("Failed to clear notifications");
    }

}
