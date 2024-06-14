package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.PurchaseDTO;

import java.util.Arrays;
import java.util.List;

public class MarketHistoryPurchasesModel {

    private final RestTemplate restTemplate;
    public MarketHistoryPurchasesModel (){
        this.restTemplate = new RestTemplate();
    }

    public List<PurchaseDTO> getListOfPurchases(String token) {
        String url = "http://localhost:8080/systemManager/viewMarketPurchaseHistory?token=" + token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create an HttpEntity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<PurchaseDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, PurchaseDTO[].class);

            if (response.getStatusCode() == HttpStatus.OK) {
                List<PurchaseDTO> listOfPurchases = Arrays.asList(response.getBody());
                return listOfPurchases;
            }
            else {
                return null;
            }
        } catch (Exception e) {
                return null;
        }
    }
}
