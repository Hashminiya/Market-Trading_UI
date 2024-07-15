package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AssignManagerModel {
    private final RestTemplate restTemplate;

    public AssignManagerModel() {
        restTemplate = new RestTemplate();
    }

    public String assignManager(String token, String userName, String storeName, Set<String> permissions) {
        String storeID = "";
        String urlForFetchingID = "http://localhost:8080/storeManagement/getStoreIdByName?token=" + token + "&storeName=" + storeName;
        HttpHeaders headers1 = new HttpHeaders();
        headers1.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity1 = new HttpEntity<>(headers1);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    urlForFetchingID, HttpMethod.GET, requestEntity1, String.class);
            storeID = response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String url = "http://localhost:8080/storeManagement/assignStoreManager";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        params.add("storeId", storeID);
        params.add("newManagerI", userName);

        // Convert set of permissions to list
        List<String> permissionsList = new ArrayList<>(permissions);
        params.put("permissions", permissionsList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
