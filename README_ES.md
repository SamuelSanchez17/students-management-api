# Plataforma Académica - Education Management API

<div align="center">

| [🇬🇧 English](README.md) | [🇪🇸 Español](README_ES.md) |
|:---:|:---:|

</div>

---

> Solución backend profesional para gestión académica integral: usuarios, roles, cursos, periodos, asignaturas, notas, materiales y reportes, con seguridad JWT y control de acceso por roles.

---

## 🎯 Descripción general del proyecto

Plataforma Académica es una API REST diseñada para instituciones educativas que necesitan centralizar procesos académicos y administrativos con una arquitectura mantenible y segura.

Este proyecto demuestra buenas prácticas de desarrollo backend empresarial: separación por capas, validaciones de dominio, control de acceso RBAC y modelado relacional para escenarios académicos reales.

---

## 🏗️ Descripción general de la arquitectura

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

## 🛠️ Tecnologías utilizadas

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

## ✨ Características clave

### Accesso & Seguridad
- **Autenticación**: Login seguro con JWT en `/api/auth/login`
- **Autorización**: Control de acceso por rol (`ADMIN`, `PROFESOR`, `ESTUDIANTE`)
- **Protección de Contraseñas**: Cifrado de contraseñas con BCrypt
- **Sesiones sin estado**: API desacoplada y escalable sin sesión en servidor

### Gestión Académica
- **Roles y Usuarios**: Registro, consulta y administración de usuarios
- **Estructura Académica**: Gestión de cursos, periodos y asignaturas
- **Gestion de personal**: Gestión de estudiantes y profesores
- **Validación de dominio**: Reglas de validación estándar y personalizadas

### Calificaciones, materiales e informes
- **Sistema de calificación**: Registro y edición de notas por estudiante/asignatura/periodo
- **Materiales de Aprendizaje**: Publicación de recursos por profesor
- **Reportes Académicos**: Promedios, historial estudiantil y reporte final por curso
- **Trazabilidad**: Modelo relacional consistente para auditoría académica

---

## 📊 Estructura del proyecto

```text
src/
├── main/
│   ├── java/com/academia/plataforma/
│   │   ├── controllers/        # Endpoints REST
│   │   ├── services/           # Contratos de negocio
│   │   ├── services/impl/      # Implementaciones
│   │   ├── repositories/       # Acceso a datos (JPA)
│   │   ├── entities/           # Entidades del dominio
│   │   ├── Dtos/               # Objetos de transferencia
│   │   ├── security/           # Configuración de seguridad JWT
│   │   │   └── filter/         # Filtros de autenticación/validación
│   │   ├── validations/        # Validaciones custom
│   │   ├── utils/              # Utilidades compartidas
│   │   ├── PlataformaApplication.java
│   │   └── RoleInitializer.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/academia/plataforma/
        └── PlataformaApplicationTests.java
```

---

## 🔐 Características técnicas destacadas

### Backend Robusto
- Arquitectura por capas: `Controller → Service → Repository`
- Seguridad centralizada con `SpringSecurityConfig`
- Filtros JWT dedicados para autenticación y validación
- Inicialización automática de roles al arranque

### Rendimiento y escalabilidad
- Diseño stateless para escalabilidad horizontal
- ORM con JPA/Hibernate y consultas orientadas al dominio
- Estructura modular que facilita evolución del sistema

### Mantenibilidad
- Separación clara de responsabilidades
- DTOs para desacoplar modelo interno de respuestas API
- Validaciones reutilizables (`@IsRequired`, `@ValidDateRange`)
- Código listo para integración con frontend web/mobile

---

## 🚀 Configuración y ejecución locales

### 1) Prerequisitos
- Java 17+
- MySQL 8+
- Maven (opcional, se incluye Maven Wrapper)

### 2) Configurar variables de entorno
En `src/main/resources/application.properties` se utilizan:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

Ejemplo en Windows PowerShell:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/academia_db?useSSL=false&serverTimezone=UTC"
$env:DB_USER="root"
$env:DB_PASSWORD="tu_password"
```

### 3) Ejecutar la aplicación
Desde la raíz del proyecto:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternativa multiplataforma:

```bash
./mvnw spring-boot:run
```

Base URL:

```text
http://localhost:8080
```

### 4) Ejecutar pruebas

```powershell
.\mvnw.cmd test
```

---

## 📬 Uso rápido de API

### Login

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

### Consumir endpoint protegido

```http
Authorization: Bearer <token>
```

---

## 📝 Notas

Este repositorio refleja un enfoque profesional de backend para dominio educativo, orientado a demostrar:

- Dominio de arquitectura backend con Spring Boot
- Implementación de seguridad real con JWT + RBAC
- Modelado de datos relacional para casos académicos
- Aplicación de buenas prácticas de mantenibilidad y escalabilidad

---

## 👨‍💻 Acerca de este Proyecto

**Desarrollador**: Samuel Sánchez Guzmán  
**Tipo**: Backend REST API  
**Estatus**: Active / Production-ready architecture  
**Última Actualización**: Marzo 2026
