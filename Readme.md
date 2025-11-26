# Spring Boot JWT User Management Service

A complete production-ready **User Authentication & Management Service** built using  
**Spring Boot 4, Java 21, JWT, Spring Security, Postgrees Database, JPA, Validation & Role-Based Access Control (RBAC)**.

This project demonstrates backend development skills including:
- Authentication (Login + Register)
- JWT-based Authorization
- RBAC (USER, ADMIN)
- Password Encryption (BCrypt)
- Exception Handling
- DTO + Entity + Service + Repository Layers
- Unit Tests + Integration Tests
- Deployment-ready for Render / Cloud

---

## 🚀 Features

### ✅ Authentication
- Register user
- Login user
- Get current logged-in profile (`/api/auth/me`)

### ✅ Authorization
- JWT Token Validation
- Secured Endpoints
- Role-based access control (USER_ROLE & ADMIN_ROLE)

### ✅ User Management
- Fetch all users (ADMIN only)
- Fetch user by ID (ADMIN or SELF)
- Delete user (ADMIN only)

### ✅ Security
- BCrypt password hashing  
- Stateless authentication  
- Custom `JwtAuthenticationFilter`  

### ✅ Database
- PostgreSQL from render and setup for In-memory H2 DB as well
- Auto schema creation  
- Default ADMIN auto-created at startup  

### Default Admin  
email: admin@example.com
password: Admin@123



---

## 🛠 Tech Stack

| Layer | Technology |
|------|------------|
| Language | Java 17 |
| Framework | Spring Boot 4 |
| Security | Spring Security + JWT |
| ORM | JPA + Hibernate |
| Database | PostgreSQL | H2 (Memory DB) |
| Validation | Jakarta Validation |
| Build Tool | Maven |

---

## 📌 API Endpoints

### 🔐 Auth Controller (`/api/auth`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register a new user |
| POST | `/login` | Login and receive JWT |
| GET | `/me` | Get logged-in user profile |
| GET | `/users` | Get all user details |

---

### 👤 User Controller (`/api/users`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| GET | `/` | ADMIN | Get all users |
| GET | `/{id}` | ADMIN or SELF | Fetch user details |
| DELETE | `/{id}` | ADMIN | Delete user |

---

## 📦 Project Structure

```
📦 springboot-jwt-user-management-service
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── userservice
│   │   │               ├── UserServiceApplication.java
│   │   │               │
│   │   │               ├── config
│   │   │               │   ├── DataInitializer.java
│   │   │               │   ├── SecurityConfig.java
│   │   │               │   ├── JwtAuthenticationFilter.java
│   │   │               │   └── JwtAuthEntryPoint.java
│   │   │               │
│   │   │               ├── controller
│   │   │               │   ├── AuthController.java
│   │   │               │   └── UserController.java
│   │   │               │
│   │   │               ├── dto
│   │   │               │   ├── ApiResponse.java
│   │   │               │   ├── LoginRequest.java
│   │   │               │   ├── RegisterRequest.java
│   │   │               │   └── JwtResponse.java
│   │   │               │
│   │   │               ├── model
│   │   │               │   ├── User.java
│   │   │               │   └── Role.java
│   │   │               │
│   │   │               ├── repository
│   │   │               │   ├── UserRepository.java
│   │   │               │   └── RoleRepository.java
│   │   │               │
│   │   │               ├── service
│   │   │               │   ├── AuthService.java
│   │   │               │   └── UserService.java
│   │   │               │
│   │   │               └── util
│   │   │                   └── JwtTokenProvider.java
│   │   │
│   │   └── resources
│   │       ├── application.yml
│   │       └── data.sql
│   │
│   └── test
│       └── java
│           
│
├── pom.xml
└── README.md
├── DockerFile
```


---


## Notes
   -  If you are checking the service i hosted on render it will take 1-2 minute os time to start the backend because i have hosted it as a free service so wait till it provide response.
   -  Use Postman or Thunder Client for testing.
   -  Tokens expire depending on configuration.
   - Make sure to send JSON format and correct Authorization header.

## ▶️ Run  these  render hosted backend on postmen directly using 
Base url 
  : https://springboot-jwt-user-management-service.onrender.com
  

Register User using:
 POST 
 url :  https://springboot-jwt-user-management-service.onrender.com/api/auth/register

 example of body :
    {
    "email": "test1@example.com",
    "password": "password123"
    }

For Login use :
 POST 
 url :  https://springboot-jwt-user-management-service.onrender.com/api/auth/login
 
 example of body :
 {
  "email": "test1@example.com",
  "password": "password123"
 }

Fetch Logged-in User Details using :
 GET 
 url :  https://springboot-jwt-user-management-service.onrender.com/api/auth/me

example of body :
 select Authorization as Bearer  and use the jwt token which has generated through login.

for Admin Login use credentials as :
 {
  "email": "admin@example.com",
  "password": "Admin@123"
}


Fetch All Users using :
 GET 
 url :  https://springboot-jwt-user-management-service.onrender.com/api/users
 example :
 select Authorization as Bearer  and use the jwt token which has generated through login.

  (it will provide user details only if you login as a Admin or else it will shoe an access denied error message)

Delete User by using : (only for Admin)
url :  https://springboot-jwt-user-management-service.onrender.com/api/users/{id}
 example of body :
 select Authorization as Bearer  and use the jwt token which has generated through login.







## ▶️ Run Locally by this commands

mvn clean install
mvn spring-boot:run


App runs on:  
👉 `http://localhost:8080`

---


