package org.vaadin.UI.Util;

import org.vaadin.UI.model.DTOs.NotificationDTO;

import java.util.ArrayList;
import java.util.List;

public class Messages {
    private static Messages instance;
    private boolean isLoaded;
    private ArrayList<NotificationDTO> notifications;
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

    public ArrayList<NotificationDTO> getNotifications() {
        return notifications;
    }
    public void addNotification(NotificationDTO notificationDTO){
        notifications.add(notificationDTO);
    }
    public void load(List<NotificationDTO> messages){
        this.notifications.addAll(messages);
        isLoaded = true;
    }
    public boolean isLoaded(){
        return isLoaded;
    }

}
