package org.vaadin.UI.model.DTOs;
import java.io.Serializable;
import java.util.List;

public class StoreDTO implements Serializable {
    private String name;
    private String description;
    private List<ProductDTO> products;

    public StoreDTO(String name, String description, List<ProductDTO> products) {
        this.name = name;
        this.description = description;
        this.products = products;
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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

}
