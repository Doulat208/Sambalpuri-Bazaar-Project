# 🧵 Sambalpuri Bazaar – Business Management Web Application : <br>

![home](https://via.placeholder.com/900x400?text=Sambalpuri+Bazaar+Home)

---

## 📌 Project Desc : Sambalpuri Bazaar  

=> Sambalpuri Bazaar is a Spring Boot based Business Management Web Application designed to manage Sambalpuri products, users, admins, and orders through an admin dashboard.  
It provides an easy-to-use interface for product management, user handling, and order tracking.

---

## ✨ Features :

- Admin Login System  
- Product Management (Add / Update / Delete)  
- Product Image Upload  
- User Management  
- Order Management  
- Admin Dashboard  
- Thymeleaf Templates  
- MySQL Database Integration  

---

## 🛠️ Technologies Used :

- Spring Boot 3.5.0  
- Spring MVC  
- Spring Data JPA (Hibernate)  
- Thymeleaf  
- MySQL  
- HTML & CSS  
- Maven  
- IDE/Tool : Spring Tool Suite 4 (STS / Eclipse)  

---

## ⚙️ Installation :

1. Clone the repository :

2. Import the project into STS / Eclipse :
- File → Import → Maven → Existing Maven Project  
- Browse project folder → Finish  

3. Configure database in `application.properties`

4. Run the project :
- Run `main()` method of `SambalpuriBazaarApplication.java`  
- OR  
- Right click → Run As → Spring Boot App  

5. Open in browser :

6. Database tables will be created automatically  
- Add one Admin record manually for first login  

---

## 🗄️ Database :

Configure MySQL in `application.properties` :

spring.datasource.name=your_db_name
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
---

## 🔄 WorkFlow :

Admin Login
↓
Admin Dashboard
↓
Manage Products / Users / Orders
↓
MySQL Database

---

## 🖼️ Preview :

### Products
![products](https://via.placeholder.com/700x350?text=Products+Page)

### Login Page
![login](https://via.placeholder.com/700x350?text=Login+Page)

### Admin Panel
![admin](https://via.placeholder.com/700x350?text=Admin+Dashboard)

### Exception Page
![exception](https://via.placeholder.com/700x350?text=Exception+Page)

---

## 🎯 Purpose

This project is built to demonstrate Spring Boot, MVC architecture, Thymeleaf, and CRUD operations in a real-world business application.

---

## 👨‍💻 Developer

Doulat Biswal
Dolly Prajapati
Gargi Singh

MCA Student | KIET Group of Institutions | Java | Spring Boot | MySQL
