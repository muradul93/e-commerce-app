# E-commerce Customer Service

This project is a part of an e-commerce application focused on managing customer-related operations. It is built using Java, Spring Boot, and Maven, and it interacts with an SQL database. This project is designed as a microservice.

## Features

### Customer Service
- Create, update, retrieve, and delete customer details
- Check if a customer exists by ID

### Order Service
- Manage customer orders

### Payment Service
- Handle payment processing

### Notification Service
- Send notifications to customers

### Product Service
- Manage product information

## Technologies Used

- Java
- Spring Boot
- Maven
- SQL

## Project Structure

- `CustomerController.java`: Handles HTTP requests related to customer operations.
- `CustomerService.java`: Contains business logic for customer operations.
- `CustomerRequest.java`: DTO for customer creation and update requests.
- `CustomerResponse.java`: DTO for customer response data.
- `Address.java`: Represents the address of a customer.

## Endpoints

- `POST /api/v1/customers`: Create a new customer.
- `PUT /api/v1/customers`: Update an existing customer.
- `GET /api/v1/customers`: Retrieve all customers.
- `GET /api/v1/customers/exists/{customer-id}`: Check if a customer exists by ID.
- `GET /api/v1/customers/{customer-id}`: Retrieve customer details by ID.
- `DELETE /api/v1/customers/{customer-id}`: Delete a customer by ID.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- SQL Database

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/muradul93/e-commerce-app.git