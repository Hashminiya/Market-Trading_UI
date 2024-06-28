package org.vaadin.UI.presenter;

import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.NotificationDTO;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.model.models.NotificationModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.InventorySettingView;
import org.vaadin.UI.view.NotificationView;

import java.util.ArrayList;
import java.util.List;

public class NotificationPresenter implements IPresenter {

    private final NotificationView view;
    private NotificationModel model;
    private ArrayList<NotificationDTO> notifications;

    public NotificationPresenter(NotificationView view) {
        this.view = view;
        this.model = new NotificationModel();
    }

    @Override
    public void onViewLoaded() {
        //List<NotificationDTO> resultListOfNotifications = model.getListOfNotifications(Credentials.getToken());
        List<NotificationDTO> resultListOfNotifications = model.getDemoNotifications(Credentials.getToken());

        if(resultListOfNotifications!= null){
            view.setGrid(resultListOfNotifications);
        }
        else view.showNotification("Failed to fetch purchases history");
    }

    @Override
    public void onViewStopped() {
        // Implement any cleanup logic here
    }
}
