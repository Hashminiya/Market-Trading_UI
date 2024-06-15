package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.CartItemDTO;

import java.util.Arrays;
import java.util.List;

public class CartModel {

    private final RestTemplate restTemplate;

    public CartModel() {
        this.restTemplate = new RestTemplate();
    }

    public List<CartItemDTO> getCartItems(String token) {
        String url = "http://localhost:8080/user/viewShoppingCart?token=" + token;
        ResponseEntity<CartItemDTO[]> response = restTemplate.getForEntity(url, CartItemDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public String addItemToCart(String token, long storeId, long itemId, int quantity) {
        String url = "http://localhost:8080/user/addItemToBasket";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String requestBody = "token=" + token + "&storeId=" + storeId + "&itemId=" + itemId + "&quantity=" + quantity;
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return response.getBody();
    }

    public String updateCartItem(String token, long basketId, long itemId, int newQuantity) {
        String url = "http://localhost:8080/user/modifyShoppingCart";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String requestBody = "token=" + token + "&basketId=" + basketId + "&itemId=" + itemId + "&newQuantity=" + newQuantity;
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, String.class);
        return response.getBody();
    }

    public String checkout(String token, String creditCard, String expiryDate, String cvv, String discountCode) {
        String url = "http://localhost:8080/user/checkoutShoppingCart";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String requestBody = "token=" + token + "&creditCard=" + creditCard + "&expiryDate=" + expiryDate + "&cvv=" + cvv + "&discountCode=" + discountCode;
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        return response.getBody();
    }
}