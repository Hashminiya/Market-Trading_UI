package org.vaadin.UI.model.DTOs;

import java.io.Serializable;
import java.util.List;

public class StoreDTO implements Serializable {
    private long id;
    private String name;
    private String description;
    private List<ItemDTO> items;

    public StoreDTO(long id, String name, String description, List<ItemDTO> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = items;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
