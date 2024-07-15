package org.vaadin.UI.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationDTO {
    @JsonProperty("content")
    private String content;

    public NotificationDTO() {
    }

    public NotificationDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    ;
}
