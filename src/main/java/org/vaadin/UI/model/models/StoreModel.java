package org.vaadin.UI.model.models;

import org.vaadin.UI.model.DTOs.ProductDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

public class StoreModel {

    public static List<StoreDTO> getStores() {
        // This would normally be an API request. For demonstration, we return mock data.
        return Arrays.asList(
                new StoreDTO("Store 1", "Description 1", Arrays.asList(
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 1", "Description 1", 10.0, "Category 1"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 2", "Description 2", 20.0, "Category 2"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 3", "Description 3", 30.0, "Category 3"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 4", "Description 4", 40.0, "Category 4"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 5", "Description 5", 50.0, "Category 5"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 6", "Description 6", 60.0, "Category 6"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 7", "Description 7", 70.0, "Category 7"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 8", "Description 8", 80.0, "Category 8"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 9", "Description 9", 90.0, "Category 9"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 10", "Description 10", 100.0, "Category 10"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 11", "Description 11", 110.0, "Category 11"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 12", "Description 12", 120.0, "Category 12"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 13", "Description 13", 130.0, "Category 13"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 14", "Description 14", 140.0, "Category 14"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 15", "Description 15", 150.0, "Category 15"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 16", "Description 16", 160.0, "Category 16"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 17", "Description 17", 170.0, "Category 17"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 18", "Description 18", 180.0, "Category 18"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 19", "Description 19", 190.0, "Category 19"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 20", "Description 20", 200.0, "Category 20")


                        // Add more ProductDTOs here
                )),
                new StoreDTO("Store 2", "Description 2", Arrays.asList(
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 1", "Description 1", 10.0, "Category 1"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 2", "Description 2", 20.0, "Category 2"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 3", "Description 3", 30.0, "Category 3"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 4", "Description 4", 40.0, "Category 4"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 5", "Description 5", 50.0, "Category 5"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 6", "Description 6", 60.0, "Category 6"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 7", "Description 7", 70.0, "Category 7"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 8", "Description 8", 80.0, "Category 8"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 9", "Description 9", 90.0, "Category 9"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 10", "Description 10", 100.0, "Category 10"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 11", "Description 11", 110.0, "Category 11"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 12", "Description 12", 120.0, "Category 12"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 13", "Description 13", 130.0, "Category 13"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 14", "Description 14", 140.0, "Category 14"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 15", "Description 15", 150.0, "Category 15"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 16", "Description 16", 160.0, "Category 16"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 17", "Description 17", 170.0, "Category 17"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 18", "Description 18", 180.0, "Category 18"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 19", "Description 19", 190.0, "Category 19"),
                        new ProductDTO("https://via.placeholder.com/150", "ProductDTO 20", "Description 20", 200.0, "Category 20")
                        // Add more ProductDTOs here
                ))
        );
    }

    public Optional<StoreDTO> getStoreById(String id) {
        // Simulate API call to get store by ID
        List<StoreDTO> stores = getStores();
        return stores.stream().filter(store -> store.getName().equals(id)).findFirst();
    }

   
}
