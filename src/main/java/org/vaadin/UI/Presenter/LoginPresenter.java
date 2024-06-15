package org.vaadin.UI.Presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.UserDTO;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.ViewInterface.ILoginView;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class LoginPresenter implements IPresenter {
    private final ILoginView view;
    private final RestTemplate restTemplate;

    @Autowired
    public LoginPresenter(ILoginView view) {
        this.restTemplate = new RestTemplate();
        this.view = view;
    }

    public void onLogin() {

        // Define the URL
        String url = "http://localhost:8080/user/login";

        // Define the query parameters
        String username = view.getUsername();
        String password = view.getPassword();

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
                view.showNotification("Login successful for user: " + username);
            } else {
                // Login failed
                view.showNotification("Login failed. User is already exists.");
            }
        } catch (Exception e) {
            // Request failed
            view.showNotification("Couldn't connect to server. Please try again later.");
        }
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}