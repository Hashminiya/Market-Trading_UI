package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    public List<Long> viewUserStoresOwnership(String token) {
        String url = "http://localhost:8080/user/viewUserStoresOwnership?token=" + token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
//            ResponseEntity<List<Long>> response = restTemplate.exchange(
//                    url, HttpMethod.PUT, requestEntity, (Class<List<Long>>) (Class<?>) List.class);
//            return response.getBody();
            return List.of(1L, 2L, 3L, 4L);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<String> viewManagementInfo(String token, long storeId) {
        String url = "http://localhost:8080/storeManagement/viewManagementInfo?token=" + token + "&storeId=" + storeId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
//            ResponseEntity<HashMap<String, List<String>>> response = restTemplate.exchange(
//                    url, HttpMethod.GET, requestEntity, (Class<HashMap<String, List<String>>>) (Class<?>) HashMap.class);
//            return response.getBody().keySet();
            HashMap<String, List<String>> userPermissions = new HashMap<>();

            String user1 = "User1";
            String user2 = "User2";
            String user3 = "User3";

            List<String> permissions1 = new ArrayList<>();
            permissions1.add("Read");
            permissions1.add("Write");

            List<String> permissions2 = new ArrayList<>();
            permissions2.add("Read");

            List<String> permissions3 = new ArrayList<>();
            permissions3.add("Write");

            userPermissions.put(user1, permissions1);
            userPermissions.put(user2, permissions2);
            userPermissions.put(user3, permissions3);
            return userPermissions.keySet();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}