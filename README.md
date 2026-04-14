# Backend Template

A Spring Boot backend template providing basic REST APIs for account and user information management with JPA, validation, security, CORS, and OpenAPI support.

## Table of Contents

- [Overview](#overview)
- [Built With](#built-with)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Local Setup](#local-setup)
  - [Docker Compose](#docker-compose)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [OpenAPI / Swagger](#openapi--swagger)
- [Environment Variables](#environment-variables)
- [Testing](#testing)
- [Notes](#notes)

## Overview

This repository is a backend template for a Spring Boot application with:

- REST controllers for accounts and user info
- Spring Data JPA for persistence
- PostgreSQL as the default database
- Spring Security for application authorization
- Spring Validation for request validation
- SpringDoc OpenAPI for API documentation
- CORS configuration for local frontend integration

## Built With

- Java 17
- Spring Boot 3.2.7
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring Web
- PostgreSQL
- SpringDoc OpenAPI UI
- Maven

## Project Structure

- `src/main/java/com/main/java`
  - `controller/` — general REST endpoints
  - `config/` — security, CORS, OpenAPI configuration
  - `common/` — shared utilities and response wrappers
  - `exceptions/` — global error handling
  - `features/account/` — account feature controllers, services, DTOs, mappers, validation
  - `features/userInfo/` — user info feature controllers, services, DTOs, mappers
- `src/main/resources`
  - `application.properties` — main application configuration
  - `application-dev.properties` — development profile configuration
  - `error-messages.properties` — application messages
- `docker-compose.yml` — optional Docker Compose setup for the app and PostgreSQL

## Getting Started

### Prerequisites

- Java 17
- Maven
- PostgreSQL (or Docker)
- Git

### Local Setup

1. Clone the repository:
   ```bash
   git clone <repo-url>
   cd backend-template
   ```

2. Set environment variables:
   ```bash
   export SPRING_PROFILES_ACTIVE=dev
   export POSTGRES_DB=backend_template
   export POSTGRES_USER=postgres
   export POSTGRES_PASSWORD=postgres
   ```

3. Build the project:
   ```bash
   ./mvnw clean package
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Docker Compose

1. Create a `.env` file containing:
   ```env
   POSTGRES_DB=backend_template
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=postgres
   ```

2. Start services:
   ```bash
   docker compose up --build
   ```

3. Access the backend at:
   ```
   http://localhost:8080
   ```

## Running the Application

The application starts on port `8080` by default.

```bash
./mvnw spring-boot:run
```

## API Endpoints

### General

- `GET /api/home` — Returns a simple hello message.

### Accounts

- `GET /api/v1/accounts?page=0&size=10` — List paged accounts.
- `POST /api/v1/accounts` — Create a new account.
- `GET /api/v1/accounts/{id}` — Get account by ID.
- `PUT /api/v1/accounts/{id}` — Update account by ID.
- `DELETE /api/v1/accounts/{id}` — Delete account by ID.
- `DELETE /api/v1/accounts/batch` — Delete multiple accounts.

### Users

- `GET /api/v1/users?page=0&size=10` — List paged user info.
- `POST /api/v1/users` — Create a new user info record.
- `GET /api/v1/users/{id}` — Get user info by ID.
- `PUT /api/v1/users/{id}` — Update user info by ID.
- `DELETE /api/v1/users/{id}` — Delete user info by ID.
- `DELETE /api/v1/users/batch` — Delete multiple user info records.

## OpenAPI / Swagger

This project uses SpringDoc OpenAPI for API documentation and Swagger UI.

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

The OpenAPI configuration is defined in `src/main/java/com/main/java/config/OpenApiConfig.java`.
The documentation is exposed automatically by SpringDoc and is available without authentication for API routes.

> Note: The current security configuration permits all `/api/**` requests and relies on Spring Security for any non-API endpoints.

## Environment Variables

- `DATABASE_URL` — JDBC URL for PostgreSQL.
- `POSTGRES_USER` — PostgreSQL username.
- `POSTGRES_PASSWORD` — PostgreSQL password.
- `SPRING_PROFILES_ACTIVE` — active Spring profile (`dev` by default).

Default dev values in `application-dev.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/default_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## Testing

Run tests with:

```bash
./mvnw test
```

## Notes

- The current Spring Security configuration permits all `/api/**` requests and protects other endpoints.
- The repository metadata in `pom.xml` should be updated with proper license, developer, and SCM details.
- A `CONTRIBUTING.md` file is recommended for development guidelines.
