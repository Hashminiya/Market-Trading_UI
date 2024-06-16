package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class AssignOwnerModel {
    private final RestTemplate restTemplate;

    public AssignOwnerModel() {
        restTemplate = new RestTemplate();
    }

    public String assignOwner(String token, String userName, String storeNumber) {
        String url = "http://localhost:8080/storeManagement/assignStoreOwner?founderToken=" + token + "&storeId=" + storeNumber + "&newOwnerId=" + userName;

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
