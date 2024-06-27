package org.vaadin.UI.model.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.PurchaseDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarketHistoryPurchasesModel {
    private final RestTemplate restTemplate;
    public MarketHistoryPurchasesModel (){
        this.restTemplate = new RestTemplate();
    }
    public ArrayList<PurchaseDTO> getMarketHistory(String token) {
        try {
            String url = "http://localhost:8080/systemManager/viewMarketPurchaseHistory?token=" + token;

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.getBody());
                ArrayList<PurchaseDTO> purchases = new ArrayList<>();

                root.forEach(purchaseNode -> {
                    long id = purchaseNode.get("id").asLong();
                    double totalAmount = purchaseNode.get("totalAmount").asDouble();
                    String userId = purchaseNode.get("userId").asText();
                    String purchaseDate = purchaseNode.get("purchaseDate").asText();

                    List<ItemDTO> purchasedItemsList = new ArrayList<>();
                    JsonNode itemsNode = purchaseNode.get("purchasedItemsList");

                    itemsNode.forEach(itemNode -> {
                        long itemId = itemNode.get("id").asLong();
                        double itemPrice = itemNode.get("price").asDouble();
                        int itemQuantity = itemNode.get("quantity").asInt();
                        String itemName = itemNode.get("name").asText();
                        List<String> itemCategories = new ArrayList<>();
                        itemNode.get("categories").forEach(categoryNode -> {
                            itemCategories.add(categoryNode.asText());
                        });
                        String itemDescription = itemNode.get("description").asText();
                        long storeId = itemNode.get("storeId").asLong();

                        ItemDTO item = new ItemDTO(itemId, itemName, itemQuantity, storeId, itemPrice, itemCategories, itemDescription);
                        purchasedItemsList.add(item);
                    });

                    purchases.add(new PurchaseDTO(id, purchasedItemsList, totalAmount, userId, purchaseDate));
                });

                return purchases;
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
