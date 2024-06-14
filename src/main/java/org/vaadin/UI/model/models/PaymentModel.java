package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.view.PaymentView;

import java.text.SimpleDateFormat;

public class PaymentModel {

    private final RestTemplate restTemplate;

    public PaymentModel() {
        this.restTemplate = new RestTemplate();
    }

    public String checkoutShoppingCart(String token, String creditCard, String expiryDate, String cvv) {
        String url = "http://localhost:8080/user/checkoutShoppingCart";

        // Create the request body
        String requestBody = "token=" + token + "&creditCard=" + creditCard + "&expiryDate=" + expiryDate + "&cvv=" + cvv ;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create an HttpEntity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // Payment successful
                return "Payment successful.";
            } else {
                // Payment failed
                return "Payment failed: " + response.getBody();
            }
        } catch (Exception e) {
            // Request failed
            return "Couldn't connect to server. Please try again later.";
        }
    }
}
