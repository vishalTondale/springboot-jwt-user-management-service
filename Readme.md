# Spring Boot JWT User Management Service

A complete production-ready **User Authentication & Management Service** built using  
**Spring Boot 4, Java 21, JWT, Spring Security, H2 Database, JPA, Validation & Role-Based Access Control (RBAC)**.

This project was developed as part of a hiring assignment and demonstrates backend development skills including:
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
- Role-based access control (USER & ADMIN)

### ✅ User Management
- Fetch all users (ADMIN only)
- Fetch user by ID (ADMIN or SELF)
- Delete user (ADMIN only)

### ✅ Security
- BCrypt password hashing  
- Stateless authentication  
- Custom `JwtAuthenticationFilter`  

### ✅ Database
- In-memory H2 DB for assignment  
- Auto schema creation  
- Default ADMIN auto-created at startup  

### Default Admin  
email: admin@example.com
password: Admin@123

yaml
Copy code

---

## 🛠 Tech Stack

| Layer | Technology |
|------|------------|
| Language | Java 21 |
| Framework | Spring Boot 4 |
| Security | Spring Security + JWT |
| ORM | JPA + Hibernate |
| Database | H2 (Memory DB) |
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

---

### 👤 User Controller (`/api/users`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| GET | `/` | ADMIN | Get all users |
| GET | `/{id}` | ADMIN or SELF | Fetch user details |
| DELETE | `/{id}` | ADMIN | Delete user |

---

## 📦 Project Structure

src/main/java/com/example/userservice
│
├── config
│ ├── DataInitializer.java
│ ├── SecurityConfig.java
│ ├── JwtAuthenticationFilter.java
│ └── JwtAuthEntryPoint.java
│
├── controller
│ ├── AuthController.java
│ └── UserController.java
│
├── dto
│ ├── LoginRequest.java
│ ├── RegisterRequest.java
│ ├── ApiResponse.java
│ └── JwtResponse.java
│
├── model
│ ├── User.java
│ └── Role.java
│
├── repository
│ ├── UserRepository.java
│ └── RoleRepository.java
│
├── service
│ ├── AuthService.java
│ └── UserService.java
│
└── util
└── JwtTokenProvider.java

yaml
Copy code

---

## 🧪 Testing

This project includes:
- Unit Tests  
- Integration Tests  

(See `src/test/java/...`)  

Run tests using:
mvn test

yaml
Copy code

---

## ▶️ Run Locally

mvn clean install
mvn spring-boot:run

yaml
Copy code

App runs on:  
👉 `http://localhost:8080`

---




## 📝 License
This project is created for  evaluation and educational use.

