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

        // Define the URL
        String url = "http://localhost:8080/user/login";


        // Create the request body as a query string
        String requestBody = "userName=" + username + "&password=" + password;

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create an HttpEntity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // Login successful
                Credentials.setToken(response.getBody(),this);
                return "Login successful for user: " + username;
            } else {
                // Login failed
                return response.getBody();
            }
        } catch (Exception e) {
            // Request failed
            return e.getMessage();
        }
    }
}
