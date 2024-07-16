<img src="src/main/webapp/icons/logoFinal.png" alt="Project Logo" width="300">

# Market Trading UI

This project is a sophisticated market trading system.

It utilizes Spring Boot for the backend and Vaadin for the frontend to provide a seamless user experience.



## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installing](#installing)
- [Running the Application](#running-the-application)
- [Functionality Overview](#functionality-overview)




## Getting Started

These instructions will help you set up and run the project on your local machine.

## Prerequisites

- Java 17 or higher
- Maven
- Node.js (for Vaadin)
- Spring Boot

## Installing

1. **Clone the repository:**
   ```sh
   git clone https://github.com/Hashminiya/Market-Trading_UI.git
   cd Market-Trading_UI
   ```

2. **Install dependencies:**
   ```sh
   mvn clean install
   ```
3. **Run the backend:**

      Before running the application, you need to start the backend API.

      You can find the instructions to run the backend API https://github.com/Hashminiya/Market-Trading_System

      Backend API will run on: `http://localhost:8080`

## Running the Application


After installing the dependencies and setting up the project, you can run the application using:
   ```sh
   mvn jetty:run
   ```

The application will start on http://localhost:8081


## Functionality Overview

The Market-Trading UI application offers a wide range of functionalities to provide a comprehensive trading platform.
Below are the key features and capabilities available to users, store owners, and admins:

#### User Management
- **User Registration:** New users can register by providing necessary details such as username, password, and email.
- **User Login:** Registered users can log in using their credentials to access their account and utilize the platform's features.

#### Store Management
- **Create Store:** Users can create a new store by providing store details such as name, description, and category.
- **Add Items to Store:** Store owners can add items to their store by specifying item details like name, price, description, and stock quantity.
- **Modify Items:** Store owners can update item details to keep their inventory accurate and up-to-date.
- **Delete Items:** Store owners can remove items from their store if they are no longer available for sale.
- **Assign Store Owners:** Store owners can assign additional owners to help manage the store.

#### Shopping Cart
- **Add Items to Cart:** Users can browse stores and add items to their shopping cart for purchase.
- **Modify Shopping Cart:** Users can update quantities or remove items from their shopping cart as needed.
- **Checkout:** Users can proceed to checkout to finalize their purchase and place an order.
- **View Order History:** Admins can view the history of orders placed on the platform, providing oversight and management capabilities for all transactions.

#### Admin Capabilities
- **Order History:** Admins have access to the complete history of all purchases made on the platform. This includes details such as order date, items purchased, and total amount spent, allowing for effective monitoring and analysis of the trading activities.

This functionality makes the Market-Trading UI application a robust platform for managing stores, handling purchases, and ensuring smooth transactions between buyers and sellers.







