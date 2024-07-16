package org.vaadin.UI.model.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.Discounts.DiscountDTO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DiscountModel {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(DiscountModel.class);

    public DiscountModel() {
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

    public String savePolicy(String token, String currentStoreName, String json) {
        return null;
    }

    public String saveHiddenDiscount(String token, long storeId, String discountDetails) {
        try {
            // Encode the discountDetails parameter
            String encodedDiscountDetails = URLEncoder.encode(discountDetails, StandardCharsets.UTF_8.toString());

            String url = "http://localhost:8080/storeManagement/addDiscount?"
                    + "token=" + token
                    + "&storeId=" + storeId
                    + "&discountDetails=" + encodedDiscountDetails;

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> request = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return "Discount added successfully";
            } else {
                return "Discount addition - Failed";
            }
        } catch (UnsupportedEncodingException e) {
            return "Discount added successfully";
        } catch (Exception e) {
            return "Discount added successfully";
        }
    }
}
