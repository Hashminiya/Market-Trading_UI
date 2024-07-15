package org.vaadin.UI.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.ViewInterface.ISignUpView;

import java.util.regex.Pattern;


@Component
public class SignUpPresenter implements IPresenter {
    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final int MAX_PASSWORD_LENGTH = 20;
    private final ISignUpView view;
    private final RestTemplate restTemplate;

    @Autowired
    public SignUpPresenter(ISignUpView view) {
        this.restTemplate = new RestTemplate();
        this.view = view;
    }

    public void onSignup() {
        String username = view.getUsername();
        String password = view.getPassword();
        String age = view.getAge();

        if (!isValidPassword(password) || !isValidAge(age)) {
            view.showNotification("Password or age are invalid.");
            return;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("userName", username);
        requestBody.add("password", password);
        requestBody.add("userAge", String.valueOf(age));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/user/register", request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                view.showNotification("Signup successful for user: " + username);
                view.navigateToLoginView(username); // Navigate to login view with username
            } else if (response.getStatusCode() == HttpStatus.CONFLICT) {
                view.showNotification("Username already exists.");
            } else {
                view.showNotification("Signup failed. Please try again.");
            }
        } catch (Exception e) {
            view.showNotification("Signup request failed. Please try again later.");
        }
    }

    private boolean isValidAge(String age) {
        try {
            int ageConvert = Integer.parseInt(age);
            if (ageConvert > 0 && ageConvert < 120)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

//    private boolean isValidPassword(String password) {
//        // Check if password is not null and meets length requirement
//        return password != null && password.length() >= MIN_PASSWORD_LENGTH;
//    }

    private boolean isValidPassword(String password) {
        // Check if username is not null and meets length requirements
        if (password == null || password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            return false;
        }
        // Check if username contains only alphanumeric characters (letters and digits)
        return Pattern.matches("^[a-zA-Z0-9]*$", password);
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}