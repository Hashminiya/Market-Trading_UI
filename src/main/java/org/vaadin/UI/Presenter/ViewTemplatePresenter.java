package org.vaadin.UI.Presenter;
import org.vaadin.UI.Notifications.MessageListener;
import org.vaadin.UI.Notifications.WebSocketHandler;
import org.vaadin.UI.Util.Messages;
import org.vaadin.UI.model.DTOs.NotificationDTO;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.ViewTemplate;

public class ViewTemplatePresenter implements IPresenter {

    private final ViewTemplate view;

    public ViewTemplatePresenter(ViewTemplate view) {
        this.view = view;
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
