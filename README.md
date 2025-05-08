# Dev Register

![Java](https://img.shields.io/badge/Java-24-orange)
![Spring](https://img.shields.io/badge/Spring_Boot-3.4.5-green)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen)

Developer and task management system built with Spring Boot. This API allows registration, querying, updating, and logical deletion of developers and their respective tasks.

## 📋 Table of Contents

- [Overview](#-overview)
- [Main Features](#-main-features)
- [Technologies](#-technologies)
- [Installation and Setup](#-installation-and-setup)
- [API Usage](#-api-usage)
- [Database Structure](#%EF%B8%8F-database-structure)
- [Security Implementation](#-security-implementation)
- [API Documentation](#-api-documentation)
- [License](#-license)

## 🔎 Overview

Dev Register is a backend system for managing developers and their tasks. It allows you to register developers with different specializations, experience levels, and programming languages, and assign tasks with priorities and tracking status. The API is secured with JWT authentication for protected endpoints.

## 🚀 Main Features

### Authentication & Security

- User registration and login system
- JWT (JSON Web Tokens) based authentication
- Protected endpoints with token validation
- Role-based access control

### Developers

- Registration of developers with personal and professional information
- Paginated listing with HATEOAS support
- Query by ID
- Data updates
- Logical deletion (soft delete) and restoration

### Tasks

- Creation of tasks with title, description, and developer association
- Paginated listing with HATEOAS support
- Priority definition and status
- Date control (creation, start, and end)
- Status management (complete, active)
- Logical deletion (soft delete) and restoration

## 💻 Technologies

- **Java 24**
- **Spring Boot 3.4.5**
- **Spring Security**: For authentication and authorization
- **JWT (JSON Web Tokens)**: For secure token-based authentication
- **Spring Data JPA**: For data persistence
- **Spring HATEOAS**: For RESTful API implementation with hypermedia
- **PostgreSQL**: Main database
- **H2 Database**: For testing and development
- **Flyway**: For database migrations and versioning
- **Lombok**: For reducing boilerplate code
- **SpringDoc OpenAPI**: For automatic API documentation
- **Maven**: For dependency management

## 🔧 Installation and Setup

### Prerequisites

- Java 24 or higher
- Maven 3.6.3 or higher
- PostgreSQL (or use H2 for development)

### Installation Steps

1. Clone the repository:

```bash
git clone https://github.com/Ranieeery/dev-register.git
cd register
```

2. Configure the `application.properties` file with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Compile and run the application:

```bash
mvn clean install
mvn spring-boot:run
```

## 📝 API Usage

### Authentication Endpoints

#### Register a new user

```http
POST /auth/register
```

#### Login and get JWT token

```http
POST /auth/login
```

### Developer Endpoints

#### Create a developer

```http
POST /developer/create
```

#### List all developers (paginated)

```http
GET /developer/list
```

#### Find developer by ID

```http
GET /developer/list/{id}
```

#### Update developer

```http
PATCH /developer/update/{id}
```

#### Delete developer (soft delete)

```http
DELETE /developer/delete/{id}
```

#### Restore deleted developer

```http
PATCH /developer/delete/{id}/undo
```

### Task Endpoints

#### Create a task

```http
POST /tasks/create
```

#### List all tasks (paginated)

```http
GET /tasks/list
```

#### Find task by ID

```http
GET /tasks/list/{id}
```

#### Update task

```http
PATCH /tasks/update/{id}
```

#### Delete task (soft delete)

```http
DELETE /tasks/delete/{id}
```

#### Restore deleted task

```http
PATCH /tasks/delete/{id}/undo
```

## 🗃️ Database Structure

The system uses Flyway migrations to create and update the database structure. The main tables include:

- `tb_developer`: Stores developer information
- `tb_tasks`: Stores tasks associated with developers

Migrations include the addition of columns for:

- Active/inactive state management
- Task status
- Creation, start, and end dates
- Priorities
- Specializations and programming languages

## 🔒 Security Implementation

The API uses JWT (JSON Web Token) for authentication:

1. **Registration**: Users register with email and password
2. **Authentication**: Upon successful login, the system returns a JWT token
3. **Authorization**: For protected endpoints, the token must be included in the request header
4. **Token Format**: `Authorization: Bearer {your_jwt_token}`
5. **Token Lifetime**: Tokens are valid for 4 hours by default

JWT benefits in this implementation:
- Stateless authentication
- Cross-domain/CORS support
- Decoupled from user storage
- Security information embedded within the token

## 📚 API Documentation

Complete API documentation is available through Swagger UI, accessible after starting the application:

```html
http://localhost:8080/swagger-ui/index.html
```

## 📄 License

This project is licensed under the MIT License terms.

---

Developed by [Raniery](https://github.com/Ranieeery)
