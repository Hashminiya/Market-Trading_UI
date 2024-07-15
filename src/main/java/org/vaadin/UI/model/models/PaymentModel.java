package org.vaadin.UI.model.models;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class PaymentModel {

    private final RestTemplate restTemplate;

    public PaymentModel() {
        this.restTemplate = new RestTemplate();
    }

    public String checkoutShoppingCart(String token, String creditCard, String expiryDate, String cvv, String discountCode) {
        String url = "http://localhost:8080/user/checkoutShoppingCart";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("token", token);
        requestBody.add("creditCard", creditCard);
        requestBody.add("expiryDate", expiryDate);
        requestBody.add("cvv", cvv);
        requestBody.add("discountCode", discountCode);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "Payment successful.";
            } else {
                return "Payment failed: " + response.getBody();
            }
        } catch (Exception e) {
            return "Couldn't connect to server. Please try again later.";
        }
    }

    public double getCartTotalPrice(String token) {
        String url = "http://localhost:8080/user/getShoppingCartTotalPrice?token=" + token;
        try {
            ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
            return response.getBody();
        } catch (Exception e) {
            return 0;
        }
    }
}
