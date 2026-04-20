# Backend Template

A Spring Boot backend template providing REST APIs for authentication, user information, and address management with JPA, JWT authentication, validation, CORS, and OpenAPI support.

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
  - [General](#general)
  - [Authentication](#authentication)
  - [Accounts](#accounts)
  - [Users](#users)
  - [Addresses](#addresses)
- [Authentication & Security](#authentication--security)
- [OpenAPI / Swagger](#openapi--swagger)
- [Environment Variables](#environment-variables)
- [Testing](#testing)
- [Notes](#notes)

## Overview

This repository is a backend template for a Spring Boot application with:

- JWT-based stateless authentication with login/logout
- Role-based access control (USER, ADMIN)
- REST controllers for accounts, user info, and addresses
- Generic base layer (BaseEntity, BaseRepository, BaseService) for rapid CRUD feature development
- Spring Data JPA for persistence with soft-delete support
- PostgreSQL as the default database
- Spring Security for application authorization
- Spring Validation for request validation
- SpringDoc OpenAPI for API documentation
- CORS configuration for frontend integration
- Docker and Docker Compose for containerized deployment
- GitHub Actions CI pipeline

## Built With

- Java 17
- Spring Boot 3.2.7
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring Web
- PostgreSQL
- JSON Web Tokens (jjwt 0.11.5)
- SpringDoc OpenAPI UI
- Lombok
- Maven

## Project Structure

- `src/main/java/com/larvae/backend_template`
  - `controller/` — general REST endpoints (e.g., health check)
  - `config/` — security, CORS, OpenAPI configuration
  - `common/` — shared utilities and response wrappers (`ApiResponse`)
  - `entity/` — JPA entities (`BaseEntity`, `Account`, `UserInfo`, `Address`)
  - `enums/` — application enums (`Roles`, `AccountStatus`, `MyanmarRegion`)
  - `exceptions/` — global error handling (`GlobalExceptionHandler`, `CustomException`)
  - `mapper/` — base data mapper for audit fields
  - `repository/` — base repository with soft-delete query methods
  - `service/` — base service interface and abstract implementation
  - `security/` — Spring Security config, JWT filter, token utilities, blacklist service
  - `features/auth/` — account/authentication controllers, services, DTOs, mappers, validation
  - `features/userInfo/` — user info controllers, services, DTOs, mappers
  - `features/address/` — address controllers, services, DTOs, mappers
- `src/main/resources`
  - `application.properties` — main application configuration
  - `application-dev.properties` — development profile configuration
  - `application-prod.properties` — production profile configuration
- `src/test/java` — test classes
- `.github/workflows/backend-ci.yml` — GitHub Actions CI pipeline
- `docker-compose.yml` — Docker Compose setup for the app and PostgreSQL
- `Dockerfile` — multi-stage Docker build

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
   export JWT_SECRET_KEY=<your-secret-key-at-least-64-characters>
   ```

3. Build the project:
   ```bash
   ./mvnw clean package
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

> **Note:** On first startup, a default admin account is created automatically:
> - Username: `admin`
> - Password: `Admin@123`

### Docker Compose

1. Create a `.env` file containing:
   ```env
   POSTGRES_DB=backend_template
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=postgres
   JWT_SECRET_KEY=<your-secret-key-at-least-64-characters>
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

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/v1/home` | Health check — returns a hello message | No |

### Authentication

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/v1/auth/login` | Authenticate and receive a JWT token | No |
| `POST` | `/api/v1/auth/logout` | Revoke the current JWT token | Yes |

**Login request body:**
```json
{
  "username": "admin",
  "password": "Admin@123",
  "role": "ROLE_ADMIN",
  "status": "ACTIVE"
}
```

**Login response:**
```json
{
  "success": true,
  "message": "Success",
  "data": {
    "token": "<jwt-token>"
  },
  "timestamp": "2026-04-20T10:30:00"
}
```

### Accounts

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/v1/auth?page=0&size=10` | List paged accounts | Yes |
| `POST` | `/api/v1/auth` | Create a new account | Yes |
| `GET` | `/api/v1/auth/{id}` | Get account by ID | Yes |
| `PUT` | `/api/v1/auth/{id}` | Update account by ID | Yes |
| `DELETE` | `/api/v1/auth/{id}` | Soft delete account by ID | Yes |
| `POST` | `/api/v1/auth/actions/batch-delete` | Soft delete multiple accounts | Yes |

### Users

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/v1/users?page=0&size=10` | List paged user info | Yes |
| `POST` | `/api/v1/users` | Create a new user info record | Yes |
| `GET` | `/api/v1/users/{id}` | Get user info by ID | Yes |
| `PUT` | `/api/v1/users/{id}` | Update user info by ID | Yes |
| `DELETE` | `/api/v1/users/{id}` | Soft delete user info by ID | Yes |
| `POST` | `/api/v1/users/actions/batch-delete` | Soft delete multiple user info records | Yes |

### Addresses

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/v1/addresses?page=0&size=10` | List paged addresses | Yes |
| `POST` | `/api/v1/addresses` | Create a new address | Yes |
| `GET` | `/api/v1/addresses/{id}` | Get address by ID | Yes |
| `PUT` | `/api/v1/addresses/{id}` | Update address by ID | Yes |
| `DELETE` | `/api/v1/addresses/{id}` | Soft delete address by ID | Yes |
| `POST` | `/api/v1/addresses/actions/batch-delete` | Soft delete multiple addresses | Yes |

## Authentication & Security

This project uses **JWT (JSON Web Token)** for stateless authentication.

- **Login:** Send credentials to `/api/v1/auth/login` to receive a JWT token.
- **Authenticated requests:** Include the token in the `Authorization` header:
  ```
  Authorization: Bearer <your-jwt-token>
  ```
- **Logout:** Send a `POST` to `/api/v1/auth/logout` with the token in the header. The token will be blacklisted.
- **Roles:** `ROLE_USER` and `ROLE_ADMIN`. Admin-only operations (hard delete) require `ROLE_ADMIN`.
- **Password policy:** 8–32 characters, must contain at least one digit, one lowercase, one uppercase, and one special character.

### Public Endpoints (No authentication needed)

- `/api/v1/home`
- `/api/v1/auth/login`
- `/swagger-ui/**`
- `/v3/api-docs/**`

### Security Configuration

- CSRF is disabled (stateless API)
- Sessions are stateless (`SessionCreationPolicy.STATELESS`)
- Passwords are hashed with BCrypt
- JWT tokens expire after 24 hours

## OpenAPI / Swagger

This project uses SpringDoc OpenAPI for API documentation and Swagger UI.

- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

The OpenAPI configuration is defined in `src/main/java/com/larvae/backend_template/config/OpenApiConfig.java`.
The Swagger UI is accessible without authentication.

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `DATABASE_URL` | JDBC URL for PostgreSQL | `jdbc:postgresql://localhost:5432/default_db` |
| `POSTGRES_USER` | PostgreSQL username | `postgres` |
| `POSTGRES_PASSWORD` | PostgreSQL password | `postgres` |
| `SPRING_PROFILES_ACTIVE` | Active Spring profile | `dev` |
| `JWT_SECRET_KEY` | Secret key for JWT signing (min 64 chars) | *(fallback provided, override in production)* |

### Profile-specific settings

**Development (`dev`):**
```properties
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
logging.level.com.main=DEBUG
```

**Production (`prod`):**
```properties
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=validate
logging.level.com.main=INFO
server.error.include-message=never
server.error.include-stacktrace=never
```

## Testing

Run tests with:

```bash
./mvnw test
```

The CI pipeline (`.github/workflows/backend-ci.yml`) automatically runs tests on push and pull requests to the `develop` branch using a PostgreSQL service container.

## Notes

- On first startup, a default admin account is created (`admin` / `Admin@123`).
- The application uses **soft delete** — records are marked as inactive rather than permanently removed. Admin users can perform hard deletes.
- CORS is configured for `http://localhost:5173` and `http://localhost:3000` by default. Update `app.cors.allowed-origins` in `application.properties` for other environments.
- The repository metadata in `pom.xml` should be updated with proper license, developer, and SCM details for your project.
