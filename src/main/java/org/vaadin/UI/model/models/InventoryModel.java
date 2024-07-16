package org.vaadin.UI.model.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.ItemDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryModel {
    private final RestTemplate restTemplate;

    public InventoryModel() {
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
                    String description = itemNode.has("description") ? itemNode.get("description").asText() : "";
                    long storeId = itemNode.has("storeId") ? itemNode.get("storeId").asLong() : 0L;

                    List<String> categories = new ArrayList<>();
                    if (itemNode.has("category")) {
                        itemNode.get("category").forEach(categoryNode -> categories.add(categoryNode.asText()));
                    }

                    items.add(new ItemDTO(itemId, itemName, stockAmount, storeId, itemPrice, categories, description));
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

    public String saveItem(String token, long storeId, String name, String description, double price, int amount, List<String> categories) {
        try {
            String url = "http://localhost:8080/storeManagement/addItemToStore?"
                    + "token=" + token
                    + "&storeId=" + storeId
                    + "&itemName=" + name
                    + "&description=" + description
                    + "&itemPrice=" + price
                    + "&stockAmount=" + amount
                    + "&categories=" + String.join(",", categories);

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> request = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

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
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            String url = "http://localhost:8080/storeManagement/updateItem?"
                    + "token=" + token
                    + "&storeId=" + storeId
                    + "&itemId=" + itemId
                    + "&newName=" + newName
                    + "&newPrice=" + newPrice
                    + "&newAmount=" + newAmount;

            HttpEntity<String> request = new HttpEntity<>(headers);

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
        headers.setContentType(MediaType.APPLICATION_JSON);
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


    //    @GetMapping("storeManagement/getStoreIdByName")
    //    public ResponseEntity<String> getStoreIdByName(@RequestParam String token,@RequestParam String storeName) {
    //        ResponseEntity<String> result = storeManagementService.getStoreIdByName(token, storeName);
    //        return result;
    //    }
    public long getStoreIdByName(String storeName, String token) {
        String url = "http://localhost:8080/storeManagement/getStoreIdByName?token=" + token + "&storeName=" + storeName;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, String.class);
            return Long.parseLong(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;

        }
    }
}
