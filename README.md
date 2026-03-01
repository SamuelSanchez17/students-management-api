# Plataforma Académica - students-management-api

<div align="center">

| [🇬🇧 English](README.md) | [🇪🇸 Español](README_ES.md) |
|:---:|:---:|

</div>

---

> Professional backend solution for comprehensive academic management: users, roles, courses, periods, subjects, grades, materials and reports, with JWT security and role-based access control.

---

## 🎯 Project Overview

Academic Platform is a REST API designed for educational institutions that need to centralize academic and administrative processes with a maintainable and secure architecture.

This project demonstrates good practices in enterprise backend development: layering, domain validation, RBAC access control, and relational modeling for real-world academic scenarios.
---

## 🏗️ Architecture Overview

```text
┌─────────────────────────────────────────────────────┐
│              API LAYER (Controllers)               │
│  Endpoints REST | JSON | Validación de Requests    │
└────────────────────┬────────────────────────────────┘
                     │
            ┌────────▼────────┐
            │  Service Layer  │
            │ Business Rules  │
            └────────┬────────┘
                     │
            ┌────────▼────────┐
            │ Repository Layer│
            │ Spring Data JPA │
            └────────┬────────┘
                     │
┌────────────────────▼───────────────────────────────┐
│                 Security Layer                     │
│      Spring Security + JWT + Role-Based Access    │
└────────────────────┬───────────────────────────────┘
                     │
             ┌───────▼────────┐
             │   MySQL DB      │
             │ Relational Data │
             └─────────────────┘
```

---

## 🛠️ Technologies Used

### Backend
![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.6-brightgreen?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-RBAC-success?style=for-the-badge&logo=springsecurity)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-Hibernate-green?style=for-the-badge)
![JWT](https://img.shields.io/badge/JWT-jjwt%200.12.6-orange?style=for-the-badge&logo=jsonwebtokens)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql)

### Build & Testing
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-red?style=for-the-badge&logo=apachemaven)
![JUnit5](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5)
![Spring Test](https://img.shields.io/badge/Spring-Boot%20Test-6DB33F?style=for-the-badge&logo=spring)

### Infrastructure
![Git](https://img.shields.io/badge/Git-Version%20Control-red?style=for-the-badge&logo=git)
![REST API](https://img.shields.io/badge/API-REST-lightgrey?style=for-the-badge)

---

## ✨ Key Features

### Access & Security
- **Authentication**: Secure login with JWT at `/api/auth/login`
- **Authorization**: Access control by role (`ADMIN`, `TEACHER`, `STUDENT`)
- **Password Protection**: Password encryption with BCrypt
- **Stateless Sessions**: Decoupled and scalable API without server session

### Academic Management
- **Users & Roles**: User registration, query, and administration
- **Academic Structure**: Management of courses, terms, and subjects
- **People Management**: Management of students and faculty
- **Domain Validation**: Standard and custom validation rules

### Grades, Materials & Reports
- **Grading System**: Recording and editing grades by student/subject/term
- **Learning Materials**: Publishing resources by professor
- **Academic Reports**: Averages, student history, and final report by course
- **Traceability**: Consistent relational model for academic auditing

---

## 📊 Project Structure

```text
src/
├── main/
│   ├── java/com/academia/plataforma/
│   │   ├── controllers/        # Endpoints REST
│   │   ├── services/           # Business contracts
│   │   ├── services/impl/      # Implementations
│   │   ├── repositories/       # Data Access (JPA)
│   │   ├── entities/           # Domain Entities
│   │   ├── Dtos/               # Transfer objects
│   │   ├── security/           # JWT Security Configuration
│   │   │   └── filter/         # Autentication/Verification Filters
│   │   ├── validations/        # Custom Validations
│   │   ├── utils/              # Shared Utilities
│   │   ├── PlataformaApplication.java
│   │   └── RoleInitializer.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/academia/plataforma/
        └── PlataformaApplicationTests.java
```

---

## 🔐 Highlighted Technical Features

### Robust Backend
- Layered architecture: Controller → Service → Repository
- Centralized security with SpringSecurityConfig
- Dedicated JWT filters for authentication and validation
- Automatic role initialization at startup

### Performance & Scalability
- Stateless design for horizontal scalability
- ORM with JPA/Hibernate and domain-oriented queries
- Modular structure that facilitates system evolution

### Maintainability
- Clear separation of responsibilities
- DTOs to decouple internal model from API responses
- Reusable validations (`@IsRequired`, `@ValidDateRange`)
- Code ready for integration with web/mobile frontend


---

## 🚀 Local Setup & Run

### 1) Prerequisites
- Java 17+
- MySQL 8+
- Maven (optional, included in Maven Wrapper)

### 2) Configure environment variables
The following are used in `src/main/resources/application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

Example on Windows PowerShell:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/academia_db?useSSL=false&serverTimezone=UTC"
$env:DB_USER="root"
$env:DB_PASSWORD="tu_password"
```

### 3) Run the Application
from the project's root folder:

```powershell
.\mvnw.cmd spring-boot:run
```

Multiplatform alternative:

```bash
./mvnw spring-boot:run
```

Base URL:

```text
http://localhost:8080
```

### 4) Run Tests

```powershell
.\mvnw.cmd test
```

---

## 📬 Quick API Usage

### Login

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

### Consume protected endpoint

```http
Authorization: Bearer <token>
```

---

## 📝 Notes

This repository reflects a professional backend approach for the educational domain, geared towards demonstrating:

- Proficiency in backend architecture with Spring Boot
- Implementation of real-world security with JWT + RBAC
- Relational data modeling for academic applications
- Application of best practices for maintainability and scalability

---

## 👨‍💻 About This Project

**Developer**: Samuel Sánchez Guzmán  
**Type**: Backend REST API  
**Status**: Active / Production-ready architecture  
**Last Updated**: March 2026
