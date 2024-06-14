package org.vaadin.UI.model.models;

import org.vaadin.UI.Util.DemoDataAPI;
import org.vaadin.UI.model.DTOs.StoreDTO;

import java.util.List;
import java.util.Optional;

public class StoreModel {

    private final boolean useDemoData;

    public StoreModel() {
        this.useDemoData = true; // Change this to false when the real API is implemented
    }

    public List<StoreDTO> getStores() {
        if (useDemoData) {
            return DemoDataAPI.getStores();

        }
        // Real API implementation here
        return List.of();
    }

    public Optional<StoreDTO> getStoreById(String id) {
        if (useDemoData) {
            return Optional.ofNullable(DemoDataAPI.getStoreById(id));
        }
        // Real API implementation here
        return Optional.empty();
    }
}
