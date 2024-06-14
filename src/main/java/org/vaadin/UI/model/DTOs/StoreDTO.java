package org.vaadin.UI.model.DTOs;
import java.io.Serializable;
import java.util.List;

public class StoreDTO implements Serializable {
    private String name;
    private String description;
    private List<ItemDTO> items;

    public StoreDTO(String name, String description, List<ItemDTO> items) {
        this.name = name;
        this.description = description;
        this.items = items;
    }

    // Getters and setters
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