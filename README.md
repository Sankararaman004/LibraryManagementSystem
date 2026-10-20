# Library Management System

A Java-based Library Management System that uses MySQL to store and manage book records permanently.

## Features

- Add new books to the database
- View all books
- Search books by ID
- Search books by title or author
- Issue books
- Return books
- Validate user input
- Store book details permanently in MySQL

## Technologies Used

- Java (Core Java)
- MySQL
- JDBC (Java Database Connectivity)
- MySQL Connector/J
- Object-Oriented Programming (OOP)
- Visual Studio Code

## Database Setup

Create the database and table in MySQL Workbench:

```sql
CREATE DATABASE IF NOT EXISTS library_db;

USE library_db;

CREATE TABLE IF NOT EXISTS books (
    book_id INT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    is_issued BOOLEAN DEFAULT FALSE
);
```

## Configuration

Set these environment variables in PowerShell before running the application:

```powershell
$env:DB_USER = "root"
$env:DB_PASSWORD = "your_mysql_password"
```

Replace the placeholder with your own MySQL password. Never upload your actual password to GitHub.

## How to Run

Make sure the MySQL server is running and the MySQL Connector/J JAR file is located at `lib/mysql-connector-j-26.7.0.jar`.

Compile the Java files:

```powershell
javac -cp ".;lib\mysql-connector-j-26.7.0.jar" book.java DBConnection.java librarymanagement.java
```

Run the database connection test:

```powershell
java -cp ".;lib\mysql-connector-j-26.7.0.jar" DBConnection
```

Run the application:

```powershell
java -cp ".;lib\mysql-connector-j-26.7.0.jar" librarymanagement
```

## Learning Outcomes

- Java programming and object-oriented concepts
- SQL queries and database management
- JDBC connectivity between Java and MySQL
- Exception handling and input validation
- Using Git and GitHub for version control

## Future Improvements

- Add member management
- Track book issue and return dates
- Add due dates and fine calculation
- Introduce a graphical user interface

