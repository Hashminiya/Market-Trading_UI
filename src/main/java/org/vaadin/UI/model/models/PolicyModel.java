package org.vaadin.UI.model.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.PolicyViewDTO;

import java.util.ArrayList;
import java.util.List;

public class PolicyModel {

    private final RestTemplate restTemplate;

    public PolicyModel (){
        this.restTemplate = new RestTemplate();
    }
    public List<PolicyViewDTO> getPolicies(String storeName, String token) {
        try {
            String url = "http://localhost:8080/storeManagement/viewAllPolicies?token=" + token + "&storeName=" + storeName;

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(response.getBody(), new TypeReference<List<PolicyViewDTO>>() {});

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }




    public List<String> getStores(String token) {
        String url = "http://localhost:8080/user/viewStoresByNameForUserOwnership?token=" + token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<List<String>> response = restTemplate.exchange(
                    url, HttpMethod.PUT, requestEntity, (Class<List<String>>) (Class<?>) List.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String savePolicy(String token, String storeName, String policyDetails){
        String url = "http://localhost:8080/storeManagement/addPolicyByStoreNameAndToken";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        params.add("storeName", storeName);
        params.add("policyDetails", policyDetails);
        System.out.println(policyDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
