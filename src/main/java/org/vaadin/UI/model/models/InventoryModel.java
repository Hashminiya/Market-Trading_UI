
package org.vaadin.UI.model.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
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
    }
    public ArrayList<ItemDTO> getStoreItems(String storeName, String token) {
        try {
            String url = "http://localhost:8080/storeManagement/viewInventoryByStoreNameAndToken?token=" + token + "&storeName=" + storeName;

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // Print response (for debugging)
                for (int i = 0; i < 100; i++) {
                    System.out.println();
                }
                System.out.println(response.getBody());

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

                    items.add(new ItemDTO(itemId, itemName, stockAmount, storeId, itemPrice, new ArrayList<String>(), "desc"));//TODO: add categorys and description
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

    public String saveItem(ItemDTO itemDTO,String token) {
        //Modifies http request to receive json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("storeId=", String.valueOf(itemDTO.getStoreId()));
        requestBody.add("itemName=", String.valueOf(itemDTO.getItemName()));
        requestBody.add("description=", "");
        requestBody.add("itemPrice=", String.valueOf(itemDTO.getItemPrice()));
        requestBody.add("stockAmount=", String.valueOf(itemDTO.getItemQuantity()));
        requestBody.add("categories=","");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/storeManagement/addItemToStore?token="+token, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return "Item added successfully";

            } else {
                return "Item addition - Failed";

            }
        } catch (Exception e) {
            return "Item addition - Failed";
        }
    }

    public String updateItem(ItemDTO itemDTO,String token) {
        //Modifies http request to receive json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("storeId=", String.valueOf(itemDTO.getStoreId()));
        requestBody.add("itemId=", String.valueOf(itemDTO.getId()));
        requestBody.add("newName=", String.valueOf(itemDTO.getItemName()));
        requestBody.add("newPrice=", String.valueOf(itemDTO.getItemPrice()));
        requestBody.add("newAmount=", String.valueOf(itemDTO.getItemQuantity()));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/storeManagement/updateItem?token="+token, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return "Item update successfully";

            } else {
                return "Item update - Failed";

            }
        } catch (Exception e) {
            return "Item update - Failed";
        }
    }

    public String deleteItem(ItemDTO itemDTO, String token) {
        //Modifies http request to receive json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("storeId=", String.valueOf(itemDTO.getStoreId()));
        requestBody.add("itemId=", String.valueOf(itemDTO.getId()));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/storeManagement/deleteItem?token="+token, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return "Item update successfully";

            } else {
                return "Item update - Failed";

            }
        } catch (Exception e) {
            return "Item update - Failed";
        }
    }

    public List<String> getStoreCategories(String storeName, String token) {
        String url = "http://localhost:8080/storeManagement/viewCategoriesByStoreNameAndToken?token=" + token + "&storeName=" + storeName;

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
}


