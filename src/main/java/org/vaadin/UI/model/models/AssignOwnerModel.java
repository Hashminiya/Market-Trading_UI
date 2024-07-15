package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class AssignOwnerModel {
    private final RestTemplate restTemplate;

    public AssignOwnerModel() {
        restTemplate = new RestTemplate();
    }

    public String assignOwner(String token, String userName, String storeName) {
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


        String url = "http://localhost:8080/storeManagement/assignStoreOwner?founderToken=" + token + "&storeId=" + storeID + "&newOwnerId=" + userName;

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
