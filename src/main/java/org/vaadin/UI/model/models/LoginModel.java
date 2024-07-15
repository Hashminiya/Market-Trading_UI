package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.Util.SuccessCallBack;

public class LoginModel {

    private final RestTemplate restTemplate;

    public LoginModel() {
        this.restTemplate = new RestTemplate();
    }


    public static String guestEntry() {
        // Define the URL
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/user/GuestEntry", null, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Credentials.setGuestToken(response.getBody(), "guest");
                return "Guest entry successful";
            } else {
                return response.getBody();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String login(String username, String password, SuccessCallBack callBack) {

        // Define the URL
        String url = "http://localhost:8080/user/login";
        String requestBody = "userName=" + username + "&password=" + password;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                // Login successful
                Credentials.setToken(response.getBody(), username);
                callBack.call();
                return "Login successful for user: " + username;
            } else {
                return response.getBody();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String logout(String token, SuccessCallBack callBack) {

        // Define the URL
        String url = "http://localhost:8080/user/logout";
        String requestBody = "token=" + token;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Credentials.logOut();
                callBack.call();
                return "Logout successful";
            } else {
                return response.getBody();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
