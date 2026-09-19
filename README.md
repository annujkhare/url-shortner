# URL Shortener Service

A simple URL Shortener application built using Java and Spring Boot.

## Technologies Used

* Java
* Spring Boot
* REST API
* MySQL
* JPA / Hibernate
* HTML
* CSS
* JavaScript

## Features

* Shorten long URLs
* Generate unique short URLs
* Redirect to original URLs
* Track URL click count
* URL validation
* REST API integration

## How to Run

1. Clone the repository
2. Open the project in VS Code
3. Create a MySQL database named `url_shortener`
4. Configure your MySQL username and password in `application.properties`
5. Run:

```bash
./mvnw spring-boot:run
```

6. Open:

http://localhost:8080

## API Endpoints

```text
POST /api/urls/shorten
GET  /api/urls/{shortCode}
GET  /api/analytics/{shortCode}
GET  /s/{shortCode}
```
