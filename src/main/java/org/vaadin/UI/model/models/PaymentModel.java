package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PaymentModel {

    private final RestTemplate restTemplate;

    public PaymentModel() {
        this.restTemplate = new RestTemplate();
    }

    /*
     @PostMapping("/user/checkoutShoppingCart")
    public ResponseEntity<String> checkoutShoppingCart(@RequestParam String token,@RequestParam String creditCard,@RequestParam Date expiryDate,@RequestParam String cvv,@RequestParam String discountCode) {
        return userService.checkoutShoppingCart(token, creditCard, expiryDate, cvv, discountCode);
    }
     */
    public String checkoutShoppingCart(String token) {
        String url = "http://localhost:8080/user/checkoutShoppingCart";
        String creditCard = "1111111111111111";
        String cvv = "111";
        String discountCode = "DISCOUNT10";
        String expiryDate = "07/25";  // Adjust the expiry date format as needed

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
