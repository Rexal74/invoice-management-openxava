# Invoice Management System (OpenXava)

A backend-focused business application developed using Java, OpenXava,
Hibernate (JPA), and PostgreSQL. The project demonstrates enterprise-style
backend development with ORM, relational data modeling, and automatic UI
generation.

## 🚀 Features
- Invoice and order management
- Customer, product, category, and author entities
- Automatic CRUD UI generation with OpenXava
- Relational database design using JPA/Hibernate
- Business logic via calculators, actions, and validations

## 🛠 Tech Stack
- Java
- OpenXava
- Hibernate (JPA)
- PostgreSQL
- Maven
- Apache Tomcat

## 📦 Project Structure
- `src/main/java` → Domain models and business logic
- `src/main/resources/META-INF/persistence.xml` → JPA configuration
- `xava/` → OpenXava configuration
- `pom.xml` → Maven project configuration

## ⚙️ Setup Instructions
1. Configure a PostgreSQL datasource with JNDI name:
   `java:comp/env/jdbc/invoiceDS`
2. Deploy the project on Apache Tomcat
3. Start the server and access the application via browser

## 🎯 Purpose
This project was created to gain hands-on experience with enterprise backend
development, ORM, and database-driven applications using OpenXava.
