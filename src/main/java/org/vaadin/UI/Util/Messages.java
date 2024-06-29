package org.vaadin.UI.Util;

import org.springframework.context.ApplicationEventPublisher;
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
    private boolean isLoaded;
    private boolean newMessage;
    private List<NotificationDTO> notifications;
    private Messages(){
        this.notifications = new ArrayList<>();
        isLoaded = false;
    }

    public static Messages getInstance(){
        if(instance == null){
            instance = new Messages();
        }
        return instance;
    }
    public List<NotificationDTO> getNotifications() {
        return notifications;
    }
    public void addNotification(NotificationDTO notificationDTO){
        notifications.add(notificationDTO);
    }
    public void load(List<NotificationDTO> messages){
        this.notifications.addAll(messages);
        isLoaded = true;
        newMessage = !this.notifications.isEmpty();
    }
    public boolean isLoaded(){
        return isLoaded;
    }

    public boolean isNewMessage() {
        return newMessage;
    }

    @Override
    public void onMessageReceived(String message) {
        notifications.add(new NotificationDTO(message));
        newMessage = true;
    }

    public void seen() {
        newMessage = false;
    }

    public void replace(List<NotificationDTO> resultListOfNotifications) {
        notifications = resultListOfNotifications;
    }
}
