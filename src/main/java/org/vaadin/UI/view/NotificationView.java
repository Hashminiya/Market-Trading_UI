package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.NotificationDTO;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.presenter.CreateStorePresenter;
import org.vaadin.UI.presenter.NotificationPresenter;

import java.util.List;

@Route("notification")
public class NotificationView extends ViewTemplate {

    private NotificationPresenter notificationPresenter;
    private Grid<NotificationDTO> grid;

    public NotificationView() {
        notificationPresenter = new NotificationPresenter(this);
        VerticalLayout restOfPage = new VerticalLayout();
        restOfPage.add(initGrid());
        add(restOfPage);
    }
    private Grid initGrid() {
        grid = new Grid<>(NotificationDTO.class);
        grid.setColumns("content");
        notificationPresenter.onViewLoaded();
        return grid;
    }
    public void setGrid(List<NotificationDTO> listOfNotifications) {
        grid.setItems(listOfNotifications);
    }
    public void showNotification(String message) {
        Notification.show(message);
    }
}
