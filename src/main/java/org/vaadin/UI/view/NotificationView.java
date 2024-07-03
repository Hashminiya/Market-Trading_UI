package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.NotificationDTO;
import org.vaadin.UI.presenter.NotificationPresenter;

import java.util.List;

@Route("notification")
public class NotificationView extends ViewTemplate {
    private NotificationPresenter notificationPresenter;
    private Grid<NotificationDTO> grid;

    public NotificationView() {
        notificationPresenter = new NotificationPresenter(this);
        VerticalLayout restOfPage = new VerticalLayout();
        restOfPage.add(createGridWithClearButton());
        add(restOfPage);
    }

    private HorizontalLayout createGridWithClearButton() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();

        grid = new Grid<>(NotificationDTO.class);
        grid.setColumns("content");
        grid.setWidthFull();

        Button clearButton = new Button("Clear All Messages");
        clearButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        clearButton.addClickListener(e -> notificationPresenter.clear(this::clearAllMessages));

        layout.add(grid, clearButton);
        layout.setFlexGrow(1, grid);
        layout.setAlignItems(Alignment.START);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        notificationPresenter.onViewLoaded();

        return layout;
    }

    private void clearAllMessages() {
        getUI().ifPresent(ui -> ui.navigate("notification"));
    }

    public void setGrid(List<NotificationDTO> listOfNotifications) {
        grid.setItems(listOfNotifications);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }
}