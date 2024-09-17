Logistic Management System
Overview
The Logistic Management System is a Spring Boot application designed to manage logistics-related operations, such as users, carriers, trucks, orders, and cargos. It also incorporates functionalities for managing loading and unloading operations, and provides security measures for user roles.

Key Features:
User Management: Create, update, and delete users with role-based access control.
Carrier and Driver Management: Handle carriers, their drivers, and associated details.
Order Management: Create and manage orders including details like cargo, loading, and unloading.
Security: Implements Spring Security for handling access control using roles (admin, user).
Data Persistence: Utilizes PostgreSQL for data storage.
Validation: Uses Jakarta Bean Validation for request validations.
Technologies Used
Java: Programming language used for building the application.
Spring Boot: Framework used for creating the REST API.
Spring Security: Provides role-based access control.
Spring Data JPA: For data persistence and database interaction.
PostgreSQL: Database system used for storage.
Maven: Dependency management tool.
Jakarta Bean Validation: For request validation.
Requirements
Java 17 or higher
Maven 4.x
PostgreSQL (or any other relational database)
Spring Boot 3.3.3
Getting Started
Clone the Repository
bash
Copy code
git clone https://github.com/your-username/logistic.git
cd logistic
Set Up Database
Ensure PostgreSQL is installed and running. Create a database named logistic_db:

sql
Copy code
CREATE DATABASE logistic_db;
Configure your database connection in the application.properties (or application.yml) file:

properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/logistic_db
spring.datasource.username=your-username
spring.datasource.password=your-password
Build and Run the Application
bash
Copy code
mvn clean install
mvn spring-boot:run
The application will start at http://localhost:8080.

REST API Endpoints
The application provides various REST API endpoints for managing the logistic system. Below are a few key ones:

User Management
POST /api/admin/addUser - Add a new user.
PUT /api/admin/updateUser/{userId} - Update an existing user.
DELETE /api/admin/deleteUser/{userId} - Delete a user.
Carrier and Driver Management
POST /api/admin/addCarrier - Add a new carrier.
PUT /api/admin/updateCarrier/{carrierId} - Update an existing carrier.
POST /api/admin/addDriver - Add a new driver.
Order Management
POST /api/admin/addOrder - Add a new order.
GET /api/admin/getAllOrders - Get all orders.
DELETE /api/admin/deleteOrder/{orderId} - Delete an order.
Loading and Unloading Management
POST /api/admin/addLoading - Add loading information.
PUT /api/admin/updateLoading/{loadingId} - Update loading information.
POST /api/admin/addUnloading - Add unloading information.
Security
The application uses role-based access control with Spring Security. Users need to provide their role as part of requests, which determines their access level.