package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.Util.SuccessCallBack;
import org.vaadin.UI.model.DTOs.NotificationDTO;
import org.vaadin.UI.model.DTOs.PurchaseDTO;

import javax.security.auth.callback.Callback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationModel {

    private final RestTemplate restTemplate;
    public NotificationModel(){
        this.restTemplate = new RestTemplate();
    }

    public List<NotificationDTO> getListOfNotifications(String token) {
        String url = "http://localhost:8080/user/viewMessages?token=" + token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create an HttpEntity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<NotificationDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, NotificationDTO[].class);

            if (response.getStatusCode() == HttpStatus.OK) {
                List<NotificationDTO> listOfNotifications = Arrays.asList(response.getBody());
                return listOfNotifications;
            }
            else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<NotificationDTO> clear(String token, SuccessCallBack callback) {
        String url = "http://localhost:8080/user/viewMessages/clear?token=" + token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create an HttpEntity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<NotificationDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, NotificationDTO[].class);

            if (response.getStatusCode() == HttpStatus.OK) {
                List<NotificationDTO> listOfNotifications = Arrays.asList(response.getBody());
                callback.call();
                return listOfNotifications;
            }
            else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
