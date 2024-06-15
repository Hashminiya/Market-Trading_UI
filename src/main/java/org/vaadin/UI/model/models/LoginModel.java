package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.Util.Credentials;

public class LoginModel {

    private final RestTemplate restTemplate;

    public LoginModel() {
        this.restTemplate = new RestTemplate();
    }

    public String login(String username, String password) {
        String url = "http://localhost:8080/user/login";
        String requestBody = "userName=" + username + "&password=" + password;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Credentials.setToken(response.getBody(), username);
                return "Login successful for user: " + username;
            } else {
                return response.getBody();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
