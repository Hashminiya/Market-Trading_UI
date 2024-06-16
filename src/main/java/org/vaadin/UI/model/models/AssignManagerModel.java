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

    public String assignManager(String token, String userName, String storeNumber, Set<String> permissions) {
        String url = "http://localhost:8080/storeManagement/assignStoreManager";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        params.add("storeId", storeNumber);
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
