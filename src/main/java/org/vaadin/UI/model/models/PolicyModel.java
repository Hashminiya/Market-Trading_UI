package org.vaadin.UI.model.models;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.Policies.PolicyDTO;

import java.util.*;

public class PolicyModel {

    private final RestTemplate restTemplate;

    public PolicyModel (){
        this.restTemplate = new RestTemplate();
    }
    public List<PolicyDTO> getPolicies(String storeName, String token) {
        return new ArrayList<>();
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
    public String assignManager(String token, String userName, String storeNumber, Set<String> permissions) {
        String url = "http://localhost:8080/storeManagement/assignStoreManager";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        params.add("storeId", storeNumber);
        params.add("newManagerI", userName);

        // Convert set of permissions to list
        List<String> permissionsList = new ArrayList<>(permissions);
        params.put("permissions", permissionsList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
