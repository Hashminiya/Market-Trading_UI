package org.vaadin.UI.Util;

import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoDataAPI {

    public static List<StoreDTO> getStores() {
        List<ItemDTO> items1 = Arrays.asList(
                new ItemDTO(1, "Laptop", 5, 1, 1000.0),
                new ItemDTO(2, "Phone", 10, 1, 500.0)
        );
        List<ItemDTO> items2 = Arrays.asList(
                new ItemDTO(3, "T-shirt", 20, 2, 20.0),
                new ItemDTO(4, "Jeans", 15, 2, 40.0)
        );
        List<ItemDTO> items3 = Arrays.asList(
                new ItemDTO(5, "Tent", 8, 3, 200.0),
                new ItemDTO(6, "Sleeping Bag", 12, 3, 50.0)
        );

        List<StoreDTO> stores = new ArrayList<>();
        stores.add(new StoreDTO(1, "Electronics", "Electronic devices and gadgets", items1));
        stores.add(new StoreDTO(2, "Clothing", "Fashionable clothing items", items2));
        stores.add(new StoreDTO(3, "Camping", "Camping equipment and supplies", items3));

        return stores;
    }

    public static StoreDTO getStoreById(long id) {
        List<StoreDTO> stores = getStores();
        return stores.stream()
                .filter(store -> store.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
