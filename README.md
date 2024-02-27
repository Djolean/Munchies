# 🍽 Munchies - Food Ordering Simplified for Small and Medium Size Companies

## 🌐 Project Overview

Munchies is an application designed to streamline the food ordering process within small and medium-sized companies. The traditional challenges of deciding on a restaurant and placing orders are addressed through a centralized platform, offering a repository of restaurants including contact information, group order creation followed by order timeout (you can set the time of the order), adding items into it and calculating total price of the order. The application consists of two main parts:

### Administration Portal

#### Log-in
- Simple login form with email and password fields.

#### Add Restaurant
- Form for administrators to add a new restaurant.
- Input fields: restaurant name, address, phone number, menu URL, delivery information.

#### View All Restaurants
- Table displaying all restaurants in the system.
- Columns: restaurant name, short name, address, phone number, menu link.

#### View Restaurant Details
- Page displaying all details for a specific restaurant.
- Allows administrator to delete and edit the restaurant.

#### Edit Restaurant
- Form for administrators to edit restaurant information.

### Employee Portal

#### View All Restaurants
- Table displaying all restaurants.
- Columns: restaurant name, short name, address, phone number, menu URL.
- Link to the "New Group Order" page for each restaurant.

#### View Restaurant Details
- Page displaying all details for a specific restaurant.
- Allows the employee to delete and edit the restaurant.

#### New Group Order
- Page for employees to start a new group order.
- Requires employee name and order timeout.
- Clicking "Create" redirects to the Group Order page.

#### Group Order
- Page displaying information about a specific group order.
- Sections: Order information, Add selection form, Table of individual orders.

## 💻 Technologies Used

- Spring Boot
- Maven
- Spring Security with basic Auth
- Thymeleaf for frontend
- PostgreSQL for database
- Flyway for migrations
- Hibernate
- Git for version control
- JUnit for testing
- Postman 
- Swagger with preauthorization for all endpoints
- Slack Integrations (slack bot for listing all restaurants, creating new order and adding items to that order via slash commands)
- Docker for project deployment
- Angular (other repository, but not finished)

## 💬 Slack Bot Integration

- Implemented a Slack bot with API integration to execute slash commands.
- The bot can provide direct responses and write data into the database through commands on Slack.

## 🔐 Admin Verification

- Ensure security with a verification process for new admins.
- Verification sent via e-mail with template using unique token.

## 🔑 Forgot Password? (feature)

- Password reset if you ever forget it.
- Quickly reset your password and rewrite a new one on page with your token that is provided by e-mail.

## 🐳 **Docker Ready**
 
- Munchies is Dockerized for easy deployment and scalability.
- Run the app seamlessly in containers with server and database with migrations.
  
## 🚀 Getting Started

1. Clone the repository: `git clone <repository-url>`
2. Set up the database using Flyway migrations.
3. Build and run the application using Maven.
4. Access the application via the provided URLs.
5. Configure Slack integration for enhanced functionality (Munchies bot is for single workspace only, you can make your own with slack api)

## 👤 Contributors

- [Đorđe Risić] , java developer
- Initial user data: (email: djoler001@gmail.com
                      password: djoledjole)


