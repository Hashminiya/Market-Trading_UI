package org.vaadin.UI.model.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class InventoryModel {
    private final RestTemplate restTemplate;
    public InventoryModel (){
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }
    public ArrayList<ItemDTO> getStoreItems(String storeName, String token) {
        try {
            String url = "http://localhost:8080/storeManagement/viewInventoryByStoreNameAndToken?token=" + token + "&storeName=" + storeName;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.getBody());
                ArrayList<ItemDTO> items = new ArrayList<>();

                root.forEach(itemNode -> {
                    long itemId = itemNode.get("itemId").asLong();
                    String itemName = itemNode.get("itemName").asText();
                    double itemPrice = itemNode.get("itemPrice").asDouble();
                    int stockAmount = itemNode.get("stockAmount").asInt();
                    //String category = itemNode.get("category").asText();
                    long storeId = itemNode.has("storeId") ? itemNode.get("storeId").asLong() : 0L;

                    items.add(new ItemDTO(itemId, itemName, stockAmount, storeId, itemPrice, new ArrayList<>(), "desc"));
                });

                return items;
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<String> getStores(String token) {
        String url = "http://localhost:8080/user/viewStoresByNameForUserOwnership?token=" + token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<List<String>> response = restTemplate.exchange(
                    url, HttpMethod.PUT, requestEntity, (Class<List<String>>) (Class<?>) List.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String saveItem(ItemDTO itemDTO, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonItem = objectMapper.writeValueAsString(itemDTO);
            HttpEntity<String> request = new HttpEntity<>(jsonItem, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://localhost:8080/storeManagement/addItemToStore?token=" + token,
                    HttpMethod.PUT, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return "Item added successfully";
            } else {
                return "Item addition - Failed";
            }
        } catch (Exception e) {
            return "Item addition - Failed: " + e.getMessage();
        }
    }

    public String updateItem(long itemId, long storeId, String newName, double newPrice, int newAmount, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        try {
            MultiValueMap<String, String> payload = new LinkedMultiValueMap<>();
            payload.add("storeId", String.valueOf(storeId));
            payload.add("itemId", String.valueOf(itemId));
            payload.add("newName", newName);
            payload.add("newPrice", String.valueOf(newPrice));
            payload.add("newAmount", String.valueOf(newAmount));

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(payload, headers);
            String url = "http://localhost:8080/storeManagement/updateItem?token=" + token;

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PATCH, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return "Item updated successfully";
            } else {
                return "Item update - Failed";
            }
        } catch (Exception e) {
            return "Item update - Failed: " + e.getMessage();
        }
    }


    public String deleteItem(ItemDTO itemDTO, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String url = "http://localhost:8080/storeManagement/deleteItem?token=" + token + "&storeId=" + itemDTO.getStoreId() + "&itemId=" + itemDTO.getItemId();

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return "Item deleted successfully";
            } else {
                return "Item deletion - Failed";
            }
        } catch (Exception e) {
            return "Item deletion - Failed";
        }
    }

    public List<String> getStoreCategories(String storeName, String token) {
        String url = "http://localhost:8080/storeManagement/viewCategoriesByStoreNameAndToken?token=" + token + "&storeName=" + storeName;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<List<String>> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, (Class<List<String>>) (Class<?>) List.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
