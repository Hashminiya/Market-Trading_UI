package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class StoreOwnerSettingModel {
    private final RestTemplate restTemplate;

    public StoreOwnerSettingModel() {
        restTemplate = new RestTemplate();
    }

    public String showStoreOwnerSetting(String token, String storeName, String storeDescription) {
        String url = "http://localhost:8080/storeManagement/createStore?founderToken=" + token + "&storeName=" + storeName + "&storeDescription=" + storeDescription;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<String> viewUserStoresOwnership(String token) {
        String url = "http://localhost:8080/user/viewStoresByNameForUserOwnership?token=" + token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<List<String>> response = restTemplate.exchange(
                    url, HttpMethod.PUT, requestEntity, (Class<List<String>>) (Class<?>) List.class);
            return response.getBody();
//            return List.of(1L, 2L, 3L, 4L);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<String> viewManagementInfo(String token, String storeName) {
        String storeID = "";
        String urlForFetchingID = "http://localhost:8080/storeManagement/getStoreIdByName?token=" + token + "&storeName=" + storeName;
        HttpHeaders headers1 = new HttpHeaders();
        headers1.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity1 = new HttpEntity<>(headers1);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    urlForFetchingID, HttpMethod.GET, requestEntity1, String.class);
            storeID = response.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String url = "http://localhost:8080/storeManagement/viewManagementInfo?token=" + token + "&storeId=" + storeID;

        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity2 = new HttpEntity<>(headers2);

        try {
            ResponseEntity<HashMap<String, List<String>>> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity2, (Class<HashMap<String, List<String>>>) (Class<?>) HashMap.class);
            return response.getBody().keySet();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}