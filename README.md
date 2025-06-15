# Mood Tracker Backend (Spring Boot)

A backend project built with Spring Boot, designed to explore and apply core backend development practices and tools commonly used in real-world applications.  
The focus is on **clean architecture**, **robust testing**, and **modern Java backend patterns**.


## Technologies & Concepts Used

### Core Stack
- **Java**
- **Spring Boot**
- **Maven**

### Architecture
- **Layered Architecture**
  - `Controller` – handles HTTP requests
  - `Service` – business logic layer
  - `Repository` – data access layer (Spring Data JPA)
  - `DTOs` – for separating internal models from external representations
- **ModelMapper** – for DTO ↔ Entity conversion

### Testing
- **JUnit 5** – unit and integration testing
- **MockMvc** – controller testing
- **Mockito** – mocking service/repository layers
- **Test-Driven Development (TDD)** approach – writing tests before implementation

### Validation
- **Jakarta Bean Validation** (`@NotBlank`, `@Size`, etc.)
- **Custom Global Exception Handling** using `@ControllerAdvice`

### API Documentation
- **Swagger / OpenAPI** – auto-generated docs from annotations

