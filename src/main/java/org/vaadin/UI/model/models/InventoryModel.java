
package org.vaadin.UI.model.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.ItemDTO;

import java.util.ArrayList;

public class InventoryModel {
    private final RestTemplate restTemplate;
    public InventoryModel (){
        this.restTemplate = new RestTemplate();
    }
    public ArrayList<ItemDTO> getStoreItems(String storeName){
        //Modifies http request to receive json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("storeName",storeName);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/api/storeManagement/viewInventory", request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {

            } else {


            }
        } catch (Exception e) {

        }
        return null;
    }

    public ArrayList<String> getStores() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://localhost:8080/storeBuyer/getAllStoreInfo",
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                ArrayList<String> storeNames = parseStoreNames(response.getBody());
                return storeNames;
            } else {
                return null; // Handle non-OK status code as needed
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
            return null;
        }
    }
    private ArrayList<String> parseStoreNames(String responseBody) {
        // Implement parsing logic based on the JSON response
        // Example:
        // JSON Array: ["Store A", "Store B", "Store C"]
        // Parse JSON array into ArrayList<String>
        // This is a simplified example; actual parsing logic will depend on your JSON structure
        // Gson or Jackson libraries can be used for parsing JSON
        // Here is a simplified example:
        ArrayList<String> storeNames = new ArrayList<>();
        // Assuming response body is a JSON array of store names
        // Parse JSON array into list of strings
        // Example parsing using Jackson ObjectMapper:
        try {
            ObjectMapper mapper = new ObjectMapper();
            storeNames = mapper.readValue(responseBody, new TypeReference<ArrayList<String>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return storeNames;
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
        requestBody.add("itemPrice=", String.valueOf(itemDTO.getTotalPrice()));
        requestBody.add("stockAmount=", String.valueOf(itemDTO.getQuantity()));
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
        requestBody.add("itemId=", String.valueOf(itemDTO.getItemId()));
        requestBody.add("newName=", String.valueOf(itemDTO.getItemName()));
        requestBody.add("newPrice=", String.valueOf(itemDTO.getTotalPrice()));
        requestBody.add("newAmount=", String.valueOf(itemDTO.getQuantity()));

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
        requestBody.add("itemId=", String.valueOf(itemDTO.getItemId()));

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
}


