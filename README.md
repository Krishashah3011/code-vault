# 🚀 CodeVault

CodeVault is a Code Snippet Management System developed using Spring Boot, Spring Security, Hibernate (JPA), Thymeleaf, and MySQL.

The application allows developers to securely store, manage, search, and organize their code snippets in one place.

---

## ✨ Features

- 🔐 User Registration & Login
- 👤 User-Specific Snippet Management
- ➕ Add New Snippets
- ✏️ Edit Existing Snippets
- 🗑️ Delete Snippets
- ⭐ Mark Snippets as Favorites
- 🔍 Search Snippets by Title
- 💻 Filter Snippets by Programming Language
- 🌙 Dark Mode / ☀️ Light Mode
- 📱 Responsive UI Design
- 🔒 Password Encryption using BCrypt

---

## 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- Hibernate (JPA)

### Frontend
- Thymeleaf
- HTML5
- CSS3
- JavaScript

### Database
- MySQL

### Build Tool
- Maven

---

## 📂 Project Structure

src/main/java/com/codevault/snippets

├── controller/
├── service/
├── service/impl/
├── repository/
├── entity/
├── config/
└── CodeVaultApplication.java


src/main/resources

├── templates/
├── static/
└── application.properties

---

## ⚙️ Installation

### Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/codevault.git
```

### Navigate to Project

```bash
cd codevault
```

### Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/codevault_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
```

### Run Application

Using Maven:

```bash
mvn spring-boot:run
```

Or:

```bash
.\mvnw.cmd spring-boot:run
```

---

## 🌐 Access Application

Open:

```text
http://localhost:8080
```

---

## 🗄️ Database Tables

### Users
- id
- username
- email
- password

### Snippets
- id
- title
- language
- code
- favorite
- user_id

---

## 🏗️ Architecture

The project follows MVC Architecture:

User → Controller → Service → Repository → Database

- Controller handles requests
- Service contains business logic
- Repository interacts with database
- Entity represents database tables

---
