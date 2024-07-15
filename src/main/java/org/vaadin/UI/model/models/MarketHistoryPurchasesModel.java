package org.vaadin.UI.model.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.PurchaseDTO;

import java.util.Arrays;
import java.util.List;

public class MarketHistoryPurchasesModel {

    private final RestTemplate restTemplate;

    public MarketHistoryPurchasesModel() {
        this.restTemplate = new RestTemplate();
    }

    public List<PurchaseDTO> getListOfPurchases(String token) {
        String url = "http://localhost:8080/systemManager/viewMarketPurchaseHistory?token=" + token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String rawJson = response.getBody();
                System.out.println("Raw JSON response: " + rawJson);

                ObjectMapper mapper = new ObjectMapper();
                PurchaseDTO[] purchaseDTOs = mapper.readValue(rawJson, PurchaseDTO[].class);
                return Arrays.asList(purchaseDTOs);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to debug the error
            return null;
        }
    }
}
