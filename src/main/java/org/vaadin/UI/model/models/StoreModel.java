package org.vaadin.UI.model.models;

import org.vaadin.UI.Util.DemoDataAPI;
import org.vaadin.UI.model.DTOs.StoreDTO;

import java.util.List;
import java.util.Optional;

public class StoreModel {

    private final boolean useDemoData;

    public StoreModel() {
        this.useDemoData = true; // Change this to false when the real API is implemented
    }

    public List<StoreDTO> getStores() {
        if (useDemoData) {
            return DemoDataAPI.getStores();

        }
        // Real API implementation here
        return List.of();
    }

    public Optional<StoreDTO> getStoreById(String id) {
        if (useDemoData) {
            return Optional.ofNullable(DemoDataAPI.getStoreById(id));
        }
        // Real API implementation here
        return Optional.empty();
    }
}
/*
package org.vaadin.UI.model.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.Util.DemoDataAPI;
import org.vaadin.UI.model.DTOs.StoreDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StoreModel {
    private final RestTemplate restTemplate;
    private final boolean useDemoData;

    public StoreModel() {
        this.restTemplate = new RestTemplate();
        this.useDemoData = true; // Change this to false when the real API is implemented
    }

    public List<StoreDTO> getStores() {
        if (useDemoData) {
            return DemoDataAPI.getStores();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("/api/store/getAll", request, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                StoreDTO[] stores = new ObjectMapper().readValue(response.getBody(), StoreDTO[].class);
                return Arrays.asList(stores);
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Optional<StoreDTO> getStoreById(String id) {
        if (useDemoData) {
            return Optional.ofNullable(DemoDataAPI.getStoreById(id));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("id", id);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("/api/store/getById", request, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                StoreDTO store = new ObjectMapper().readValue(response.getBody(), StoreDTO.class);
                return Optional.of(store);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

 */