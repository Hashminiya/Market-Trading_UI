package org.vaadin.UI.model.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StoreModel {
    private final RestTemplate restTemplate;
    private final boolean useDemoData;

    public StoreModel() {
        this.restTemplate = new RestTemplate();
        this.useDemoData = false; // Change this to false when the real API is implemented
    }

    public List<StoreDTO> getStores() {
        if (useDemoData) {
            return getDemoStores();
        }

        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/storeBuyer/getAllStores", String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                // Print response
                for (int i = 0; i < 100; i++) {
                    System.out.println();
                }
                System.out.println(response.getBody());

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.getBody());
                List<StoreDTO> stores = new ArrayList<>();

                root.fields().forEachRemaining(entry -> {
                    long storeId = Long.parseLong(entry.getKey());
                    JsonNode storeNode = entry.getValue();
                    String storeName = storeNode.get("storeName").asText();
                    String storeDescription = storeNode.get("storeDescription").asText();

                    List<ItemDTO> items = new ArrayList<>();
                    storeNode.get("items").forEach(itemNode -> {
                        long itemId = itemNode.get("itemId").asLong();
                        String itemName = itemNode.get("itemName").asText();
                        int stockAmount = itemNode.get("stockAmount").asInt();
                        double itemPrice = itemNode.get("itemPrice").asDouble();
                        items.add(new ItemDTO(itemId, itemName, stockAmount, storeId, itemPrice));
                    });

                    stores.add(new StoreDTO(storeId, storeName, storeDescription, items));
                });

                return stores;
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Optional<StoreDTO> getStoreById(long id) {
        if (useDemoData) {
            return getDemoStoreById(id);
        }

        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/storeBuyer/getAllProductsInfoByStore?storeId=" + id, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                StoreDTO store = objectMapper.readValue(response.getBody(), StoreDTO.class);
                return Optional.of(store);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // Demo data methods
    private List<StoreDTO> getDemoStores() {
        // Your demo data implementation
        return new ArrayList<>();
    }

    private Optional<StoreDTO> getDemoStoreById(long id) {
        // Your demo data implementation
        return Optional.empty();
    }
}
