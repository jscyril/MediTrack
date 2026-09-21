# MediTrack

Meditrack is a Spring Boot backend for a medicine reminder app. It provides RESTful APIs for user registration, authentication, and will eventually support medicine reminders, stock tracking, and health monitoring features.

## 🚀 Features

- User registration with username and email
- PostgreSQL database integration
- Spring Security configuration
- REST API with JSON responses
- Future support for JWT-based authentication

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

## Configuration

Set these environment variables before running against PostgreSQL:

```text
DATABASE_URL=jdbc:postgresql://localhost:5433/meditrack
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=your-password
JWT_SECRET=replace-with-a-long-random-secret
JWT_EXPIRATION_SECONDS=3600
```

Tests use an in-memory H2 database automatically.
