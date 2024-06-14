package org.vaadin.UI.Util;

import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;

import java.util.Arrays;
import java.util.List;

public class DemoDataAPI {

    public static List<ItemDTO> demoItems1 = Arrays.asList(
            new ItemDTO(1, "Laptop", 10, 1, 999.99),
            new ItemDTO(2, "Smart TV", 5, 1, 599.99),
            new ItemDTO(3, "Bluetooth Speaker", 20, 1, 49.99)
            // Add more items here
    );

    public static List<ItemDTO> demoItems2 = Arrays.asList(
            new ItemDTO(4, "T-Shirt", 50, 2, 14.99),
            new ItemDTO(5, "Jeans", 30, 2, 39.99),
            new ItemDTO(6, "Jacket", 15, 2, 59.99)
            // Add more items here
    );

    // Add more item lists for other stores here

    public static List<StoreDTO> demoStores = Arrays.asList(
            new StoreDTO("Electronics", "Store for various electronic items", demoItems1),
            new StoreDTO("Clothing", "Store for clothing and fashion items", demoItems2)
            // Add more stores here
    );

    public static List<StoreDTO> getStores() {
        return demoStores;
    }

    public static StoreDTO getStoreById(String id) {
        return demoStores.stream().filter(store -> String.valueOf(store.getItems().get(0).getStoreId()).equals(id)).findFirst().orElse(null);
    }
}
