package org.vaadin.UI.model;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

public class StoreService {
    private DemoAPI demoAPI;

    public List<Store> getStores() {
        // This would normally be an API request. For demonstration, we return mock data.
        return Arrays.asList(
                new Store("Store 1", "Description 1", Arrays.asList(
                        new Product("https://via.placeholder.com/150", "Product 1", "Description 1", 10.0, "Category 1"),
                        new Product("https://via.placeholder.com/150", "Product 2", "Description 2", 20.0, "Category 2"),
                        new Product("https://via.placeholder.com/150", "Product 3", "Description 3", 30.0, "Category 3"),
                        new Product("https://via.placeholder.com/150", "Product 4", "Description 4", 40.0, "Category 4"),
                        new Product("https://via.placeholder.com/150", "Product 5", "Description 5", 50.0, "Category 5"),
                        new Product("https://via.placeholder.com/150", "Product 6", "Description 6", 60.0, "Category 6"),
                        new Product("https://via.placeholder.com/150", "Product 7", "Description 7", 70.0, "Category 7"),
                        new Product("https://via.placeholder.com/150", "Product 8", "Description 8", 80.0, "Category 8"),
                        new Product("https://via.placeholder.com/150", "Product 9", "Description 9", 90.0, "Category 9"),
                        new Product("https://via.placeholder.com/150", "Product 10", "Description 10", 100.0, "Category 10"),
                        new Product("https://via.placeholder.com/150", "Product 11", "Description 11", 110.0, "Category 11"),
                        new Product("https://via.placeholder.com/150", "Product 12", "Description 12", 120.0, "Category 12"),
                        new Product("https://via.placeholder.com/150", "Product 13", "Description 13", 130.0, "Category 13"),
                        new Product("https://via.placeholder.com/150", "Product 14", "Description 14", 140.0, "Category 14"),
                        new Product("https://via.placeholder.com/150", "Product 15", "Description 15", 150.0, "Category 15"),
                        new Product("https://via.placeholder.com/150", "Product 16", "Description 16", 160.0, "Category 16"),
                        new Product("https://via.placeholder.com/150", "Product 17", "Description 17", 170.0, "Category 17"),
                        new Product("https://via.placeholder.com/150", "Product 18", "Description 18", 180.0, "Category 18"),
                        new Product("https://via.placeholder.com/150", "Product 19", "Description 19", 190.0, "Category 19"),
                        new Product("https://via.placeholder.com/150", "Product 20", "Description 20", 200.0, "Category 20")


                        // Add more products here
                )),
                new Store("Store 2", "Description 2", Arrays.asList(
                        new Product("https://via.placeholder.com/150", "Product 1", "Description 1", 10.0, "Category 1"),
                        new Product("https://via.placeholder.com/150", "Product 2", "Description 2", 20.0, "Category 2"),
                        new Product("https://via.placeholder.com/150", "Product 3", "Description 3", 30.0, "Category 3"),
                        new Product("https://via.placeholder.com/150", "Product 4", "Description 4", 40.0, "Category 4"),
                        new Product("https://via.placeholder.com/150", "Product 5", "Description 5", 50.0, "Category 5"),
                        new Product("https://via.placeholder.com/150", "Product 6", "Description 6", 60.0, "Category 6"),
                        new Product("https://via.placeholder.com/150", "Product 7", "Description 7", 70.0, "Category 7"),
                        new Product("https://via.placeholder.com/150", "Product 8", "Description 8", 80.0, "Category 8"),
                        new Product("https://via.placeholder.com/150", "Product 9", "Description 9", 90.0, "Category 9"),
                        new Product("https://via.placeholder.com/150", "Product 10", "Description 10", 100.0, "Category 10"),
                        new Product("https://via.placeholder.com/150", "Product 11", "Description 11", 110.0, "Category 11"),
                        new Product("https://via.placeholder.com/150", "Product 12", "Description 12", 120.0, "Category 12"),
                        new Product("https://via.placeholder.com/150", "Product 13", "Description 13", 130.0, "Category 13"),
                        new Product("https://via.placeholder.com/150", "Product 14", "Description 14", 140.0, "Category 14"),
                        new Product("https://via.placeholder.com/150", "Product 15", "Description 15", 150.0, "Category 15"),
                        new Product("https://via.placeholder.com/150", "Product 16", "Description 16", 160.0, "Category 16"),
                        new Product("https://via.placeholder.com/150", "Product 17", "Description 17", 170.0, "Category 17"),
                        new Product("https://via.placeholder.com/150", "Product 18", "Description 18", 180.0, "Category 18"),
                        new Product("https://via.placeholder.com/150", "Product 19", "Description 19", 190.0, "Category 19"),
                        new Product("https://via.placeholder.com/150", "Product 20", "Description 20", 200.0, "Category 20")
                        // Add more products here
                ))
        );
    }

    public Optional<Store> getStoreById(String id) {
        // Simulate API call to get store by ID
        List<Store> stores = getStores();
        return stores.stream().filter(store -> store.getName().equals(id)).findFirst();
    }

    public static class Store {
        private String name;
        private String description;
        private List<Product> products;

        public Store(String name, String description, List<Product> products) {
            this.name = name;
            this.description = description;
            this.products = products;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }

    public static class Product {
        private String picture;
        private String name;
        private String description;
        private double price;
        private String category;

        public Product(String picture, String name, String description, double price, String category) {
            this.picture = picture;
            this.name = name;
            this.description = description;
            this.price = price;
            this.category = category;
        }

        // Getters and setters
        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
}
