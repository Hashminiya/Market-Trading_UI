package org.vaadin.UI.model.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreModel {
    private final RestTemplate restTemplate;
    private final boolean useDemoData;

    public StoreModel() {
        this.restTemplate = new RestTemplate();
        this.useDemoData = false;
    }

    public List<StoreDTO> getStores() {
        if (useDemoData) {
            return getDemoStores();
        }

        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/storeBuyer/getAllStores", String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.getBody());
                List<StoreDTO> stores = new ArrayList<>();

                root.fields().forEachRemaining(entry -> {
                    long storeId = Long.parseLong(entry.getKey());
                    JsonNode storeNode = entry.getValue();

                    if (storeNode != null && storeNode.has("storeName") && storeNode.has("storeDescription") && storeNode.has("items")) {
                        String storeName = storeNode.get("storeName").asText();
                        String storeDescription = storeNode.get("storeDescription").asText();

                        List<ItemDTO> items = new ArrayList<>();
                        storeNode.get("items").forEach(itemNode -> {
                            if (itemNode != null && itemNode.has("itemId") && itemNode.has("itemName") && itemNode.has("stockAmount") && itemNode.has("itemPrice") && itemNode.has("category") && itemNode.has("description")) {
                                long itemId = itemNode.get("itemId").asLong();
                                String itemName = itemNode.get("itemName").asText();
                                int stockAmount = itemNode.get("stockAmount").asInt();
                                double itemPrice = itemNode.get("itemPrice").asDouble();
                                List<String> categories = new ArrayList<>();
                                itemNode.get("category").forEach(categoryNode -> categories.add(categoryNode.asText()));
                                String description = itemNode.get("description").asText();

                                items.add(new ItemDTO(itemId, itemName, stockAmount, storeId, itemPrice, categories, description));
                            }
                        });

                        stores.add(new StoreDTO(storeId, storeName, storeDescription, items));
                    }
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

    public List<ItemDTO> searchItemsByKeyWord(String keyword) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/storeBuyer/searchGenerallyByKeyWord?keyWord=" + keyword, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.getBody(), new TypeReference<List<ItemDTO>>() {});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<ItemDTO> searchItemsByKeyWordAndCategory(String keyword, String category) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/storeBuyer/searchGenerallyByKeyWordAndCategory?keyWord=" + keyword + "&category=" + category, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.getBody(), new TypeReference<List<ItemDTO>>() {});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<StoreDTO> getDemoStores() {
        return new ArrayList<>();
    }

    private Optional<StoreDTO> getDemoStoreById(long id) {
        return Optional.empty();
    }
}
