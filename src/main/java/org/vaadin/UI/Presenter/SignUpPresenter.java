package org.vaadin.UI.presenter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.ViewInterface.ISignUpView;
import org.springframework.stereotype.Component;


@Component
public class SignUpPresenter implements IPresenter {
    private final ISignUpView view;
    private final RestTemplate restTemplate;

    @Autowired
    public SignUpPresenter(ISignUpView view) {
        this.restTemplate = new RestTemplate();
        this.view = view;
    }

    public void handleSignup()  {
        String username = view.getUsername();
        String password = view.getPassword();
        String age = view.getAge();  // Assuming age is also required for signup

        // Add your signup logic here
        if (!isValidUsername(username) || !isValidPassword(password) || !isValidAge(age)) {
            view.showNotification("Username, password, or age are invalid.");
            return;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create request body using MultiValueMap
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("userName", username);
        requestBody.add("password", password);
        requestBody.add("userAge", String.valueOf(age));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("/api/user/register", request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                view.showNotification("Signup successful for user: " + username);
                // TODO: Move to home page or other actions
            } else if (response.getStatusCode() == HttpStatus.CONFLICT) {
                view.showNotification("Username already exists.");
            } else {
                view.showNotification("Signup failed. Please try again.");
            }
        } catch (Exception e) {
            view.showNotification("Signup request failed. Please try again later.");
        }
    }

    private boolean isValidUsername(String username) {
        // TODO: Implement validation logic for username
        return username != null && username.length() >= 4 && username.length() <= 20 && username.matches("^[a-zA-Z0-9]*$");
    }

    private boolean isValidPassword(String password)  {
        // TODO: Implement validation logic for password
        return password != null && password.length() >= 8;
    }

    private boolean isValidAge(String age)  {
        // TODO: Implement validation logic for age
        try{
            int ageConvert = Integer.parseInt(age);
            if (ageConvert>0 && ageConvert<99)
                return true;
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}