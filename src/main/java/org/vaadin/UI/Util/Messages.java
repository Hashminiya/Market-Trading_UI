package org.vaadin.UI.Util;

import org.springframework.stereotype.Component;
import org.vaadin.UI.Notifications.MessageListener;
import org.vaadin.UI.model.DTOs.NotificationDTO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

@Component
public class Messages implements MessageListener {
    private static Messages instance;
    private PropertyChangeSupport support;
    private boolean isLoaded;
    private boolean newMessage;
    private List<NotificationDTO> notifications;

    private Messages() {
        this.notifications = new ArrayList<>();
        isLoaded = false;
        support = new PropertyChangeSupport(this);
    }

    public static Messages getInstance() {
        if (instance == null) {
            instance = new Messages();
        }
        return instance;
    }

    public List<NotificationDTO> getNotifications() {
        return notifications;
    }

    public void addNotification(NotificationDTO notificationDTO) {
        notifications.add(notificationDTO);
    }

    public void load(List<NotificationDTO> messages) {
        this.notifications.addAll(messages);
        isLoaded = true;
        newMessage = !this.notifications.isEmpty();
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public boolean isNewMessage() {
        return newMessage;
    }

    public void setNewMessage(boolean newMessage) {
        boolean oldValue = this.newMessage;
        this.newMessage = newMessage;
        support.firePropertyChange("newMessage", oldValue, newMessage);
    }

    @Override
    public void onMessageReceived(String message) {
        notifications.add(new NotificationDTO(message));
        setNewMessage(true);
    }

    public void seen() {
        setNewMessage(false);
    }

    public void replace(List<NotificationDTO> resultListOfNotifications) {
        notifications = resultListOfNotifications;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

}
