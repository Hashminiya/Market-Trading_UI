package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class CreateStoreModel {

    private final RestTemplate restTemplate;

    public CreateStoreModel() {
        restTemplate = new RestTemplate();
    }

    public String createStore(String token, String storeName, String storeDescription) {
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
}
