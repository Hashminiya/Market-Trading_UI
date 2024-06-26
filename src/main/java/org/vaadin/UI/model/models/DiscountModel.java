package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.DiscountDTO;

import java.util.List;

public class DiscountModel {

    private final RestTemplate restTemplate;

    public DiscountModel (){
        this.restTemplate = new RestTemplate();
    }
    public List<DiscountDTO> getDiscounts(String storeName, String token) {
        return null;
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
}
