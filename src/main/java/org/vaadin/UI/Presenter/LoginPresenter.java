package org.vaadin.UI.Presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.UserDTO;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.ViewInterface.ILoginView;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class LoginPresenter implements IPresenter {
    private final ILoginView view;
    private final RestTemplate restTemplate;

    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MAX_USERNAME_LENGTH = 20;
    private static final int MIN_PASSWORD_LENGTH = 8;

    @Autowired
    public LoginPresenter(ILoginView view) {
        this.restTemplate = new RestTemplate();
        this.view = view;
    }

    public void handleLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        // Add your login logic here
        if (!isValidUsername(username) || !isValidPassword(password)) {
            view.showNotification("Username or password are invalid.");
            return;
        }

        //Modifies http request to receive json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("userName", username);
        requestBody.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("/api/user/login", request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // Login successful
                view.showNotification("Login successful for user: " + username);
                // TODO: Move to home page or perform other actions
            } else {
                // Login failed
                view.showNotification("Login failed. User is already exists.");
            }
        } catch (Exception e) {
            // Request failed
            view.showNotification("Couldn't connect to server. Please try again later.");
        }
    }

    private boolean isValidPassword(String password) {
        // Check if password is not null and meets length requirement
        //TODO:: ADD MORE VALIDATIONS IF NEEDED
        return password != null && password.length() >= MIN_PASSWORD_LENGTH;
    }

    private boolean isValidUsername(String username) {
        // Check if username is not null and meets length requirements
        //TODO:: ADD MORE VALIDATIONS IF NEEDED
        if (username == null || username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH) {
            return false;
        }

        // Check if username contains only alphanumeric characters (letters and digits)
        return Pattern.matches("^[a-zA-Z0-9]*$", username);
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
